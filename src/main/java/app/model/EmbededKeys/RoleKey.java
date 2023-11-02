package app.model.EmbededKeys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Setter
@Getter
public class RoleKey implements Serializable {

    @Column(name = "play_id")
    private Integer playId;

    @Column(name = "name")
    private String name;

    public RoleKey(Integer playId, String name) {
        this.playId = playId;
        this.name = name;
    }

    public RoleKey() {

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RoleKey roleKey = (RoleKey) obj;
        return playId == roleKey.playId && Objects.equals(name, roleKey.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, playId);
    }
}
