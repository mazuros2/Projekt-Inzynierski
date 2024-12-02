package dev.projekt_inzynierski.repository.Obserwowani_zawodnicy;

import dev.projekt_inzynierski.models.Klub;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.ObserwowaniZawodnicySkautaId;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Skauta;
import dev.projekt_inzynierski.models.users.Zawodnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ObserwowaniZawodnicySkautaRepository extends JpaRepository<Obserwowani_Zawodnicy_Skauta,Long> {
    boolean existsById(ObserwowaniZawodnicySkautaId id);
    void deleteById(ObserwowaniZawodnicySkautaId id);

    List<Zawodnik> findZawodnicy_Skauta(long idSkaut);

}
