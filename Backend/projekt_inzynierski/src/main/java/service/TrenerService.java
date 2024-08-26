package service;

import org.springframework.stereotype.Service;
import repository.TrenerRepository;

@Service
public class TrenerService {
    private final TrenerRepository trenerRepository;

    public TrenerService(TrenerRepository trenerRepository) {
        this.trenerRepository = trenerRepository;
    }
}
