package dev.projekt_inzynierski.service.User;

import dev.projekt_inzynierski.repository.User.SkautRepository;
import org.springframework.stereotype.Service;

@Service
public class SkautService {
    private final SkautRepository skautRepository;

    public SkautService(SkautRepository skautRepository) {
        this.skautRepository = skautRepository;
    }
}
