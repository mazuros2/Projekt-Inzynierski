package service.Klub;

import org.springframework.stereotype.Service;
import repository.Klub.LigaRepository;

@Service
public class LigaService {
    private final LigaRepository ligaRepository;

    public LigaService(LigaRepository ligaRepository) {
        this.ligaRepository = ligaRepository;
    }



}
