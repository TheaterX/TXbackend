package app.repositories;

import app.model.EmbededKeys.SeatKey;
import app.model.Seat;
import app.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, String> {
}
