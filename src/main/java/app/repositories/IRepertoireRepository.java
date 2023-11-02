package app.repositories;

import app.model.Repertoire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepertoireRepository extends JpaRepository<Repertoire, Integer> {
}
