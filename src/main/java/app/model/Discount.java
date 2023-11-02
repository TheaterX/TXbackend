package app.model;

import jakarta.persistence.*;
@Entity
public class Discount {
    @Id
    private String name;
    @Column(name="percentage", nullable=false)
    private int percentage;

    public Discount() {
    }

    public Discount(String name, int percentage) {
        this.name = name;
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
