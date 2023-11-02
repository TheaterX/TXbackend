package app.model.User;

import app.model.Discount;
import app.model.Ganre;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class ClassicUser extends User{
    @Temporal(TemporalType.DATE)
    @Column(name="birthDate", nullable=true)
    private Date birthDate;
    @Column(name="classicUserType", nullable=true)
    private ClassicUserType classicUserType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_dicount",
            joinColumns = @JoinColumn(name = "Username", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "discountName", referencedColumnName = "name"))
    private List<Discount> discounts;

    public ClassicUser() {
    }

    public ClassicUser(String name, String surname, String password, UserType userType, Date birthDate, ClassicUserType classicUserType, String username) {
        super(name, surname, password, userType,username);
        this.birthDate = birthDate;
        this.classicUserType = classicUserType;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public ClassicUserType getClassicUserType() {
        return classicUserType;
    }

    public void setClassicUserType(ClassicUserType classicUserType) {
        this.classicUserType = classicUserType;
    }

}
