package service;

import org.springframework.stereotype.Service;
import repository.ZawodnikRepository;

@Service
public class ZawodnikService {
    private final ZawodnikRepository zawodnikRepository;

    public ZawodnikService(ZawodnikRepository zawodnikRepository) {
        this.zawodnikRepository = zawodnikRepository;
    }
}
