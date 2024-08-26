package service.User;

import org.springframework.stereotype.Service;
import repository.User.TrenerRepository;

@Service
public class TrenerService {
    private final TrenerRepository trenerRepository;

    public TrenerService(TrenerRepository trenerRepository) {
        this.trenerRepository = trenerRepository;
    }
}
