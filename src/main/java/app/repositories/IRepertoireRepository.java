package app.repositories;

import app.model.Repertoire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRepertoireRepository extends JpaRepository<Repertoire, Integer> {
    @Query("SELECT r.year FROM Repertoire r")
    List<Integer> getAllYearsAsIntegers();
}
