package app.model.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name ="user_table")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    private String username;
    @Column(name="name", nullable=false)
    private String name;
    @Column(name="surname", nullable=false)
    private String surname;
    @Column(name="password", nullable=false)
    private String password;
    @Column(name="user_type",nullable = false)
    private UserType userType;


    public User() {
    }

    public User(String name, String surname, String password, UserType userType,String username) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.userType = userType;
    }

}
