package service.User;

import org.springframework.stereotype.Service;
import repository.User.ZawodnikRepository;

@Service
public class ZawodnikService {
    private final ZawodnikRepository zawodnikRepository;

    public ZawodnikService(ZawodnikRepository zawodnikRepository) {
        this.zawodnikRepository = zawodnikRepository;
    }
}
