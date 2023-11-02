package app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Scene {
    @Column(name="seatsNumber", nullable=false)
    private int numberOfSeats;
    @Id
    private String name;

    public Scene() {
    }

    public Scene(int numberOfSeats, String name) {
        this.numberOfSeats = numberOfSeats;
        this.name = name;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
