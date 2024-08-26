package service.Klub;

import org.springframework.stereotype.Service;
import repository.Klub.TrofeumRepository;

@Service
public class TrofeumService {
    private final TrofeumRepository trofeumRepository;

    public TrofeumService(TrofeumRepository trofeumRepository) {
        this.trofeumRepository = trofeumRepository;
    }
}
