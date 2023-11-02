package app.model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Actor {
    @Column(name="name", nullable=false)
    private String name;
    @Column(name="surname", nullable=false)
    private String surname;
    @Id
    private String UMCN;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "actor_interprets_role",
            joinColumns = @JoinColumn(name = "actor_UMCN", referencedColumnName = "UMCN"),
            inverseJoinColumns = {
                    @JoinColumn(name = "play_id", referencedColumnName = "play_id"),
                    @JoinColumn(name = "role_name", referencedColumnName = "name")
            })
    private List<Role> roles;

    public Actor() {
    }

    public Actor(String name, String surname, String UMCN) {
        this.name = name;
        this.surname = surname;
        this.UMCN = UMCN;
        this.roles = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUMCN() {
        return UMCN;
    }

    public void setUMCN(String UMCN) {
        this.UMCN = UMCN;
    }

    public void addRole(Role role) { roles.add(role); }
}
