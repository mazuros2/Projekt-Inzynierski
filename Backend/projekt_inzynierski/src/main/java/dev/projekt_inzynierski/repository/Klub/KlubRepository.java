package dev.projekt_inzynierski.repository.Klub;

import dev.projekt_inzynierski.models.Klub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KlubRepository extends JpaRepository<Klub,Long> {
    @Query("SELECT k.id,k.nazwa_klubu,l.nazwa_Ligi,k.rok_zalozenia as id FROM Klub k JOIN Liga l on k.liga.id = l.id")
    List<Klub> getKluby();

    @Query("SELECT k FROM Klub k WHERE k.liga.id = :ligaId")
    List<Klub> findAllByLigaId(@Param("ligaId") long ligaId);

    @Query("SELECT k FROM Klub k WHERE k.id = :id")
    Klub findKlubById(@Param("id") long id);

}
