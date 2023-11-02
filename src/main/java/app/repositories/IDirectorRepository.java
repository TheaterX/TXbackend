package app.repositories;

import app.model.Director;
import app.model.Scene;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDirectorRepository extends JpaRepository<Director, String> {

}
