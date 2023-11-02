package app.model;

import app.model.EmbededKeys.ScheduledPlayKey;
import app.model.User.AdminUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@Entity
public class ScheduledPlay {
    @EmbeddedId
    private ScheduledPlayKey id;
    @Column(name="canceled", nullable=false)
    private boolean canceled;
    @Column(name="premiere", nullable=false)
    private boolean premiere;
    @Column(name = "admin_username")
    private String adminUser;

    @Column(name = "is_guest")
    private boolean guest;

    @Column(name = "travels")
    private boolean travels;

    @MapsId("admin_username")
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_username", referencedColumnName = "username")
    private AdminUser admin;
    @MapsId("play_id")
    @ManyToOne
    @JoinColumn(name = "play_id")
    private Play play;

    @MapsId("scene_name")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "scene_name")
    private Scene scene;

    public ScheduledPlay() {
    }

    public ScheduledPlay(Date date, boolean canceled, boolean premiere, String adminUser, Play play, Scene scene) {
        this.canceled = canceled;
        this.premiere = premiere;
        this.adminUser = adminUser;
        this.play = play;
        this.scene = scene;
    }

    public ScheduledPlayKey getId() {
        return id;
    }

    public void setId(ScheduledPlayKey id) {
        this.id = id;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public boolean isPremiere() {
        return premiere;
    }

    public void setPremiere(boolean premiere) {
        this.premiere = premiere;
    }

    public String getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(String adminUser) {
        this.adminUser = adminUser;
    }

    public Play getPlay() {
        return play;
    }

    public void setPlay(Play play) {
        this.play = play;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
