package dev.projekt_inzynierski.service.User;

import dev.projekt_inzynierski.DTO.MenadzerDTO;
import dev.projekt_inzynierski.DTO.SkautDTO;
import dev.projekt_inzynierski.models.Kraj_pochodzenia;
import dev.projekt_inzynierski.models.users.Menadzer_klubu;
import dev.projekt_inzynierski.models.users.Skaut;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import dev.projekt_inzynierski.repository.User.Menadzer_klubuRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class Menadzer_klubuService {
    private final Menadzer_klubuRepository menadzer_klubuRepository;

    public Menadzer_klubuService(Menadzer_klubuRepository menadzer_klubuRepository) {
        this.menadzer_klubuRepository = menadzer_klubuRepository;
    }

    public MenadzerDTO getMenadzerInfoById(long id){
        Menadzer_klubu menadzer = menadzer_klubuRepository.findById(id)
                .orElseThrow( () ->
                        new EntityNotFoundException("Nie można znaleźć Menadzera o takim id!"));

        String klub = menadzer.getMenadzerKlubu().getNazwa_klubu();

        Set<String> krajePochodzenia = menadzer.getKraj_pochodzenia().stream()
                .map(Kraj_pochodzenia::getNazwa)
                .collect(Collectors.toSet());

        return new MenadzerDTO(
                menadzer.getImie(),
                menadzer.getNazwisko(),
                menadzer.getData_Urodzenia(),
                krajePochodzenia,
                klub
        );
    }

}
