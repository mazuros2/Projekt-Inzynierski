package dev.projekt_inzynierski.controller.User;

import org.springframework.web.bind.annotation.RestController;
import dev.projekt_inzynierski.service.User.TrenerService;

@RestController
public class TrenerController {
    private final TrenerService trenerService;

    public TrenerController(TrenerService trenerService) {
        this.trenerService = trenerService;
    }
}
