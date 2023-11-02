package app.services;

import app.DTOs.CreateUserDTO;
import app.model.User.AdminUser;
import app.model.User.ClassicUser;
import app.model.User.ClassicUserType;
import app.model.User.UserType;
import app.repositories.IAdminRepository;
import app.repositories.IClassicUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private IAdminRepository adminRepository;
    @Autowired
    private IClassicUserRepository classicUserRepository;
    @Autowired
    private ModelMapper modelMapper;

    public void createUser(CreateUserDTO createUserDTO){
        System.out.println(createUserDTO.getBirthDate());
        if(createUserDTO.getUserType().equals(UserType.ADMIN)) {
            AdminUser adminUser = modelMapper.map(createUserDTO, AdminUser.class);
            adminRepository.save(adminUser);
        }
        if(createUserDTO.getUserType().equals(UserType.CLASSIC)){
            ClassicUser classicUser = modelMapper.map(createUserDTO, ClassicUser.class);
            classicUserRepository.save(classicUser);
        }
    }

}
