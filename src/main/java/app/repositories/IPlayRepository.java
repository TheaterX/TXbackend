package app.repositories;

import app.model.Play;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IPlayRepository extends JpaRepository<Play, Integer> {
    @Query("SELECT MAX(p.id) FROM Play p")
    Optional<Integer> findMaxId();
}
