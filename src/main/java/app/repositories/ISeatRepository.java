package app.repositories;

import app.model.EmbededKeys.SeatKey;
import app.model.Play;
import app.model.Repertoire;
import app.model.Scene;
import app.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ISeatRepository extends JpaRepository<Seat, SeatKey> {

    @Query("SELECT s FROM Seat s " +
            "WHERE :sceneName = s.scene.name")
    List<Seat> findByScene(@Param("sceneName")String sceneName);
    @Modifying
    @Query(value = "DELETE FROM seat WHERE scene_name = :sceneName", nativeQuery = true)
    int deleteSeatsBySceneName(@Param("sceneName") String sceneName);
}
