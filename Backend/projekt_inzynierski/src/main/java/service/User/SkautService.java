package service.User;

import org.springframework.stereotype.Service;
import repository.User.SkautRepository;

@Service
public class SkautService {
    private final SkautRepository skautRepository;

    public SkautService(SkautRepository skautRepository) {
        this.skautRepository = skautRepository;
    }
}
