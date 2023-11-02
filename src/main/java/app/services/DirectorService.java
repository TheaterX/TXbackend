package app.services;

import app.DTOs.CreateActorDTO;
import app.DTOs.CreateDirectorDTO;
import app.model.Actor;
import app.model.Director;
import app.repositories.IActorRepository;
import app.repositories.IDirectorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DirectorService {
    @Autowired
    private IDirectorRepository directorRepository;
    @Autowired
    private ModelMapper modelMapper;
    public void CreateDirector(CreateDirectorDTO createDirectorDTO){
        Director director = modelMapper.map(createDirectorDTO,Director.class);
        directorRepository.save(director);
    }

    public List<CreateDirectorDTO> getDirectors() {
        return directorRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, CreateDirectorDTO.class))
                .collect(Collectors.toList());
    }
}
