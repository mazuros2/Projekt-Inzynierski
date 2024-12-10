package dev.projekt_inzynierski.service.ObserwowaniZawodnicy;

import dev.projekt_inzynierski.DTO.ZawodnikByIdDTO;
import dev.projekt_inzynierski.models.Kraj_pochodzenia;
import dev.projekt_inzynierski.models.Obecny_klub;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.ObserwowaniZawodnicySkautaId;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Skauta;
import dev.projekt_inzynierski.models.users.Skaut;
import dev.projekt_inzynierski.models.users.Zawodnik;
import dev.projekt_inzynierski.repository.Obserwowani_zawodnicy.ObserwowaniZawodnicySkautaRepository;
import dev.projekt_inzynierski.repository.User.SkautRepository;
import dev.projekt_inzynierski.repository.User.ZawodnikRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public void dodanieZawodnikaDoListyObs(int id_Skaut, int id_Zawodnika){
        Skaut skaut = skautRepository.findById(id_Skaut)
                .orElseThrow( () -> new EntityNotFoundException("Nie można znaleźć skauta o takim id!"));

//        Zawodnik zawodnik = zawodnikRepository.findById(id_Zawodnika);
//                .orElseThrow( () -> new EntityNotFoundException("Nie można znaleźć zawodnika o takim id!"));

        ObserwowaniZawodnicySkautaId id = new ObserwowaniZawodnicySkautaId();
        id.setId_Skaut(id_Skaut);
        id.setId_Zawodnik(id_Zawodnika);

        Obserwowani_Zawodnicy_Skauta listaObserwowanychZawodnikow = new Obserwowani_Zawodnicy_Skauta();
        listaObserwowanychZawodnikow.setId(id);
        listaObserwowanychZawodnikow.setSkaut(skaut);
//        listaObserwowanychZawodnikow.setZawodnik(zawodnik);

        obserwowaniZawodnicySkautaRepository.save(listaObserwowanychZawodnikow);
    }

    public void usunZawodnikaZListyObs(int id_Skaut, int id_Zawodnika){
        ObserwowaniZawodnicySkautaId id = new ObserwowaniZawodnicySkautaId();
        id.setId_Skaut(id_Skaut);
        id.setId_Zawodnik(id_Zawodnika);

        if(!obserwowaniZawodnicySkautaRepository.existsById(id)){
            throw new EntityNotFoundException("Nie można znaleźć zawodnika o takim id na liście obserwowanych!");
        }

        obserwowaniZawodnicySkautaRepository.deleteById(id);
    }

/*    public List<ZawodnikByIdDTO> listaObsZawodnikowSkauta(long id_skaut){
        List<Zawodnik> listaZawodnikow = obserwowaniZawodnicySkautaRepository.findZawodnicy_Skauta(id_skaut);

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

    }*/
/*
    public List<ZawodnikByIdDTO> findZawodnicySkauta(long idSkaut) {
        return obserwowaniZawodnicySkautaRepository.findZawodnicyDTOBySkautId(idSkaut);
    }*/

}
