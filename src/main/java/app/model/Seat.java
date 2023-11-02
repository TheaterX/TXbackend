package app.model;

import app.model.EmbededKeys.SeatKey;
import jakarta.persistence.*;

@Entity
public class Seat {

    @EmbeddedId
    private SeatKey id;
    @ManyToOne
    @MapsId("scene_name")
    @JoinColumn(name = "scene_name")
    private Scene scene;

    public Seat() {
    }

    public Seat(SeatKey seatKey) {
        this.id = new SeatKey(seatKey.getRow(), seatKey.getNumber(), seatKey.getSceneName());
        //this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public SeatKey getSeatKey() {
        return id;
    }
    public void setSeatKey(SeatKey seatKey) {
        this.id = seatKey;
    }
}
