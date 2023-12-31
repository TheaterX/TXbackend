package app.DTOs;

import app.model.User.ClassicUserType;
import app.model.User.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateUserDTO {
    private String username;
    private String name;
    private String surname;
    private String password;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
}
