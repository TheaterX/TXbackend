package app.controllers;

import app.DTOs.CreateAwardDTO;
import app.DTOs.CreateDirectorDTO;
import app.services.AwardService;
import app.services.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/award")
public class AwardController {
    @Autowired
    private AwardService awardService;

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void createAdmin( @RequestBody CreateAwardDTO createAwardDTO)  {
        awardService.CreateAward(createAwardDTO);
    }
}