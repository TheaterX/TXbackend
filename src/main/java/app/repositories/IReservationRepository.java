package app.repositories;

import app.model.Reservation;
import app.model.Scene;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservationRepository extends JpaRepository<Reservation, Integer> {

}
