package app.services;

import app.DTOs.CreateActorDTO;
import app.DTOs.CreateDirectorDTO;
import app.DTOs.CreateSceneDTO;
import app.DTOs.EditSceneDTO;
import app.DTOs.GetDTOs.GetSceneDTO;
import app.model.Director;
import app.model.Scene;
import app.repositories.ISceneRepository;
import app.repositories.ISeatRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SceneService {
    @Autowired
    private ISceneRepository sceneRepository;
    @Autowired
    private ISeatRepository seatRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SeatService seatService;

    public void CreateScene(CreateSceneDTO createSceneDTO){
        Scene scene = modelMapper.map(createSceneDTO,Scene.class);
        scene.setNumberOfSeats(createSceneDTO.getSeatsInRow() * createSceneDTO.getRows());
        sceneRepository.save(scene);
        for(int i = 1;i <= createSceneDTO.getRows();i++)
            for(int j = 1;j <= createSceneDTO.getSeatsInRow();j++){
                seatService.CreateSeat(i,j,createSceneDTO.getName());
            }
    }

    public void EditScene(EditSceneDTO editSceneDTO) {
        Scene scene = sceneRepository.getReferenceById(editSceneDTO.getName());
        scene.setNumberOfSeats(editSceneDTO.getNumberOfSeats());
        sceneRepository.save(scene);
    }

    public List<GetSceneDTO> getScenes() {
        return sceneRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, GetSceneDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteScene(String sceneName) {
        seatRepository.deleteSeatsBySceneName(sceneName);
        sceneRepository.deleteById(sceneName);
    }
}
