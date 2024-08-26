package service.Klub;

import org.springframework.stereotype.Service;
import repository.Klub.KlubRepository;

@Service
public class KlubService {
    private final KlubRepository klubRepository;

    public KlubService(KlubRepository klubRepository) {
        this.klubRepository = klubRepository;
    }



}
