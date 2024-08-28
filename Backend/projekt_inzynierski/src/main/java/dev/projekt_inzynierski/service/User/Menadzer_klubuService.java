package dev.projekt_inzynierski.service.User;

import org.springframework.stereotype.Service;
import dev.projekt_inzynierski.repository.User.Menadzer_klubuRepository;

@Service
public class Menadzer_klubuService {
    private final Menadzer_klubuRepository menadzer_klubuRepository;

    public Menadzer_klubuService(Menadzer_klubuRepository menadzer_klubuRepository) {
        this.menadzer_klubuRepository = menadzer_klubuRepository;
    }
}
