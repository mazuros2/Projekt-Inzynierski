package controller.User;

import org.springframework.web.bind.annotation.RestController;
import service.User.SkautService;

@RestController
public class SkautController {
    private final SkautService skautService;

    public SkautController(SkautService skautService) {
        this.skautService = skautService;
    }
}
