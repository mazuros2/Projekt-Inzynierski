package dev.projekt_inzynierski.repository;

import dev.projekt_inzynierski.models.Pozycja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PozycjaRepository extends JpaRepository<Pozycja,Long> {
}
