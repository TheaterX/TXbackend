package app.repositories;

import app.model.Role;
import app.model.EmbededKeys.RoleKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, RoleKey> {
}
