package app.DTOs.GetDTOs;

import app.DTOs.SeatDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetReservationDTO {
    private int id;
    private boolean canceled;
    private boolean paid;
    private int seatsN;
    private String play;
    private Date date;
    private String scene;
    private List<SeatDTO> seats;
}
