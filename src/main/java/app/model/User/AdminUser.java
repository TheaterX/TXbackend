package app.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity

public class AdminUser extends User{

    public AdminUser() {
    }

    public AdminUser(String name, String surname, String password, UserType userType,String username) {
        super(name, surname, password, userType,username);
    }
}
