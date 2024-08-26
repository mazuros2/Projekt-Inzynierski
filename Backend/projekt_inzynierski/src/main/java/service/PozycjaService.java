package service;

import org.springframework.stereotype.Service;
import repository.PozycjaRepository;

@Service
public class PozycjaService {
    private final PozycjaRepository pozycjaRepository;

    public PozycjaService(PozycjaRepository pozycjaRepository) {
        this.pozycjaRepository = pozycjaRepository;
    }
}
