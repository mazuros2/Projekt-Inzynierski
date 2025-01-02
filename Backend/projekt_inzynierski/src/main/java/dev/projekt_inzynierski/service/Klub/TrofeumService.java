package dev.projekt_inzynierski.service.Klub;

import dev.projekt_inzynierski.DTO.Create.CreateTrofeumDTO;
import dev.projekt_inzynierski.DTO.TrofeumDTO;
import dev.projekt_inzynierski.DTO.TrofeumNazwaKlubuDTO;
import dev.projekt_inzynierski.models.Klub;
import dev.projekt_inzynierski.models.Trofeum;
import dev.projekt_inzynierski.repository.Klub.KlubRepository;
import dev.projekt_inzynierski.repository.Klub.TrofeumRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrofeumService {
    private final TrofeumRepository trofeumRepository;
    private final KlubRepository klubRepository;

    public TrofeumService(TrofeumRepository trofeumRepository, KlubRepository klubRepository) {
        this.trofeumRepository = trofeumRepository;
        this.klubRepository = klubRepository;
    }

    public List<TrofeumDTO> getTrofeaDlaKlubu(long klubId) {
        return trofeumRepository.findAllByKlubId(klubId);
    }
    public List<TrofeumNazwaKlubuDTO> getMistrzPolski() {
        return trofeumRepository.findMistrzPolski();
    }

    public List<TrofeumNazwaKlubuDTO> getPucharPolski() {
        return trofeumRepository.findPucharPolski();
    }

    public List<TrofeumNazwaKlubuDTO> getLastFiveMistrzPolski() {
        Pageable pageable = PageRequest.of(0, 5);
        return trofeumRepository.getLastFiveMistrzPolski(pageable);
    }

    public List<TrofeumNazwaKlubuDTO> getLastFivePucharPolski() {
        Pageable pageable = PageRequest.of(0, 5);
        return trofeumRepository.getLastFivePucharPolski(pageable);
    }

    public void createTrofeum(CreateTrofeumDTO request){
        Klub klub = klubRepository.findById(request.getKlubId())
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono klubu o takim id!"));

        Trofeum trofeum = Trofeum.builder()
                .nazwa(request.getNazwa())
                .data_zdobycia(request.getDataZdobycia())
                .klub(klub)
                .build();

        trofeumRepository.save(trofeum);
    }
}
