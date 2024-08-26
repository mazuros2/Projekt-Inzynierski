package repository;

import models.Pozycja;
import models.users.Uzytkownik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PozycjaRepository extends JpaRepository<Pozycja,Long> {
}
