package dev.projekt_inzynierski.service.Klub;

import dev.projekt_inzynierski.models.Trofeum;
import dev.projekt_inzynierski.repository.Klub.TrofeumRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrofeumService {
    private final TrofeumRepository trofeumRepository;

    public TrofeumService(TrofeumRepository trofeumRepository) {
        this.trofeumRepository = trofeumRepository;
    }

    public List<Trofeum> getTrofeaDlaKlubu(long klubId) {
        return trofeumRepository.findByKlubId(klubId);
    }

}
