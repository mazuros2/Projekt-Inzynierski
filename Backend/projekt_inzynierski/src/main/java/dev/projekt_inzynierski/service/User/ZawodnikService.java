package dev.projekt_inzynierski.service.User;

import org.springframework.stereotype.Service;
import dev.projekt_inzynierski.repository.User.ZawodnikRepository;

@Service
public class ZawodnikService {
    private final ZawodnikRepository zawodnikRepository;

    public ZawodnikService(ZawodnikRepository zawodnikRepository) {
        this.zawodnikRepository = zawodnikRepository;
    }
}
