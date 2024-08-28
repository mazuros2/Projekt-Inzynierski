package dev.projekt_inzynierski.service;

import dev.projekt_inzynierski.repository.PozycjaRepository;
import org.springframework.stereotype.Service;

@Service
public class PozycjaService {
    private final PozycjaRepository pozycjaRepository;

    public PozycjaService(PozycjaRepository pozycjaRepository) {
        this.pozycjaRepository = pozycjaRepository;
    }
}
