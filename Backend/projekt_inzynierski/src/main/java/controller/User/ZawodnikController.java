package controller.User;

import org.springframework.web.bind.annotation.RestController;
import service.User.ZawodnikService;

@RestController
public class ZawodnikController {
    private final ZawodnikService zawodnikService;

    public ZawodnikController(ZawodnikService zawodnikService) {
        this.zawodnikService = zawodnikService;
    }
}
