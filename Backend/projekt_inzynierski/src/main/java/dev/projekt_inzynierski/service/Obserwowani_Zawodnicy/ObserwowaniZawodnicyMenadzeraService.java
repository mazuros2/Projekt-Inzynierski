package dev.projekt_inzynierski.service.Obserwowani_Zawodnicy;

import dev.projekt_inzynierski.DTO.KrajPochodzeniaDTO;
import dev.projekt_inzynierski.DTO.PozycjaDTO;
import dev.projekt_inzynierski.DTO.ZawodnikDTO2;
import dev.projekt_inzynierski.models.Obecny_klub;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Menadzera;
import dev.projekt_inzynierski.models.users.Menadzer_klubu;
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

    public void dodanieZawodnikaDoListyObs(Long id_Menadzer, Long id_Zawodnik) {
        Menadzer_klubu menadzer_klubu = menadzer_klubuRepository.findById(id_Menadzer)
                .orElseThrow(() -> new EntityNotFoundException("Nie można znaleźć menadżera klubu o takim id!"));

        Zawodnik zawodnik = zawodnikRepository.findById(id_Zawodnik)
                .orElseThrow(() -> new EntityNotFoundException("Nie można znaleźć zawodnika o takim id!"));

        boolean alreadyObserved = obserwowaniZawodnicyMenadzeraRepository.existsByZawodnikAndMenadzerKlubu(zawodnik, menadzer_klubu);
        if (alreadyObserved) {
            throw new RuntimeException("Ten zawodnik już jest na liście obserwowanych!");
        }

        Obserwowani_Zawodnicy_Menadzera obserwacja = new Obserwowani_Zawodnicy_Menadzera();
        obserwacja.setZawodnik(zawodnik);
        obserwacja.setMenadzerKlubu(menadzer_klubu);
        obserwowaniZawodnicyMenadzeraRepository.save(obserwacja);
    }

    public List<ZawodnikDTO2> GetListaObserwowanychZawodnikow(Long idMenadzer) {
        List<Zawodnik> zawodnicy = obserwowaniZawodnicyMenadzeraRepository.findAllZawodnicyByMenadzerId(idMenadzer);

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