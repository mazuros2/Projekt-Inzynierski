package controller.User;

import org.springframework.web.bind.annotation.RestController;
import service.User.Menadzer_klubuService;

@RestController
public class Menadzer_klubuController {

    private final Menadzer_klubuService menadzer_klubuService;

    public Menadzer_klubuController(Menadzer_klubuService menadzer_klubuService) {
        this.menadzer_klubuService = menadzer_klubuService;
    }
}
