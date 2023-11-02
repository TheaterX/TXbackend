package app.model;

import app.model.User.ClassicUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="canceled", nullable=false)
    private boolean canceled;
    @Column(name="paid", nullable=false)
    private boolean paid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "scheduled_play_play_id", referencedColumnName = "play_id"),
            @JoinColumn(name = "scheduled_play_scene_name", referencedColumnName = "scene_name"),
            @JoinColumn(name = "scheduled_play_scheduled_date", referencedColumnName = "scheduled_date")
    })
    private ScheduledPlay scheduledPlay;
    @Column(name = "user_username")
    private String userUsername;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @MapsId("user_username")
    @JoinColumn(name = "user_username", referencedColumnName = "username")
    private ClassicUser classicUser;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "reservation_seat",
            joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    )
    private List<Seat> seats;

    public Reservation() {
    }

    public Reservation(int id, boolean canceled, boolean paid, ScheduledPlay scheduledPlay, ClassicUser classicUser) {
        this.id = id;
        this.canceled = canceled;
        this.paid = paid;
        this.scheduledPlay = scheduledPlay;
        this.classicUser = classicUser;
    }

}
