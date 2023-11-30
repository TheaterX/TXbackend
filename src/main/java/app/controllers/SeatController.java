package app.controllers;

import app.DTOs.CreateUserDTO;
import app.DTOs.GetDTOs.GetSeatDTO;
import app.DTOs.deleteDTOs.DeleteSeatDTO;
import app.model.Seat;
import app.services.SeatService;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @GetMapping(value = "/all",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetSeatDTO> getSeats(@RequestParam(name = "scene", required = true) String sceneName)  {
        return seatService.getSeats(sceneName);
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @DeleteMapping(value = "/delete",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSeat(@RequestParam(name = "row", required = true) int row,
                           @RequestParam(name = "number", required = true) int number,
                           @RequestParam(name = "scene", required = true) String sceneName)  {
        seatService.deleteSeat(new DeleteSeatDTO(row,number,sceneName));
    }


}
