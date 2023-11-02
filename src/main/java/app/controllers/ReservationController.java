package app.controllers;

import app.DTOs.CreateReservationDTO;
import app.DTOs.CreateSceneDTO;
import app.services.ReservationService;
import app.services.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void createScene( @RequestBody CreateReservationDTO createReservationDTO)  {
        reservationService.CreateReservation(createReservationDTO);
    }
}
