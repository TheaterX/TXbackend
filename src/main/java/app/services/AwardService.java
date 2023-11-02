package app.services;

import app.DTOs.CreateAwardDTO;
import app.DTOs.CreateDirectorDTO;
import app.model.Award;
import app.model.Director;
import app.repositories.IAwardRepository;
import app.repositories.IDirectorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AwardService {
    @Autowired
    private IAwardRepository awardRepository;
    @Autowired
    private ModelMapper modelMapper;
    public void CreateAward(CreateAwardDTO createAwardDTO){
        Award award = modelMapper.map(createAwardDTO,Award.class);
        awardRepository.save(award);
    }
}
