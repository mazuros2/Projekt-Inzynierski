package controller.Klub;

import org.springframework.web.bind.annotation.RestController;
import service.Klub.LigaService;

@RestController
public class LigaController {
    private final LigaService ligaService;

    public LigaController(LigaService ligaService) {
        this.ligaService = ligaService;
    }
}
