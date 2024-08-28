package dev.projekt_inzynierski.controller.Klub;

import dev.projekt_inzynierski.service.Klub.TrofeumService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrofeumController {
    private final TrofeumService trofeumService;

    public TrofeumController(TrofeumService trofeumService) {
        this.trofeumService = trofeumService;
    }
}
