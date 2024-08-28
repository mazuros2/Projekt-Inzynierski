package dev.projekt_inzynierski.repository.User;

import dev.projekt_inzynierski.models.users.Trener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrenerRepository extends JpaRepository<Trener,Long> {
}
