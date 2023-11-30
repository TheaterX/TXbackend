package app.services;

import app.DTOs.CreateAwardDTO;
import app.DTOs.CreateReservationDTO;
import app.DTOs.GetDTOs.GetReservationDTO;
import app.DTOs.GetDTOs.GetSceneDTO;
import app.DTOs.GetDTOs.GetSeatDTO;
import app.DTOs.SeatDTO;
import app.DTOs.keysDTO.SeatKeyDTO;
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
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<GetSeatDTO> getTakenSeatsForScheduledPlay(Date date, String scene, int playId) {
        List<GetSeatDTO> seatDTOS = new ArrayList<>();
        for(Reservation res:  reservationRepository.findSeatsByDateSceneAndPlayId(date, scene, playId)){
            for(Seat s : res.getSeats())
                seatDTOS.add(new GetSeatDTO(s.getSeatKey().getRow(),s.getSeatKey().getNumber(),res.getUserUsername()));
        }
        return seatDTOS;
    }

    public List<GetReservationDTO> getReservationsByUsername(String username) {
        List<GetReservationDTO> reservationDTOS = new ArrayList<>();
        for(Reservation reservation : reservationRepository.getReservationsByUsername(username)){
            List<SeatDTO> seats = new ArrayList<>();
            for(Seat s : reservation.getSeats()){
                seats.add(new SeatDTO(new SeatKeyDTO(s.getSeatKey().getRow(),s.getSeatKey().getNumber())));
            }
            reservationDTOS.add(new GetReservationDTO(reservation.getId(),reservation.isCanceled(),reservation.isPaid(),reservation.getSeats().size(),
                    reservation.getScheduledPlay().getPlay().getName(),
                    reservation.getScheduledPlay().getId().getScheduledDate(),
                    reservation.getScheduledPlay().getId().getSceneName(),seats
                    ));

        }

        return reservationDTOS;
    }

    public void cancel(int id) {
        Reservation reservation = reservationRepository.getReferenceById(id);
        reservation.setCanceled(true);
        reservationRepository.save(reservation);
    }
}
