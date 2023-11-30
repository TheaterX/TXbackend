package app.controllers;

import app.DTOs.CreateReservationDTO;
import app.DTOs.CreateRoleDTO;
import app.DTOs.EditRepertoireDTO;
import app.repositories.IRoleRepository;
import app.services.RepertoireService;
import app.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/repertoire")
public class RepertoireController {
    @Autowired
    private RepertoireService repertoireService;

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void createRepertoire( @RequestParam(name = "year", required = true) int year)  {
        repertoireService.createRepertoire(year);
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PutMapping(value = "/edit",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void addPlaysToRepertoire( @RequestBody EditRepertoireDTO editRepertoireDTO)  {
        repertoireService.editRepertoire(editRepertoireDTO);
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @GetMapping(value = "/all",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Integer> getAll()  {
        return repertoireService.getAll();
    }
}
