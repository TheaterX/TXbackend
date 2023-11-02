package app.repositories;

import app.model.Play;
import app.model.Repertoire;
import app.model.Scene;
import app.model.ScheduledPlay;
import app.model.EmbededKeys.ScheduledPlayKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IScheduledPlayRepository extends JpaRepository<ScheduledPlay, ScheduledPlayKey> {
    @Query("SELECT p FROM Play p " +
            "WHERE NOT EXISTS " +
            "(SELECT sp FROM ScheduledPlay sp " +
            " WHERE sp.play = p AND DATE(sp.id.scheduledDate) = DATE(:date)) " +
            "AND :repertoire member of p.repertoires")
    List<Play> findPlaysNotScheduledForDateAndRepertoire(
            @Param("date") Date date,
            @Param("repertoire") Repertoire repertoire
    );
    @Query("SELECT CASE WHEN COUNT(sp) > 0 THEN true ELSE false END " +
            "FROM ScheduledPlay sp " +
            "JOIN sp.play.roles roles " +
            "JOIN roles.actors actor " +
            "WHERE actor.UMCN = :actorUMCN " +
            "AND DATE(sp.id.scheduledDate) = DATE(:date)")
    boolean isActorBusyOnDate(@Param("actorUMCN") String actorUMCN, @Param("date") Date date);
    @Query("SELECT DISTINCT sp.id.scheduledDate FROM ScheduledPlay sp " +
            "WHERE sp.play.id = :playId " +
            "AND sp.id.scheduledDate BETWEEN :startDate AND :endDate ")
    List<Date> findDatesWherePlayIsNotScheduled(@Param("playId") int playId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT sp.id.scheduledDate " +
            "FROM ScheduledPlay sp " +
            "WHERE sp.id.sceneName = :scene")
    List<Date> findFreeDatesForScene(@Param("scene") String scene);

    @Query("SELECT s " +
            "FROM Scene s " +
            "WHERE s.name NOT IN " +
            "(SELECT sp.scene.name " +
            " FROM ScheduledPlay sp " +
            " WHERE DATE(sp.id.scheduledDate) = DATE(:date))")
    List<Scene> findFreeScenesForDate(@Param("date") Date date);

}
