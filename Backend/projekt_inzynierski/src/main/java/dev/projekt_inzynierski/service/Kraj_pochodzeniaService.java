package dev.projekt_inzynierski.service;

import dev.projekt_inzynierski.repository.Kraj_pochodzeniaRepository;
import org.springframework.stereotype.Service;

@Service
public class Kraj_pochodzeniaService {
    private Kraj_pochodzeniaRepository kraj_pochodzeniaRepository;
    public Kraj_pochodzeniaService(Kraj_pochodzeniaRepository kraj_pochodzeniaRepository) {
        this.kraj_pochodzeniaRepository=kraj_pochodzeniaRepository;
    }
}
