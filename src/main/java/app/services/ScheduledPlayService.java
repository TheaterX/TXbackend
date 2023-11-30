package app.services;

import app.DTOs.*;
import app.DTOs.GetDTOs.GetScheduledPlaysDTO;
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
    private IReservationRepository reservationRepository;
    @Autowired
    private PlayService playService;
    @Autowired
    private ModelMapper modelMapper;
    public void createScheduledPlay(CreateScheduledPlayDTO createScheduledPlayDTO){
        if(createScheduledPlayDTO.getId().getPlayId() == -1 ) createScheduledPlayDTO.getId().setPlayId(802);
        if(createScheduledPlayDTO.isTravels()) createScheduledPlayDTO.getId().setSceneName("Travels");
        ScheduledPlay scheduledPlay = modelMapper.map(createScheduledPlayDTO, ScheduledPlay.class);
        if(createScheduledPlayDTO.isGuest()) scheduledPlay.getId().setPlayId(802);
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

    private List<Date> fillUpDates3MonthsInAdvance(){
        List<Date> dates = new ArrayList<>();
        Date date = new Date();
        for(int i = 0;i<90.;i++){
            dates.add(addMonthAndDayToDate(date,0,i));
        }
        return dates;
    }

    private List<Date> datesFreeForPlay(int playId,String scene) {
        Date date = new Date();
        List<Date> dates = new ArrayList<>();
        List<Date> returnDates = new ArrayList<>();
        if(playId != -1)
        for(Date d1 : fillUpDates3MonthsInAdvance()) {
            boolean addDate = true;
            for(Date d : scheduledPlayRepository.findDatesWherePlayIsNotScheduled(playId,date,addMonthAndDayToDate(date,3,0)))
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
        else{
            dates = fillUpDates3MonthsInAdvance();
        }
        if(date != null)
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
        else return fillUpDates3MonthsInAdvance();
        return returnDates;
    }

    private List<PlaySceneDTO> playsFreeForDate(Date date,int repertoire){
        List<PlaySceneDTO> plays = new ArrayList<>();
        Repertoire Rrepertoire = repertoireRepository.getReferenceById(repertoire);
        if(Rrepertoire == null) return new ArrayList<>();
        if(date == null) {
            for(Play play : playRepository.findPlaysByYear(repertoire))
                plays.add(new PlaySceneDTO(play.getId(),play.getName(),play.getScene().getName()));
            return plays;
        }
        for(Play pl : scheduledPlayRepository.findPlaysNotScheduledForDateAndRepertoire(date,Rrepertoire)){
            boolean addPlay = true;
            for(Role rl : pl.getRoles()){
                for(Actor ac : rl.getActors()){
                    if(scheduledPlayRepository.isActorBusyOnDate(ac.getUMCN(),date))
                        addPlay = false;
                }
            }
            if(addPlay) plays.add(new PlaySceneDTO(pl.getId(),pl.getName(),pl.getScene().getName()));
        }
        return plays;
    }

    public SearchPlaysReturnDTO searchPlays(SearchPlaysDTO searchPlaysDTO) {
        List<PlaySceneDTO> plays = playsFreeForDate(searchPlaysDTO.getDate(),searchPlaysDTO.getRepertoire());
        List<Date> dates = datesFreeForPlay(searchPlaysDTO.getPlayId(),searchPlaysDTO.getScene());
        List<SceneNumberDTO> scenes = new ArrayList<>();
        if(searchPlaysDTO.getDate() != null)
        for (Scene s : scheduledPlayRepository.findFreeScenesForDate(searchPlaysDTO.getDate())){
            scenes.add(new SceneNumberDTO(s.getName(),s.getNumberOfSeats()));
        }
        else for(Scene s : sceneRepository.findAll()){
            scenes.add(new SceneNumberDTO(s.getName(),s.getNumberOfSeats()));
        }

        return new SearchPlaysReturnDTO(plays,scenes,dates);
    }

    public int calculateFreeSeats(ScheduledPlay scheduledPlay){
        return reservationRepository.countReservedSeatsByScheduledPlay(scheduledPlay);
    }

    public List<GetScheduledPlaysDTO> getAll() {
        List<GetScheduledPlaysDTO> scheduledPlaysDTOS = new ArrayList<>();
        for(ScheduledPlay sp : scheduledPlayRepository.findAll()){
            GetScheduledPlaysDTO addSP = new GetScheduledPlaysDTO(sp.getId().getPlayId(),sp.getPlay().getName(),sp.getScene().getName(),
                    sp.getId().getScheduledDate(), sp.getScene().getNumberOfSeats() - calculateFreeSeats(sp),
                    playService.convertDirectorsToString(sp.getPlay().getDirectors()));
            if(sp.isGuest()) addSP.setPlayName(sp.getGuestPlayName());
            scheduledPlaysDTOS.add(addSP);
        }
        return scheduledPlaysDTOS;
    }
}
