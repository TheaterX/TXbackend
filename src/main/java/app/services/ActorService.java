package app.services;

import app.DTOs.AttachRoleToActorDTO;
import app.DTOs.CreateActorDTO;
import app.model.Actor;
import app.model.Role;
import app.model.EmbededKeys.RoleKey;
import app.repositories.IActorRepository;
import app.repositories.IRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorService {
    @Autowired
    private IActorRepository actorRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;
    public void CreateActor(CreateActorDTO createActorDTO){
        Actor actor = modelMapper.map(createActorDTO,Actor.class);
        actorRepository.save(actor);
    }
    public void AttachRoleToActor(AttachRoleToActorDTO attachRoleToActorDTO){
        Actor actor = actorRepository.getReferenceById(attachRoleToActorDTO.getActorUMCN());
        Role role = roleRepository.getReferenceById(modelMapper.map(attachRoleToActorDTO.getRoleId(),RoleKey.class));
        actor.addRole(role);
        actorRepository.save(actor);
    }

    public List<CreateActorDTO> getActors() {
        return actorRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, CreateActorDTO.class))
                .collect(Collectors.toList());
    }
}
