package service;

import org.springframework.stereotype.Service;
import repository.SkautRepository;

@Service
public class SkautService {
    private final SkautRepository skautRepository;

    public SkautService(SkautRepository skautRepository) {
        this.skautRepository = skautRepository;
    }
}
