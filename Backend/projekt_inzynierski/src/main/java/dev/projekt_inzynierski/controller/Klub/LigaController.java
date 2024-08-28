package dev.projekt_inzynierski.controller.Klub;

import dev.projekt_inzynierski.service.Klub.LigaService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LigaController {
    private final LigaService ligaService;

    public LigaController(LigaService ligaService) {
        this.ligaService = ligaService;
    }
}
