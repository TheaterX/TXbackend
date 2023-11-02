package app.controllers;

import app.DTOs.CreateActorDTO;
import app.DTOs.CreateDirectorDTO;
import app.services.ActorService;
import app.services.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/director")
public class DirectorController {
    @Autowired
    private DirectorService directorService;

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void createAdmin( @RequestBody CreateDirectorDTO directorDTO)  {
        directorService.CreateDirector(directorDTO);
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @GetMapping(value = "/all",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CreateDirectorDTO> directors()  {
        return directorService.getDirectors();
    }
}
