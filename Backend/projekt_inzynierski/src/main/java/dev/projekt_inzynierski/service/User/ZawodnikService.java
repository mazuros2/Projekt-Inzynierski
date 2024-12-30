package dev.projekt_inzynierski.service.User;

import dev.projekt_inzynierski.DTO.*;
import dev.projekt_inzynierski.models.Kraj_pochodzenia;
import dev.projekt_inzynierski.models.Obecny_klub;
import dev.projekt_inzynierski.models.users.Zawodnik;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import dev.projekt_inzynierski.repository.User.ZawodnikRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ZawodnikService {
    private final ZawodnikRepository zawodnikRepository;

    public ZawodnikService(ZawodnikRepository zawodnikRepository) {
        this.zawodnikRepository = zawodnikRepository;
    }

    public ZawodnikByIdDTO getZawodnikInfoById(Long id){

        Zawodnik zawodnikInfo = zawodnikRepository.findById(id)
                .orElseThrow( () ->
                        new EntityNotFoundException("Nie można znaleźć zawodnika o podanym id!"));

        Obecny_klub obecny_klub = zawodnikInfo.getObecny_klub().stream()
                .filter(klub -> klub.getData_Do() == null || klub.getData_Do().isAfter(LocalDate.now()))
                .findFirst()
                .orElse(null);

        Set<String> krajePochodzenia = zawodnikInfo.getKraj_pochodzenia().stream()
                .map(Kraj_pochodzenia::getNazwa)
                .collect(Collectors.toSet());

        return new ZawodnikByIdDTO(
                zawodnikInfo.getImie(),
                zawodnikInfo.getNazwisko(),
                zawodnikInfo.getData_Urodzenia(),
                krajePochodzenia,
                zawodnikInfo.getPozycja().getNazwa_pozycji(),
                obecny_klub.getKlub().getNazwa_klubu(), // tutaj coś będzie trzeba zrobić ewentualnie żeby wyświelić brak klubu
                zawodnikInfo.getWzrost(),
                zawodnikInfo.getWaga(),
                zawodnikInfo.getProfiloweURL()
        );
    }

/*
    public List<ZawodnikDTO> findZawodnikByText(String text){
        return  zawodnikRepository.findZawodnikByText("%" + text + "%");
    }
*/
    public List<ZawodnikDTO2> getAllZawodnicy() {
        List<Zawodnik> zawodnicy = zawodnikRepository.findAll();
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
        }).sorted(Comparator.comparing(ZawodnikDTO2::getNazwisko))
        .collect(Collectors.toList());
    }
    public ZawodnikIdKlubDTO znajdzIdKlubuZawodnika(long id_zawodnik) {
        return zawodnikRepository.znajdzIdKlubuZawodnika(id_zawodnik)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono klubu dla zawodnika o ID: " + id_zawodnik));
    }



}
