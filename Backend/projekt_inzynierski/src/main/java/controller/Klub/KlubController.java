package controller.Klub;

import org.springframework.web.bind.annotation.RestController;
import service.Klub.KlubService;

@RestController
public class KlubController {
    private final KlubService klubService;

    public KlubController(KlubService klubService) {
        this.klubService = klubService;
    }
}
