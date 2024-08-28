package dev.projekt_inzynierski.service.User;

import dev.projekt_inzynierski.repository.User.TrenerRepository;
import org.springframework.stereotype.Service;

@Service
public class TrenerService {
    private final TrenerRepository trenerRepository;

    public TrenerService(TrenerRepository trenerRepository) {
        this.trenerRepository = trenerRepository;
    }
}
