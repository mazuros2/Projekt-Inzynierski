package dev.projekt_inzynierski.service.Obserwowani_Zawodnicy;

import dev.projekt_inzynierski.DTO.KrajPochodzeniaDTO;
import dev.projekt_inzynierski.DTO.PozycjaDTO;
import dev.projekt_inzynierski.DTO.ZawodnikDTO2;
import dev.projekt_inzynierski.models.Obecny_klub;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.ObserwowaniZawodnicySkautaId;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Menadzera;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Skauta;
import dev.projekt_inzynierski.models.users.Menadzer_klubu;
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

/*    public void dodanieZawodnikaDoListyObs(int id_Skaut, int id_Zawodnika){
        Skaut skaut = skautRepository.findById(id_Skaut)
                .orElseThrow( () -> new EntityNotFoundException("Nie można znaleźć skauta o takim id!"));

        Zawodnik zawodnik = zawodnikRepository.findById(id_Zawodnika)
                .orElseThrow( () -> new EntityNotFoundException("Nie można znaleźć zawodnika o takim id!"));

        ObserwowaniZawodnicySkautaId id = new ObserwowaniZawodnicySkautaId();
        id.setId_Skaut(id_Skaut);
        id.setId_Zawodnik(id_Zawodnika);

        Obserwowani_Zawodnicy_Skauta listaObserwowanychZawodnikow = new Obserwowani_Zawodnicy_Skauta();
        listaObserwowanychZawodnikow.setId(id);
        listaObserwowanychZawodnikow.setSkaut(skaut);
        listaObserwowanychZawodnikow.setZawodnik(zawodnik);

        obserwowaniZawodnicySkautaRepository.save(listaObserwowanychZawodnikow);
    }*/

/*    public void usunZawodnikaZListyObs(int id_Skaut, int id_Zawodnika){
        ObserwowaniZawodnicySkautaId id = new ObserwowaniZawodnicySkautaId();
        id.setId_Skaut(id_Skaut);
        id.setId_Zawodnik(id_Zawodnika);

        if(!obserwowaniZawodnicySkautaRepository.existsById(id)){
            throw new EntityNotFoundException("Nie można znaleźć zawodnika o takim id na liście obserwowanych!");
        }

        obserwowaniZawodnicySkautaRepository.deleteById(id);
    }*/

    public void dodanieZawodnikaDoListyObs(Long id_Skaut, Long id_Zawodnik) {
        Skaut skaut = skautRepository.findById(id_Skaut)
                .orElseThrow(() -> new EntityNotFoundException("Nie można znaleźć skauta klubu o takim id!"));

        Zawodnik zawodnik = zawodnikRepository.findById(id_Zawodnik)
                .orElseThrow(() -> new EntityNotFoundException("Nie można znaleźć zawodnika o takim id!"));

        boolean alreadyObserved = obserwowaniZawodnicySkautaRepository.existsByZawodnikAndSkaut(zawodnik, skaut);
        if (alreadyObserved) {
            throw new RuntimeException("Ten zawodnik już jest na liście obserwowanych!");
        }

        Obserwowani_Zawodnicy_Skauta obserwacja = new Obserwowani_Zawodnicy_Skauta();
        obserwacja.setZawodnik(zawodnik);
        obserwacja.setSkaut(skaut);
        obserwowaniZawodnicySkautaRepository.save(obserwacja);
    }

    public List<ZawodnikDTO2> GetListaObserwowanychZawodnikow(Long id_Skaut) {
        List<Zawodnik> zawodnicy = obserwowaniZawodnicySkautaRepository.findAllZawodnicyBySkautId(id_Skaut);

        return zawodnicy.stream().map(zawodnik -> {
            Obecny_klub obecnyKlub = zawodnik.getObecny_klub().stream()
                    .filter(klub -> klub.getData_Do() == null || klub.getData_Do().isAfter(LocalDate.now()))
                    .findFirst()
                    .orElse(null);

            PozycjaDTO pozycjaDTO = new PozycjaDTO(
                    zawodnik.getPozycja().getId_Pozycja(),
                    zawodnik.getPozycja().getNazwa_pozycji(),
                    zawodnik.getPozycja().getObszar_pozycji()
            );

            Set<KrajPochodzeniaDTO> krajePochodzeniaDTO = zawodnik.getKraj_pochodzenia().stream()
                    .map(kraj -> new KrajPochodzeniaDTO(
                            kraj.getId_Kraj(),
                            kraj.getNazwa(),
                            kraj.getRegion()
                    )).collect(Collectors.toSet());

            return new ZawodnikDTO2(
                    zawodnik.getId_Uzytkownik(),
                    zawodnik.getImie(),
                    zawodnik.getNazwisko(),
                    krajePochodzeniaDTO,
                    pozycjaDTO,
                    obecnyKlub.getKlub().getNazwa_klubu()
            );
        }).collect(Collectors.toList());
    }
}
