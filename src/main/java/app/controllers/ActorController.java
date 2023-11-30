package app.controllers;

import app.DTOs.AttachRoleToActorDTO;
import app.DTOs.CreateActorDTO;
import app.DTOs.CreatePlayDTO;
import app.services.ActorService;
import app.services.PlayService;
import com.sun.tools.attach.AttachNotSupportedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/actor")
public class ActorController {
    @Autowired
    private ActorService actorService;

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void createActor( @RequestBody CreateActorDTO createActorDTO)  {
        actorService.CreateActor(createActorDTO);
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PutMapping(value = "/attachRole",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void AttachRole( @RequestBody AttachRoleToActorDTO attachRoleToActorDTO)  {
        actorService.AttachRoleToActor(attachRoleToActorDTO);
    }
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @GetMapping(value = "/all",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CreateActorDTO> actors()  {
        return actorService.getActors();
    }
}
