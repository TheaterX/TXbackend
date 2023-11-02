package app.services;

import app.DTOs.CreateActorDTO;
import app.DTOs.GetDTOs.GetSeatDTO;
import app.DTOs.deleteDTOs.DeleteSeatDTO;
import app.model.Scene;
import app.model.Seat;
import app.model.EmbededKeys.SeatKey;
import app.repositories.ISceneRepository;
import app.repositories.ISeatRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService {
    @Autowired
    private ISeatRepository seatRepository;
    @Autowired
    private ISceneRepository sceneRepository;
    @Autowired
    private ModelMapper modelMapper;
    public void CreateSeat(int row, int number, String sceneName){
        Seat seat = new Seat(new SeatKey(row,number,sceneName));
        seatRepository.save(seat);
    }

    public List<GetSeatDTO> getSeats(String sceneName) {
        List<GetSeatDTO> getSeatDTOS = new ArrayList<>();
        for(Seat s : seatRepository.findByScene(sceneName)){
            getSeatDTOS.add(new GetSeatDTO(s.getSeatKey().getRow(), s.getSeatKey().getNumber()));
        }
        return getSeatDTOS;
    }

    public void deleteSeat(DeleteSeatDTO deleteSeatDTO) {
        seatRepository.deleteById(new SeatKey(deleteSeatDTO.getRow(),deleteSeatDTO.getNumber(),deleteSeatDTO.getSceneName()));
        Scene scene = sceneRepository.getReferenceById(deleteSeatDTO.getSceneName());
        scene.setNumberOfSeats(scene.getNumberOfSeats()-1);
        sceneRepository.save(scene);
    }
}
