package dev.projekt_inzynierski.repository;

import dev.projekt_inzynierski.models.Klub;
import dev.projekt_inzynierski.models.Obecny_klub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Obecny_klubRepository extends JpaRepository<Obecny_klub,Long> {
}
