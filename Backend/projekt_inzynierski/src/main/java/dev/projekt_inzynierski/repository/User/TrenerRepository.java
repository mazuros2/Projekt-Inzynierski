package dev.projekt_inzynierski.repository.User;

import dev.projekt_inzynierski.models.users.Trener;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrenerRepository extends JpaRepository<Trener,Long> {
}
