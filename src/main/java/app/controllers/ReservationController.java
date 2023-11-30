package app.controllers;

import app.DTOs.CreateReservationDTO;
import app.DTOs.CreateSceneDTO;
import app.DTOs.GetDTOs.GetReservationDTO;
import app.DTOs.GetDTOs.GetSeatDTO;
import app.services.ReservationService;
import app.services.SceneService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@RestController
@RequestMapping(value = "/api/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void createReservation( @RequestBody CreateReservationDTO createReservationDTO)  {
        reservationService.CreateReservation(createReservationDTO);
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @GetMapping(value = "/takenSeats",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetSeatDTO> getAllSeatsForSP(@RequestParam(name = "date", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date date,
                                             @RequestParam(name = "sceneName", required = true) String scene,
                                             @RequestParam(name = "playId", required = true) int playId) throws ParseException {
        return reservationService.getTakenSeatsForScheduledPlay(date, scene, playId);
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @GetMapping(value = "/usersReservations",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetReservationDTO> getAllSeatsForSP(@RequestParam(name = "username", required = true) String username) {
        return reservationService.getReservationsByUsername(username);
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PutMapping(value = "/cancel",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void getAllSeatsForSP(@RequestParam(name = "id", required = true) int id) {
        System.out.println("xd");
        reservationService.cancel(id);
    }
}
