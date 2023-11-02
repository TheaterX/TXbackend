package app.model;

import jakarta.persistence.*;
@Entity
public class Ganre {
    @Id
    private String name;

    public Ganre(String name) {
        this.name = name;
    }

    public Ganre() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
