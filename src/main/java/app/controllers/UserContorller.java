package app.controllers;

import app.DTOs.CreateSceneDTO;
import app.DTOs.CreateUserDTO;
import app.services.SceneService;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
public class UserContorller {
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void createScene( @RequestBody CreateUserDTO createUserDTO)  {
        userService.createUser(createUserDTO);
    }
}
