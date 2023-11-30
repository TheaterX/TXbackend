package app.controllers;

import app.DTOs.CreateScheduledPlayDTO;
import app.DTOs.GetDTOs.GetScheduledPlaysDTO;
import app.DTOs.SearchPlaysDTO;
import app.DTOs.SearchPlaysReturnDTO;
import app.services.ScheduledPlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
    @GetMapping(value = "/search",produces = MediaType.APPLICATION_JSON_VALUE)
    public SearchPlaysReturnDTO searchFree(@RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date,
                                           @RequestParam(name = "scene", required = true) String scene,
                                           @RequestParam(name = "playId", required = true) int playId,
                                           @RequestParam(name = "repertoire", required = true) int repertoire)  {
        return scheduledPlayService.searchPlays(new SearchPlaysDTO(date,scene,playId,repertoire));
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetScheduledPlaysDTO> All()  {
        return scheduledPlayService.getAll();
    }
}
