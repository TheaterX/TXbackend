package app.DTOs;

import app.DTOs.keysDTO.ScheduledPlayKeyDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateReservationDTO {
    private ScheduledPlayKeyDTO scheduledPlayKey;
    private String userUsername;
    private boolean paid;
    private List<SeatDTO> seat;
}
