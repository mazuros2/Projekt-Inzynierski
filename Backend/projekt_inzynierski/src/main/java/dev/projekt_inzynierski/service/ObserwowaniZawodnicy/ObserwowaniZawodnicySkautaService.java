package dev.projekt_inzynierski.service.ObserwowaniZawodnicy;

import dev.projekt_inzynierski.models.obserwowani_zawodnicy.ObserwowaniZawodnicySkautaId;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Skauta;
import dev.projekt_inzynierski.models.users.Skaut;
import dev.projekt_inzynierski.models.users.Zawodnik;
import dev.projekt_inzynierski.repository.Obserwowani_zawodnicy.ObserwowaniZawodnicySkautaRepository;
import dev.projekt_inzynierski.repository.User.SkautRepository;
import dev.projekt_inzynierski.repository.User.ZawodnikRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ObserwowaniZawodnicySkautaService {
    private final ObserwowaniZawodnicySkautaRepository obserwowaniZawodnicySkautaRepository;
    private final ZawodnikRepository zawodnikRepository;
    private final SkautRepository skautRepository;

    public ObserwowaniZawodnicySkautaService(ObserwowaniZawodnicySkautaRepository obserwowaniZawodnicySkautaRepository, ZawodnikRepository zawodnikRepository, SkautRepository skautRepository) {
        this.obserwowaniZawodnicySkautaRepository = obserwowaniZawodnicySkautaRepository;
        this.zawodnikRepository = zawodnikRepository;
        this.skautRepository = skautRepository;
    }

    public void dodanieZawodnikaDoListyObs(int id_Skauta, int id_Zawodnika){
        Skaut skaut = skautRepository.findbyId(id_Skauta)
                .orElseThrow( () -> new EntityNotFoundException("Nie można znaleźć skauta o takim id!"));

        Zawodnik zawodnik = zawodnikRepository.findById(id_Zawodnika)
                .orElseThrow( () -> new EntityNotFoundException("Nie można znaleźć zawodnika o takim id!"));

        ObserwowaniZawodnicySkautaId id = new ObserwowaniZawodnicySkautaId();
        id.setId_Skaut(id_Skauta);
        id.setId_Zawodnik(id_Zawodnika);

        Obserwowani_Zawodnicy_Skauta listaObserwowanychZawodnikow = new Obserwowani_Zawodnicy_Skauta();
        listaObserwowanychZawodnikow.setId(id);
        listaObserwowanychZawodnikow.setSkaut(skaut);
        listaObserwowanychZawodnikow.setZawodnik(zawodnik);

        obserwowaniZawodnicySkautaRepository.save(listaObserwowanychZawodnikow);
    }

}
