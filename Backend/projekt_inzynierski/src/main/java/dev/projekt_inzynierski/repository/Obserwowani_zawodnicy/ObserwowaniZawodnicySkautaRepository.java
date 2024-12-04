package dev.projekt_inzynierski.repository.Obserwowani_zawodnicy;

import dev.projekt_inzynierski.DTO.ZawodnikByIdDTO;
import dev.projekt_inzynierski.models.Klub;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.ObserwowaniZawodnicySkautaId;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Skauta;
import dev.projekt_inzynierski.models.users.Zawodnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ObserwowaniZawodnicySkautaRepository extends JpaRepository<Obserwowani_Zawodnicy_Skauta,Long> {
    boolean existsById(ObserwowaniZawodnicySkautaId id);
    void deleteById(ObserwowaniZawodnicySkautaId id);

/*   @Query("SELECT new dev.projekt_inzynierski.DTO.ZawodnikByIdDTO(" +
           "z.imie, z.nazwisko, z.data_Urodzenia, " +
           "z.kraj_pochodzenia, p.nazwa_pozycji, k.nazwa, " +
           "z.wzrost, z.waga) " +
           "FROM Obserwowani_Zawodnicy_Skauta o " +
           "JOIN o.zawodnik z " +
           "JOIN z.pozycja p " +
           "LEFT JOIN z.obecny_klub k " +
           "WHERE o.id.id_Skaut = :idSkaut")
   List<ZawodnikByIdDTO> findZawodnicyDTOBySkautId(@Param("idSkaut") long idSkaut);*/
}
