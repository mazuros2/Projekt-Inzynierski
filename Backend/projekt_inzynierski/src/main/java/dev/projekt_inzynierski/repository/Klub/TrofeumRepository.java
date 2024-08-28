package dev.projekt_inzynierski.repository.Klub;

import dev.projekt_inzynierski.models.Trofeum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrofeumRepository extends JpaRepository<Trofeum,Long> {
}
