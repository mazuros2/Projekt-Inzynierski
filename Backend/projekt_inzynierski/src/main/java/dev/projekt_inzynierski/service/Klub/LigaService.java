package dev.projekt_inzynierski.service.Klub;

import dev.projekt_inzynierski.repository.Klub.LigaRepository;
import org.springframework.stereotype.Service;

@Service
public class LigaService {
    private final LigaRepository ligaRepository;

    public LigaService(LigaRepository ligaRepository) {
        this.ligaRepository = ligaRepository;
    }



}
