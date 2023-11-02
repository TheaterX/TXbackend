package app.controllers;

import app.DTOs.CreateRoleDTO;
import app.DTOs.CreateScheduledPlayDTO;
import app.DTOs.SearchPlaysDTO;
import app.DTOs.SearchPlaysReturnDTO;
import app.services.ScheduledPlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/scheduledPlay")
public class ScheduledPlayController {
    @Autowired
    private ScheduledPlayService scheduledPlayService;

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void createScheduledPlay( @RequestBody CreateScheduledPlayDTO createScheduledPlayDTO)  {
        scheduledPlayService.createScheduledPlay(createScheduledPlayDTO);
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @GetMapping(value = "/search",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public SearchPlaysReturnDTO searchFree(@RequestBody SearchPlaysDTO searchPlaysDTO)  {
        return scheduledPlayService.searchPlays(searchPlaysDTO);
    }
}
