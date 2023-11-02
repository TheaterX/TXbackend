package app.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Director {
    @Column(name="name", nullable=false)
    private String name;
    @Column(name="surname", nullable=false)
    private String surname;
    @Id
    private String UMCN;
    @Column(name="title", nullable=false)
    private String title;
    @ManyToMany(mappedBy = "directors")
    private List<Play> plays;

    public Director() {
    }

    public Director(String name, String surname, String UMCN,String title) {
        this.name = name;
        this.surname = surname;
        this.UMCN = UMCN;
        this.title = title;
    }
    public void addPlay(Play play){
        plays.add(play);
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
