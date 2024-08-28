package dev.projekt_inzynierski.controller.User;

import dev.projekt_inzynierski.service.User.UzytkownikService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UzytkownikController {
    private final UzytkownikService uzytkownikService;

    public UzytkownikController(UzytkownikService uzytkownikService) {
        this.uzytkownikService = uzytkownikService;
    }
}
