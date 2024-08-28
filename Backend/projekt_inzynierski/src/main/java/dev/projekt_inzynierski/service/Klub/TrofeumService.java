package dev.projekt_inzynierski.service.Klub;

import dev.projekt_inzynierski.repository.Klub.TrofeumRepository;
import org.springframework.stereotype.Service;

@Service
public class TrofeumService {
    private final TrofeumRepository trofeumRepository;

    public TrofeumService(TrofeumRepository trofeumRepository) {
        this.trofeumRepository = trofeumRepository;
    }
}
