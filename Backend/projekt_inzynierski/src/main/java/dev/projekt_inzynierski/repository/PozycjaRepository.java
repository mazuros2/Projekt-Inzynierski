package dev.projekt_inzynierski.repository;

import dev.projekt_inzynierski.models.Pozycja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PozycjaRepository extends JpaRepository<Pozycja,Long> {
}
