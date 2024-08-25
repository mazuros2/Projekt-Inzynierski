package service;

import org.springframework.stereotype.Service;
import repository.LigaRepository;

@Service
public class LigaService {
    private final LigaRepository ligaRepository;

    public LigaService(LigaRepository ligaRepository) {
        this.ligaRepository = ligaRepository;
    }



}
