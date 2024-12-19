package dev.projekt_inzynierski.repository.Klub;

import dev.projekt_inzynierski.DTO.KlubByIdDTO;
import dev.projekt_inzynierski.DTO.KlubDTO;
import dev.projekt_inzynierski.DTO.KlubFromLigaDTO;
import dev.projekt_inzynierski.DTO.ZawodnikDTO;
import dev.projekt_inzynierski.models.Klub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KlubRepository extends JpaRepository<Klub,Long> {
    @Query("SELECT new dev.projekt_inzynierski.DTO.KlubByIdDTO(" +
            "k.id, k.nazwa_klubu, k.rok_zalozenia, k.logo_url, " +
            "k.liga.id, k.liga.nazwa_Ligi) " +
            "FROM Klub k " +
            "ORDER BY k.nazwa_klubu ASC")
    List<KlubByIdDTO> getKluby();

    @Query("SELECT new dev.projekt_inzynierski.DTO.KlubFromLigaDTO(k.id, k.nazwa_klubu,k.logo_url) FROM Klub k WHERE k.liga.id = :ligaId ORDER BY k.nazwa_klubu")
    List<KlubFromLigaDTO> findAllByLigaId(@Param("ligaId") long ligaId);

    @Query("SELECT new dev.projekt_inzynierski.DTO.KlubByIdDTO(" +
            "k.id, k.nazwa_klubu, k.rok_zalozenia, k.logo_url, " +
            "k.liga.id, k.liga.nazwa_Ligi) " +
            "FROM Klub k WHERE k.id = :id")
    KlubByIdDTO findKlubById(@Param("id") long id);

    @Query("""
    SELECT new dev.projekt_inzynierski.DTO.ZawodnikDTO(
        z.id_Uzytkownik,
        o.imie,
        o.nazwisko,
        z.pozycja.nazwa_pozycji
    )
    FROM Uzytkownik o 
    JOIN Zawodnik z ON o.id_Uzytkownik = z.id_Uzytkownik 
    JOIN Obecny_klub uk ON z.id_Uzytkownik = uk.zawodnik.id_Uzytkownik 
    JOIN Klub k ON k.id = uk.klub.id 
    WHERE uk.data_Do IS NULL 
    AND k.id = :id_klub
    ORDER BY o.nazwisko
""")
    List<ZawodnikDTO> findZawodnicyByIdKlub(@Param("id_klub") Long idKlub);


    @Query("SELECT new dev.projekt_inzynierski.DTO.KlubDTO(k.id, k.nazwa_klubu) " +
            "FROM Klub k " +
            "WHERE LOWER(k.nazwa_klubu) LIKE LOWER(:nazwaKlubu)")
    List<KlubDTO> findKlubByNazwa(@Param("nazwaKlubu") String nazwaKlubu);
}
