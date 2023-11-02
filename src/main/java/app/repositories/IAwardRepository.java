package app.repositories;

import app.model.Award;
import app.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAwardRepository extends JpaRepository<Award, String> {
}
