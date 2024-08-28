package dev.projekt_inzynierski.service.Klub;

import dev.projekt_inzynierski.repository.Klub.KlubRepository;
import org.springframework.stereotype.Service;

@Service
public class KlubService {
    private final KlubRepository klubRepository;

    public KlubService(KlubRepository klubRepository) {
        this.klubRepository = klubRepository;
    }



}
