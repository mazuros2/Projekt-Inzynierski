package service;

import org.springframework.stereotype.Service;
import repository.KlubRepository;

@Service
public class KlubService {
    private final KlubRepository klubRepository;

    public KlubService(KlubRepository klubRepository) {
        this.klubRepository = klubRepository;
    }



}
