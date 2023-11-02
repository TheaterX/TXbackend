package app.services;

import app.DTOs.CreateDirectorDTO;
import app.DTOs.GetDTOs.GetGanreDTO;
import app.repositories.IGanreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {
    @Autowired
    private IGanreRepository ganreRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<GetGanreDTO> getGenres() {
        return ganreRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, GetGanreDTO.class))
                .collect(Collectors.toList());
    }
}
