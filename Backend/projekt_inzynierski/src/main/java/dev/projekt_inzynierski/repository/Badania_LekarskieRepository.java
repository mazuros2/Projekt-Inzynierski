package dev.projekt_inzynierski.repository;

import dev.projekt_inzynierski.models.Badania_lekarskie;
import dev.projekt_inzynierski.models.Klub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Badania_LekarskieRepository extends JpaRepository<Badania_lekarskie,Long> {
}
