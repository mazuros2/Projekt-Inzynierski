package dev.projekt_inzynierski.repository.User;

import dev.projekt_inzynierski.models.users.Skaut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkautRepository extends JpaRepository<Skaut,Long> {

    Optional<Skaut> findbyId(long id);
}
