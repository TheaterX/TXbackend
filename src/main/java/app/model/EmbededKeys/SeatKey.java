package app.model.EmbededKeys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Setter
@Getter
public class SeatKey implements Serializable {
    @Column(name = "row")
    private Integer row;
    @Column(name = "number")
    private Integer number;
    @Column(name = "scene_name")
    private String sceneName;

    public SeatKey(Integer row, Integer number,String sceneName) {
        this.row = row;
        this.number = number;
        this.sceneName = sceneName;
    }

    public SeatKey() {
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatKey seatKey = (SeatKey) o;
        return Objects.equals(row, seatKey.row) &&
                Objects.equals(number, seatKey.number) &&
                Objects.equals(sceneName, seatKey.sceneName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, number, sceneName);
    }
}
