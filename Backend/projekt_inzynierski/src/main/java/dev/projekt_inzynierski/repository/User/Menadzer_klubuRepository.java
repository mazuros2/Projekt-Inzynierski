package dev.projekt_inzynierski.repository.User;

import dev.projekt_inzynierski.models.users.Menadzer_klubu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Menadzer_klubuRepository extends JpaRepository<Menadzer_klubu,Long> {
    Optional<Menadzer_klubu> findById(long id);
}
