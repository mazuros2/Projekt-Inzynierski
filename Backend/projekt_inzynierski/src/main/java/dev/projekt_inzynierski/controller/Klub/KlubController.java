package dev.projekt_inzynierski.controller.Klub;

import dev.projekt_inzynierski.service.Klub.KlubService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KlubController {
    private final KlubService klubService;

    public KlubController(KlubService klubService) {
        this.klubService = klubService;
    }
}
