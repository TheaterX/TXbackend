package app.controllers;

import app.DTOs.CreatePlayDTO;
import app.DTOs.GetDTOs.GetFullPlayDTO;
import app.DTOs.GetDTOs.GetPlay;
import app.services.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/play")
public class PlayController {
    @Autowired
    private PlayService playService;
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void createPlay( @RequestBody CreatePlayDTO createPlayDTO)
    {
        playService.CreatePlay(createPlayDTO);;
    }
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @GetMapping(value = "/all",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetPlay> getAllPlays()
    {
        return playService.getPlays();
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @GetMapping(value = "/allYear",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetPlay> getPlaysForYear( @RequestParam(name = "year", required = true) int year)
    {
        return playService.getPlaysForYear(year);
    }
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @GetMapping(value = "/fullPlay",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public GetFullPlayDTO getFullPlay(@RequestParam(name = "playId", required = true) int playId)
    {
        return playService.getFullPlay(playId);
    }
}
