package app.model;

import app.model.EmbededKeys.RoleKey;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class Role {
    @EmbeddedId
    private RoleKey id;
    @Column(name="description", nullable=false)
    private String description;
    @MapsId("playId")
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="play_id",referencedColumnName = "id")
    private Play play;

    @ManyToMany(mappedBy = "roles")
    private List<Actor> actors;

    public Role() {
    }

    public Role(RoleKey roleKey, String description, Play play) {
        this.id = roleKey;
        this.description = description;
        this.play = play;
    }

    public RoleKey getId() {
        return id;
    }

    public void setId(RoleKey roleKey) {
        this.id = roleKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Play getPlay() {
        return play;
    }

    public void setPlay(Play play) {
        this.play = play;
    }
}
