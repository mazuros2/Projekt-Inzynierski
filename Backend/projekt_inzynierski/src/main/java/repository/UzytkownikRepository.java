package repository;

import models.users.Uzytkownik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UzytkownikRepository extends JpaRepository<Uzytkownik,Long> {
}
