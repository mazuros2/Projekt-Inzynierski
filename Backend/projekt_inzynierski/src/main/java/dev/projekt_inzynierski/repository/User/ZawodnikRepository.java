package dev.projekt_inzynierski.repository.User;

import dev.projekt_inzynierski.models.users.Zawodnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZawodnikRepository extends JpaRepository<Zawodnik,Long> {
}
