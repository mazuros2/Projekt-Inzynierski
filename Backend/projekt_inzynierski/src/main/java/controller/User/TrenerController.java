package controller.User;

import org.springframework.web.bind.annotation.RestController;
import service.User.TrenerService;

@RestController
public class TrenerController {
    private final TrenerService trenerService;

    public TrenerController(TrenerService trenerService) {
        this.trenerService = trenerService;
    }
}
