package app.repositories;

import app.model.Play;
import app.model.Scene;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISceneRepository extends JpaRepository<Scene, String> {

}
