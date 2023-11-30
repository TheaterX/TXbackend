package app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Repertoire {
    @Id
    private int year;
    @Column(name="name", nullable=false)
    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "plays_on_repertoire",
            joinColumns = @JoinColumn(name = "repertoireId", referencedColumnName = "year"),
            inverseJoinColumns = @JoinColumn(name = "playId", referencedColumnName = "id"))
    private List<Play> plays;

    public Repertoire() {
    }

    public Repertoire(int year, String name) {
        this.year = year;
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlays(List<Play> plays) {
        this.plays = plays;
    }
}
