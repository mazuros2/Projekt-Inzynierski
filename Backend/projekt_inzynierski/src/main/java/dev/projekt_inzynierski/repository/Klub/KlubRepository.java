package dev.projekt_inzynierski.repository.Klub;

import dev.projekt_inzynierski.DTO.KlubByIdDTO;
import dev.projekt_inzynierski.DTO.KlubFromLigaDTO;
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

    @Query("SELECT new dev.projekt_inzynierski.DTO.KlubFromLigaDTO(k.id, k.nazwa_klubu) FROM Klub k WHERE k.liga.id = :ligaId")
    List<KlubFromLigaDTO> findAllByLigaId(@Param("ligaId") long ligaId);

    @Query("SELECT new dev.projekt_inzynierski.DTO.KlubByIdDTO(" +
            "k.id, k.nazwa_klubu, k.rok_zalozenia, " +
            "k.liga.id, k.liga.nazwa_Ligi) " +
            "FROM Klub k WHERE k.id = :id")
    KlubByIdDTO findKlubById(@Param("id") long id);

}
