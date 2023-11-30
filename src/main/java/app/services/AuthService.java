package app.services;

import app.DTOs.LoginDTO;
import app.model.User.User;
import app.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private AuthJWT authJWT;
    @Autowired
    private IUserRepository userRepository;

    public String Auth(LoginDTO loginDTO) {
        var check = userRepository.findById(loginDTO.getUsername());
        if (!check.isPresent()) return "";
        User user = userRepository.getReferenceById(loginDTO.getUsername());
        if(user == null) return "";

        if(loginDTO.getPassword().equals(user.getPassword())) {
            return authJWT.generateToken(user.getUsername(),user.getUserType().toString());
        }
        return "";
    }
}
