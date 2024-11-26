package dev.projekt_inzynierski.service.User;

import dev.projekt_inzynierski.DTO.SkautDTO;
import dev.projekt_inzynierski.models.Kraj_pochodzenia;
import dev.projekt_inzynierski.models.users.Skaut;
import dev.projekt_inzynierski.repository.User.SkautRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SkautService {
    private final SkautRepository skautRepository;

    public SkautService(SkautRepository skautRepository) {
        this.skautRepository = skautRepository;
    }


    public SkautDTO getSkautInfoById(long id){
        Skaut skaut = skautRepository.findById(id)
                .orElseThrow( () ->
                        new EntityNotFoundException("Nie można znaleźć skauta o takim id!"));

        String klub = skaut.getSkautKlubu().getNazwa_klubu();

        Set<String> krajePochodzenia = skaut.getKraj_pochodzenia().stream()
                .map(Kraj_pochodzenia::getNazwa)
                .collect(Collectors.toSet());

        return new SkautDTO(
             skaut.getImie(),
             skaut.getNazwisko(),
             skaut.getData_Urodzenia(),
             krajePochodzenia,
             klub
        );
    }
}
