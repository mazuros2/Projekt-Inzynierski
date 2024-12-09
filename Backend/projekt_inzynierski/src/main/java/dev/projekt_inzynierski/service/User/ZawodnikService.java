package dev.projekt_inzynierski.service.User;

import dev.projekt_inzynierski.DTO.KlubByIdDTO;
import dev.projekt_inzynierski.DTO.ZawodnikByIdDTO;
import dev.projekt_inzynierski.DTO.ZawodnikDTO;
import dev.projekt_inzynierski.models.Kraj_pochodzenia;
import dev.projekt_inzynierski.models.Obecny_klub;
import dev.projekt_inzynierski.models.users.Zawodnik;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import dev.projekt_inzynierski.repository.User.ZawodnikRepository;

import java.time.LocalDate;
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
                zawodnikInfo.getWaga()
        );
    }

    public List<ZawodnikDTO> findZawodnikByText(String text){
        return  zawodnikRepository.findZawodnikByText("%" + text + "%");
    }

}
