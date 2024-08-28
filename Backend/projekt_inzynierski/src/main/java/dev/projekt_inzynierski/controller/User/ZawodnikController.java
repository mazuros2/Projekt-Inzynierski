package dev.projekt_inzynierski.controller.User;

import dev.projekt_inzynierski.service.User.ZawodnikService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZawodnikController {
    private final ZawodnikService zawodnikService;

    public ZawodnikController(ZawodnikService zawodnikService) {
        this.zawodnikService = zawodnikService;
    }
}
