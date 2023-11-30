package app.services;

import app.DTOs.*;
import app.DTOs.GetDTOs.GetFullPlayDTO;
import app.DTOs.GetDTOs.GetPlay;
import app.DTOs.GetDTOs.GetRoleDTO;
import app.DTOs.keysDTO.RoleKeyDTO;
import app.model.*;
import app.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PlayService {
    @Autowired
    private IPlayRepository playRepository;
    @Autowired
    private ActorService actorService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ISceneRepository sceneRepository;
    @Autowired
    private IGanreRepository ganreRepository;
    @Autowired
    private IDirectorRepository directorRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<GetPlay> getPlaysForYear(int year){
        List<GetPlay> getPlayList = new ArrayList<>();
        for(Play play : playRepository.findPlaysByYear(year)){
            getPlayList.add(new GetPlay(play.getName(),convertDirectorsToString(play.getDirectors()),play.getId()));
        }
        return getPlayList;
    }

    public List<GetPlay> getPlays(){
        List<GetPlay> getPlayList = new ArrayList<>();
        for(Play play : playRepository.findAll()){
            getPlayList.add(new GetPlay(play.getName(),convertDirectorsToString(play.getDirectors()),play.getId()));
        }
        return getPlayList;
    }

    public String convertDirectorsToString(List<Director> dirs){
        String directors = "";
        for(Director director : dirs){
            directors += director.getName() + " " + director.getSurname() + ", ";
        }
        StringBuilder modifiedString = new StringBuilder(directors);
        if(directors.length() > 2)
            modifiedString.deleteCharAt(directors.length()-2);
        return modifiedString.toString();
    }

    public String convertGenresToString(List<Ganre> gans){
        String ganres = "";
        for(Ganre ganre : gans){
            ganres += ganre.getName() + ", ";
        }
        StringBuilder modifiedString = new StringBuilder(ganres);
        if(ganres.length() > 2)
            modifiedString.deleteCharAt(ganres.length()-2);
        return modifiedString.toString();
    }

    public String convertAwardsToString(List<Play_award> awas){
        String awards = "";
        for(Play_award award : awas){
            awards += award.getAward().getName() + ", ";
        }
        StringBuilder modifiedString = new StringBuilder(awards);
        if(awards.length() > 2)
            modifiedString.deleteCharAt(awards.length()-2);
        return modifiedString.toString();
    }

    public String convertActorsToString(List<Actor> acts){
        String actors = "";
        for(Actor actor : acts){
            actors += actor.getName() + " " + actor.getSurname() + ", ";
        }
        StringBuilder modifiedString = new StringBuilder(actors);
        if(actors.length() > 2)
            modifiedString.deleteCharAt(actors.length()-2);
        return modifiedString.toString();
    }

    public ResponseEntity<String> CreatePlay(CreatePlayDTO createPlayDTO){
        Play play = modelMapper.map(createPlayDTO,Play.class);
        try {
            List<Director> directors = directorRepository.findAllById(createPlayDTO.getDirectors());
            if (directors == null || directors.size() == 0) throw new Exception("no director");
            play.setDirectors(directors);
            List<Ganre> ganres= ganreRepository.findAllById(createPlayDTO.getGenres());
            if (ganres == null || ganres.size() == 0) throw new Exception("no ganre");
            play.setGanres(ganres);
            Scene scene = sceneRepository.getReferenceById(createPlayDTO.getScene());
            if (scene == null) throw new Exception("no scene");
            play.setScene(scene);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        playRepository.save(play);
        Optional<Integer> addedPlayId = playRepository.findMaxId();
        for(RolesActorsDTO radto : createPlayDTO.getRoles()) {
            System.out.println(radto.getActor().getName() + " " + radto.getRoleName());
            roleService.CreateRole(new CreateRoleDTO(new RoleKeyDTO(addedPlayId.get(), radto.getRoleName()), radto.getDescription()));
            actorService.AttachRoleToActor(new AttachRoleToActorDTO(radto.getActor().getUmcn(), new RoleKeyDTO(addedPlayId.get(), radto.getRoleName())));
        }
        return ResponseEntity.ok("Success");
    }

    public GetFullPlayDTO getFullPlay(int playId) {
        Play play = playRepository.getReferenceById(playId);
        List<GetRoleDTO> roles = new ArrayList<>();
        for(Role role : play.getRoles()){
            roles.add(new GetRoleDTO(convertActorsToString(role.getActors()),role.getId().getName()));
        }
        return new GetFullPlayDTO(play.getGrade(),convertGenresToString(play.getGanres()),convertDirectorsToString(play.getDirectors()),convertAwardsToString(play.getAwards()),
                roles);
    }
}
