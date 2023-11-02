package app.services;

import app.DTOs.*;
import app.model.*;
import app.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ScheduledPlayService {
    @Autowired
    private IScheduledPlayRepository scheduledPlayRepository;
    @Autowired
    private IPlayRepository playRepository;
    @Autowired
    private IRepertoireRepository repertoireRepository;
    @Autowired
    private ISceneRepository sceneRepository;
    @Autowired
    private ModelMapper modelMapper;
    public void createScheduledPlay(CreateScheduledPlayDTO createScheduledPlayDTO){
        ScheduledPlay scheduledPlay = modelMapper.map(createScheduledPlayDTO, ScheduledPlay.class);
        scheduledPlay.setCanceled(false);
        scheduledPlayRepository.save(scheduledPlay);
    }

    private Date addMonthAndDayToDate(Date date,int months,int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add((Calendar.MONTH),months);
        calendar.add((Calendar.DAY_OF_MONTH),days);
        return calendar.getTime();
    }

    private static int compareDatesIgnoringTime(Date date1, Date date2) {
        // Convert Date objects to Instant
        Instant instant1 = date1.toInstant();
        Instant instant2 = date2.toInstant();

        // Convert Instant objects to LocalDate
        LocalDate localDate1 = instant1.atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = instant2.atZone(ZoneId.systemDefault()).toLocalDate();

        // Compare LocalDate objects
        return localDate1.compareTo(localDate2);
    }

    private List<Date> fillUpDatesMonthInAdvance(){
        List<Date> dates = new ArrayList<>();
        Date date = new Date();
        for(int i = 0;i<31.;i++){
            dates.add(addMonthAndDayToDate(date,0,i));
        }
        return dates;
    }

    private List<Date> datesFreeForPlay(int playId,String scene) {
        Date date = new Date();
        List<Date> dates = new ArrayList<>();
        List<Date> returnDates = new ArrayList<>();
        for(Date d1 : fillUpDatesMonthInAdvance()) {
            boolean addDate = true;
            for(Date d : scheduledPlayRepository.findDatesWherePlayIsNotScheduled(playId,date,addMonthAndDayToDate(date,1,0)))
            {
                if(compareDatesIgnoringTime(d,d1) == 0) {
                    addDate = !true;
                    break;
                }
            }
            if(addDate)
                for(Role rl : playRepository.getReferenceById(playId).getRoles()) {
                    for (Actor ac : rl.getActors())
                        if (scheduledPlayRepository.isActorBusyOnDate(ac.getUMCN(), d1)) {
                            addDate = !true;
                        }
                }
            if(addDate)
                dates.add(d1);
        }
        for(Date dateIt : dates) {
            boolean addDate = true;
            for (Date dateIt2 : scheduledPlayRepository.findFreeDatesForScene(scene)) {
                if (compareDatesIgnoringTime(dateIt, dateIt2) == 0) {
                    addDate = false;
                    break;
                }
            }
            if(addDate)
                returnDates.add(dateIt);
        }
        return returnDates;
    }

    private List<PlaySceneDTO> playsFreeForDate(Date date,int repertoire){
        List<PlaySceneDTO> plays = new ArrayList<>();
        Repertoire Rrepertoire = repertoireRepository.getReferenceById(repertoire);
        if(Rrepertoire == null) return new ArrayList<>();
        for(Play pl : scheduledPlayRepository.findPlaysNotScheduledForDateAndRepertoire(date,Rrepertoire)){
            boolean addPlay = true;
            for(Role rl : pl.getRoles()){
                for(Actor ac : rl.getActors()){
                    if(scheduledPlayRepository.isActorBusyOnDate(ac.getUMCN(),date))
                        addPlay = false;
                }
            }
            if(addPlay) plays.add(new PlaySceneDTO(pl.getName(),pl.getScene().getName()));
        }
        return plays;
    }

    public SearchPlaysReturnDTO searchPlays(SearchPlaysDTO searchPlaysDTO) {
        List<PlaySceneDTO> plays = playsFreeForDate(searchPlaysDTO.getDate(),searchPlaysDTO.getRepertoire());
        List<Date> dates = datesFreeForPlay(searchPlaysDTO.getPlayId(),searchPlaysDTO.getScene());
        List<SceneNumberDTO> scenes = new ArrayList<>();
        for (Scene s : scheduledPlayRepository.findFreeScenesForDate(searchPlaysDTO.getDate())){
            scenes.add(new SceneNumberDTO(s.getName(),s.getNumberOfSeats()));
        }

        return new SearchPlaysReturnDTO(plays,scenes,dates);
    }
}
