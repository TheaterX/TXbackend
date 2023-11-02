package app.services;

import app.DTOs.CreateRoleDTO;
import app.model.Role;
import app.repositories.IPlayRepository;
import app.repositories.IRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IPlayRepository playRepository;
    @Autowired
    private ModelMapper modelMapper;
    public void CreateRole(CreateRoleDTO createRoleDTO){
            Role role = modelMapper.map(createRoleDTO, Role.class);
            role.setPlay(playRepository.getReferenceById(createRoleDTO.getId().getPlayId()));
            roleRepository.save(role);
    }
}
