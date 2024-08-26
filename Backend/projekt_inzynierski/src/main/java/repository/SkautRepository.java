package repository;

import models.users.Skaut;
import models.users.Uzytkownik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkautRepository extends JpaRepository<Skaut,Long> {
}
