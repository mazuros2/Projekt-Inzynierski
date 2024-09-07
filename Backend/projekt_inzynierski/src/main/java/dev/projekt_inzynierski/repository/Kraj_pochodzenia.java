package dev.projekt_inzynierski.repository;

import dev.projekt_inzynierski.models.Klub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Kraj_pochodzenia extends JpaRepository<dev.projekt_inzynierski.models.Kraj_pochodzenia,Long> {
}
