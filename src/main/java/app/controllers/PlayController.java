package app.controllers;

import app.DTOs.CreatePlayDTO;
import app.services.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(value = "/check",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void check()
    {
        //playService.check();
    }
}
