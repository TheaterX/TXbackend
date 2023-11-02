package app.controllers;

import app.DTOs.CreateRepertoireDTO;
import app.DTOs.CreateRoleDTO;
import app.repositories.IRoleRepository;
import app.services.RepertoireService;
import app.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/repertoire")
public class RepertoireController {
    @Autowired
    private RepertoireService repertoireService;

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void createRepertoire( @RequestBody CreateRepertoireDTO createRepertoireDTO)  {
        repertoireService.createRepertoire(createRepertoireDTO);
    }
}
