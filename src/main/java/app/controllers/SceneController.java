package app.controllers;

import app.DTOs.CreateActorDTO;
import app.DTOs.CreateSceneDTO;
import app.DTOs.EditSceneDTO;
import app.DTOs.GetDTOs.GetSceneDTO;
import app.services.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/scene")
public class SceneController {
    @Autowired
    private SceneService sceneService;

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void createScene( @RequestBody CreateSceneDTO createSceneDTO)  {
        sceneService.CreateScene(createSceneDTO);
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PutMapping(value = "/edit",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void editScene( @RequestBody EditSceneDTO editSceneDTO)  {
        sceneService.EditScene(editSceneDTO);
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @GetMapping(value = "/all",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetSceneDTO> allScenes()  {
        return sceneService.getScenes();
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @DeleteMapping(value = "/delete",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteScene(@RequestParam(name = "scene", required = true) String sceneName)  {
        sceneService.deleteScene(sceneName);
    }
}
