package service;

import org.springframework.stereotype.Service;
import repository.TrofeumRepository;

@Service
public class TrofeumService {
    private final TrofeumRepository trofeumRepository;

    public TrofeumService(TrofeumRepository trofeumRepository) {
        this.trofeumRepository = trofeumRepository;
    }
}
