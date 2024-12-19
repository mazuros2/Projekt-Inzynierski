package dev.projekt_inzynierski.repository.Obserwowani_zawodnicy;

import dev.projekt_inzynierski.DTO.ZawodnikByIdDTO;
import dev.projekt_inzynierski.models.Klub;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.ObserwowaniZawodnicySkautaId;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Menadzera;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Skauta;
import dev.projekt_inzynierski.models.users.Menadzer_klubu;
import dev.projekt_inzynierski.models.users.Skaut;
import dev.projekt_inzynierski.models.users.Zawodnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ObserwowaniZawodnicySkautaRepository extends JpaRepository<Obserwowani_Zawodnicy_Skauta,Long> {

    boolean existsByZawodnikAndSkaut(Zawodnik zawodnik, Skaut skaut);

    @Query("SELECT o.zawodnik FROM Obserwowani_Zawodnicy_Skauta o WHERE o.skaut.id_Uzytkownik = :idSkaut ORDER BY o.zawodnik.nazwisko")
    List<Zawodnik> findAllZawodnicyBySkautId(@Param("idSkaut") Long idSkaut);

    Optional<Obserwowani_Zawodnicy_Skauta> findByZawodnikAndSkaut(Zawodnik zawodnik, Skaut skaut);

}