package dev.projekt_inzynierski.repository.User;

import dev.projekt_inzynierski.models.users.Trener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrenerRepository extends JpaRepository<Trener,Long> {

    Optional<Trener> findById(long id);

}
