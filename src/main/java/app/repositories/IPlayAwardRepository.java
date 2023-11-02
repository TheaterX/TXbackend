package app.repositories;

import app.model.EmbededKeys.PAKEY;
import app.model.Play_award;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlayAwardRepository extends JpaRepository<Play_award, PAKEY> {
}
