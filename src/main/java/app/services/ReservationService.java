package app.services;

import app.DTOs.CreateAwardDTO;
import app.DTOs.CreateReservationDTO;
import app.DTOs.SeatDTO;
import app.model.Award;
import app.model.EmbededKeys.ScheduledPlayKey;
import app.model.EmbededKeys.SeatKey;
import app.model.Reservation;
import app.model.ScheduledPlay;
import app.model.Seat;
import app.repositories.IReservationRepository;
import app.repositories.ISceneRepository;
import app.repositories.IScheduledPlayRepository;
import app.repositories.ISeatRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private IReservationRepository reservationRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IScheduledPlayRepository scheduledPlayRepository;
    @Autowired
    private ISeatRepository seatRepository;
    public void CreateReservation(CreateReservationDTO createReservationDTO){
        Reservation reservation = modelMapper.map(createReservationDTO, Reservation.class);
        List<Seat> seats = new ArrayList<>();
        for(SeatDTO sdto : createReservationDTO.getSeat()){
            seats.add(seatRepository.getReferenceById(new SeatKey(sdto.getId().getRow(),sdto.getId().getNumber(),createReservationDTO.getScheduledPlayKey().getSceneName())));
        }
        reservation.setSeats(seats);
        ScheduledPlay scheduledPlay = scheduledPlayRepository.getReferenceById(modelMapper.map(createReservationDTO.getScheduledPlayKey(), ScheduledPlayKey.class));
        reservation.setCanceled(false);
        reservation.setScheduledPlay(scheduledPlay);
        reservationRepository.save(reservation);
    }
}
