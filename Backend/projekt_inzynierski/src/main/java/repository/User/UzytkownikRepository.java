package repository.User;

import models.users.Uzytkownik;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UzytkownikRepository extends JpaRepository<Uzytkownik,Long> {
}
