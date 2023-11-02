package app.model.EmbededKeys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;
@Getter
@Setter
@Embeddable
public class ScheduledPlayKey implements Serializable {
    @Column(name = "play_id")
    private Integer playId;

    @Column(name = "scene_name")
    private String sceneName;

    // Constructors, getters, and setters
    @Column(name = "scheduled_date")
    private Date scheduledDate;

    // Constructors, getters, and setters


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduledPlayKey that = (ScheduledPlayKey) o;
        return Objects.equals(playId, that.playId) &&
                Objects.equals(sceneName, that.sceneName) &&
                Objects.equals(scheduledDate, that.scheduledDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playId, sceneName, scheduledDate);
    }
}