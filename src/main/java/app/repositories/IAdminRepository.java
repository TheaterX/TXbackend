package app.repositories;

import app.model.User.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository extends JpaRepository<AdminUser, String> {

}
