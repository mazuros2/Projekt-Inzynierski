package dev.projekt_inzynierski.service.Klub;

import dev.projekt_inzynierski.models.Liga;
import dev.projekt_inzynierski.repository.Klub.LigaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LigaService {
    private final LigaRepository ligaRepository;

    public LigaService(LigaRepository ligaRepository) {
        this.ligaRepository = ligaRepository;
    }

    public List<String> getAllLigaNames() {
        return ligaRepository.findAllLigaNames();
    }

}
