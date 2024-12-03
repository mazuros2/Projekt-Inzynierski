package dev.projekt_inzynierski.service.ObserwowaniZawodnicy;

import dev.projekt_inzynierski.DTO.ZawodnikByIdDTO;
import dev.projekt_inzynierski.models.Kraj_pochodzenia;
import dev.projekt_inzynierski.models.Obecny_klub;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

/*
    public List<ZawodnikByIdDTO> listaObsZawodnikowMenadzera(long id_Menadzer){
        List<Zawodnik> listaZawodnikow = obserwowaniZawodnicyMenadzeraRepository.findZawodnicy_Menadzera(id_Menadzer);

        return listaZawodnikow.stream()
                .map( zawodnik -> {

                    Obecny_klub obecny_klub = zawodnik.getObecny_klub().stream()
                            .filter(klub -> klub.getData_Do() == null || klub.getData_Do().isAfter(LocalDate.now()))
                            .findFirst()
                            .orElse(null);

                    Set<String> krajePochodzenia = zawodnik.getKraj_pochodzenia().stream()
                            .map(Kraj_pochodzenia::getNazwa)
                            .collect(Collectors.toSet());

                    return new ZawodnikByIdDTO(
                            zawodnik.getImie(),
                            zawodnik.getNazwisko(),
                            zawodnik.getData_Urodzenia(),
                            krajePochodzenia,
                            zawodnik.getPozycja().getNazwa_pozycji(),
                            obecny_klub.getKlub().getNazwa_klubu(),
                            zawodnik.getWzrost(),
                            zawodnik.getWaga()
                    );
                }).collect(Collectors.toList());
    }
*/

}

