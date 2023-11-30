package app.services;

import app.DTOs.CreateAwardDTO;
import app.DTOs.EditRepertoireDTO;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class RepertoireService {
    @Autowired
    private IRepertoireRepository repertoireRepository;
    @Autowired
    private IPlayRepository playRepository;
    @Autowired
    private ModelMapper modelMapper;
    public void createRepertoire(int year){
        Repertoire repertoire = new Repertoire(year,Integer.toString(year) + " rep",new ArrayList<>());
        repertoireRepository.save(repertoire);
    }

    public List<Integer> getAll() {
        return this.repertoireRepository.getAllYearsAsIntegers();
    }

    public void editRepertoire(EditRepertoireDTO editRepertoireDTO) {
        Repertoire repertoire = this.repertoireRepository.getReferenceById(editRepertoireDTO.getYear());
        List<Play> plays = new ArrayList<>();
        for(int id : editRepertoireDTO.getPlays())
                plays.add(this.playRepository.getReferenceById(id));
        repertoire.setPlays(plays);
        repertoireRepository.save(repertoire);
    }
}
