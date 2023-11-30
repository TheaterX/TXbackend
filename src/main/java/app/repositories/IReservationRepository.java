package app.repositories;

import app.model.Reservation;
import app.model.Scene;
import app.model.ScheduledPlay;
import app.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public interface IReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT COUNT(s) FROM Reservation r JOIN r.seats s WHERE r.scheduledPlay = :scheduledPlay")
    int countReservedSeatsByScheduledPlay(@Param("scheduledPlay") ScheduledPlay scheduledPlay);

    @Query("SELECT r FROM Reservation r " +
            "WHERE r.scheduledPlay.id.sceneName = :scene " +
            "AND DATE(r.scheduledPlay.id.scheduledDate) = DATE(:date) " +
            "AND r.scheduledPlay.id.playId = :playId " +
            "AND r.canceled = false")
    List<Reservation> findSeatsByDateSceneAndPlayId(@Param("date") Date date, @Param("scene") String scene, @Param("playId") int playId);

    @Query("SELECT r FROM Reservation r " +
            "WHERE r.classicUser.username = :username")
    List<Reservation> getReservationsByUsername(@Param("username") String username);
}
