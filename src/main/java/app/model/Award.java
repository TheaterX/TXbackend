package app.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Award {
    @Id
    private String name;

    @OneToMany(mappedBy = "award")
    private List<Play_award> plays;

    public Award() {
    }

    public Award(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
