package app.services;

import app.DTOs.CreateAwardDTO;
import app.DTOs.CreateRepertoireDTO;
import app.model.Award;
import app.model.Play;
import app.model.Repertoire;
import app.model.Role;
import app.repositories.IAwardRepository;
import app.repositories.IPlayRepository;
import app.repositories.IRepertoireRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepertoireService {
    @Autowired
    private IRepertoireRepository repertoireRepository;
    @Autowired
    private IPlayRepository playRepository;
    @Autowired
    private ModelMapper modelMapper;
    public void createRepertoire(CreateRepertoireDTO createRepertoireDTO){
        Repertoire repertoire = modelMapper.map(createRepertoireDTO, Repertoire.class);
        List<Play> plays = playRepository.findAllById(createRepertoireDTO.getPlays());
        repertoire.setPlays(plays);
        repertoireRepository.save(repertoire);
    }
}
