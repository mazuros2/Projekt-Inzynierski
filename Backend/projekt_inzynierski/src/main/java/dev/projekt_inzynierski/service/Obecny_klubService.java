package dev.projekt_inzynierski.service;

import dev.projekt_inzynierski.repository.Obecny_klubRepository;
import org.springframework.stereotype.Service;

@Service
public class Obecny_klubService {
    private final Obecny_klubRepository obecny_klubRepository;
    public Obecny_klubService(Obecny_klubRepository obecny_klubRepository) {
        this.obecny_klubRepository = obecny_klubRepository;
    }
}
