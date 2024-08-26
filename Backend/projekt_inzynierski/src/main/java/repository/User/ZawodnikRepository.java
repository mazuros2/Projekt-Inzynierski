package repository.User;

import models.users.Zawodnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZawodnikRepository extends JpaRepository<Zawodnik,Long> {
}
