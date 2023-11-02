package app.model;

import app.model.EmbededKeys.PAKEY;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "play_award")
public class Play_award {
    @EmbeddedId
    private PAKEY id;
    @ManyToOne
    @MapsId("play_id")
    @JoinColumn(name="play_id")
    private Play play;
    @ManyToOne
    @MapsId("award_name")
    @JoinColumn(name="award_name")
    private Award award;
    private Date date;
    private String place;

    public Play_award(PAKEY id, Date date, String place) {
        this.id = id;
        this.date = date;
        this.place = place;
    }

    public Play_award() {

    }
}