package app.repositories;

import app.model.Director;
import app.model.User.ClassicUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClassicUserRepository extends JpaRepository<ClassicUser, String> {

}
