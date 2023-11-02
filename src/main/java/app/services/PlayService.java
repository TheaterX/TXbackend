package app.services;

import app.DTOs.*;
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

    public ResponseEntity<String> CreatePlay(CreatePlayDTO createPlayDTO){
        System.out.println("USLO");
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
        System.out.println("ZISLOOOOOOOOOOOOOOOO TF");
        playRepository.save(play);
        Optional<Integer> addedPlayId = playRepository.findMaxId();
        for(RolesActorsDTO radto : createPlayDTO.getRoles()) {
            roleService.CreateRole(new CreateRoleDTO(new RoleKeyDTO(addedPlayId.get(), radto.getRoleName()), radto.getDescription()));
            actorService.AttachRoleToActor(new AttachRoleToActorDTO(radto.getActor().getUmcn(), new RoleKeyDTO(addedPlayId.get(), radto.getRoleName())));
        }
        return ResponseEntity.ok("Success");
    }
//    public void check(){
        //Play play = playRepository.findById(1).get();
//        Award award = awardRepository.findById("Nobel").get();
//        List<Play_award> awards = new ArrayList<>();
//        Play_award play_award = new Play_award(new PAKEY(1,"Nobel"),new Date(),"Kisac");
//        playAwardRepository.save(play_award);
//        awards.add(play_award);
//        play.setAwards(awards);
//        playRepository.save(play);
//        Play_award play_award = playAwardRepository.findById(new PAKEY(1,"Nobel")).get();
 //       System.out.println(play_award.getPlay().getName());
 //   }
}
