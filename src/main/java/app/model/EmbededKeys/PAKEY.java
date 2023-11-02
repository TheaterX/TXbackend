package app.model.EmbededKeys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Embeddable
public class PAKEY implements Serializable {
    @Column(name="play_id")
    private Integer play;
    @Column(name="award_name")
    private String award;

    public PAKEY(Integer play, String award) {
        this.play = play;
        this.award = award;
    }

    public PAKEY() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PAKEY pakey = (PAKEY) o;
        return Objects.equals(play, pakey.play) &&
                Objects.equals(award, pakey.award);
    }

    @Override
    public int hashCode() {
        return Objects.hash(play, award);
    }

    // Optional: Implementing a toString() method
    @Override
    public String toString() {
        return "PAKEY{" +
                "play=" + play +
                ", award='" + award + '\'' +
                '}';
    }
}
