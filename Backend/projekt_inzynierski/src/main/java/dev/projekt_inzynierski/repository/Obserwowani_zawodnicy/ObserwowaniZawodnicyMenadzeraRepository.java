package dev.projekt_inzynierski.repository.Obserwowani_zawodnicy;

import dev.projekt_inzynierski.DTO.ZawodnikByIdDTO;
import dev.projekt_inzynierski.models.Klub;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.ObserwowaniZawodnicyMenadzeraId;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.ObserwowaniZawodnicySkautaId;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Menadzera;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Skauta;
import dev.projekt_inzynierski.models.users.Menadzer_klubu;
import dev.projekt_inzynierski.models.users.Zawodnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ObserwowaniZawodnicyMenadzeraRepository extends JpaRepository<Obserwowani_Zawodnicy_Menadzera,Long> {
    boolean existsByZawodnikAndMenadzerKlubu(Zawodnik zawodnik, Menadzer_klubu menadzerKlubu);

    @Query("SELECT o.zawodnik FROM Obserwowani_Zawodnicy_Menadzera o WHERE o.menadzerKlubu.id_Uzytkownik = :idMenadzer ORDER BY o.zawodnik.nazwisko")
    List<Zawodnik> findAllZawodnicyByMenadzerId(@Param("idMenadzer") Long idMenadzer);

    Optional<Obserwowani_Zawodnicy_Menadzera> findByZawodnikAndMenadzerKlubu(Zawodnik zawodnik, Menadzer_klubu menadzerKlubu);
}
