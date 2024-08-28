package dev.projekt_inzynierski.repository.Klub;

import dev.projekt_inzynierski.models.Klub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KlubRepository extends JpaRepository<Klub,Long> {
    @Query("SELECT k.id,k.nazwa_klubu,l.nazwa_Ligi,k.rok_zalozenia as id FROM Klub k JOIN Liga l on k.id_Ligii= l.id")
    List<Klub> getKluby();
}
