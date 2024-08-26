package controller.Klub;

import org.springframework.web.bind.annotation.RestController;
import service.Klub.TrofeumService;

@RestController
public class TrofeumController {
    private final TrofeumService trofeumService;

    public TrofeumController(TrofeumService trofeumService) {
        this.trofeumService = trofeumService;
    }
}
