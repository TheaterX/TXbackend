package app.repositories;

import app.model.Actor;
import app.model.Play;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActorRepository extends JpaRepository<Actor, String> {
}
