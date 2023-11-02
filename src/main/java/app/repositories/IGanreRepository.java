package app.repositories;

import app.model.Ganre;
import app.model.Scene;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGanreRepository extends JpaRepository<Ganre, String> {

}
