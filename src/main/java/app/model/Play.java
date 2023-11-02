package app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Play {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(name="name", nullable=false)
    private String name;
    @Column(name="grade", nullable=false)
    private float grade;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "scene", referencedColumnName = "name")
    private Scene scene;

    @OneToMany(mappedBy = "play")
    private List<Play_award> awards;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "play_ganre",
            joinColumns = @JoinColumn(name = "playId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ganreName", referencedColumnName = "name"))
    private List<Ganre> ganres;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "director_play",
            joinColumns = @JoinColumn(name = "playId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "directorUMCN", referencedColumnName = "UMCN"))
    private List<Director> directors;

    @OneToMany(mappedBy = "play")
    private List<Role> roles;

    @ManyToMany(mappedBy = "plays")
    private List<Repertoire> repertoires;
    public Play() {
        this.ganres = new ArrayList<>();
        this.directors = new ArrayList<>();
    }

    public Play( String name, float grade, Scene scene) {
        this.name = name;
        this.grade = grade;
        this.scene = scene;
        this.ganres = new ArrayList<>();
        this.directors = new ArrayList<>();
    }

    public void addDirector(Director director){
        directors.add(director);
    }
    public void addGanre(Ganre ganre){
        ganres.add(ganre);
    }
}
