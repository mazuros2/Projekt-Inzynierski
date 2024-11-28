package dev.projekt_inzynierski.service.ObserwowaniZawodnicy;

import dev.projekt_inzynierski.models.obserwowani_zawodnicy.ObserwowaniZawodnicyMenadzeraId;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.ObserwowaniZawodnicySkautaId;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Menadzera;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Skauta;
import dev.projekt_inzynierski.models.users.Menadzer_klubu;
import dev.projekt_inzynierski.models.users.Skaut;
import dev.projekt_inzynierski.models.users.Zawodnik;
import dev.projekt_inzynierski.repository.Obserwowani_zawodnicy.ObserwowaniZawodnicyMenadzeraRepository;
import dev.projekt_inzynierski.repository.User.Menadzer_klubuRepository;
import dev.projekt_inzynierski.repository.User.ZawodnikRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ObserwowaniZawodnicyMenadzeraService {
    private final ObserwowaniZawodnicyMenadzeraRepository obserwowaniZawodnicyMenadzeraRepository;
    private final ZawodnikRepository zawodnikRepository;
    private final Menadzer_klubuRepository menadzer_klubuRepository;

    public ObserwowaniZawodnicyMenadzeraService(ObserwowaniZawodnicyMenadzeraRepository obserwowaniZawodnicyMenadzeraRepository, ZawodnikRepository zawodnikRepository, Menadzer_klubuRepository menadzer_klubuRepository) {
        this.obserwowaniZawodnicyMenadzeraRepository = obserwowaniZawodnicyMenadzeraRepository;
        this.zawodnikRepository = zawodnikRepository;
        this.menadzer_klubuRepository = menadzer_klubuRepository;
    }

    public void dodanieZawodnikaDoListyObs(int id_Menadzer, int id_Zawodnik){
        Menadzer_klubu menadzer_klubu = menadzer_klubuRepository.findById(id_Menadzer)
                .orElseThrow( () -> new EntityNotFoundException("Nie można znaleźć menadzera klubu o takim id!"));

        Zawodnik zawodnik = zawodnikRepository.findById(id_Zawodnik)
                .orElseThrow( () -> new EntityNotFoundException("Nie można znaleźć zawodnika o takim id!"));

        ObserwowaniZawodnicyMenadzeraId id = new ObserwowaniZawodnicyMenadzeraId();
        id.setId_Menadzer(id_Menadzer);
        id.setId_Zawodnik(id_Zawodnik);

        Obserwowani_Zawodnicy_Menadzera listaObserwowanychZawodnikow = new Obserwowani_Zawodnicy_Menadzera();
        listaObserwowanychZawodnikow.setId(id);
        listaObserwowanychZawodnikow.setMenadzer_klubu(menadzer_klubu);
        listaObserwowanychZawodnikow.setZawodnik(zawodnik);

        obserwowaniZawodnicyMenadzeraRepository.save(listaObserwowanychZawodnikow);
    }

    public void usunZawodnikaZListyObs(int id_Menadzer, int id_Zawodnika){
        ObserwowaniZawodnicyMenadzeraId id = new ObserwowaniZawodnicyMenadzeraId();
        id.setId_Menadzer(id_Menadzer);
        id.setId_Zawodnik(id_Zawodnika);

        if(!obserwowaniZawodnicyMenadzeraRepository.existsById(id)){
            throw new EntityNotFoundException("Nie można znaleźć zawodnika o takim id na liście obserwowanych!");
        }

        obserwowaniZawodnicyMenadzeraRepository.deleteById(id);
    }


}

