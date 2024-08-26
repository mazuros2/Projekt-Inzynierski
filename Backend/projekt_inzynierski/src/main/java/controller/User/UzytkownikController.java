package controller.User;

import org.springframework.web.bind.annotation.RestController;
import service.User.UzytkownikService;

@RestController
public class UzytkownikController {
    private final UzytkownikService uzytkownikService;

    public UzytkownikController(UzytkownikService uzytkownikService) {
        this.uzytkownikService = uzytkownikService;
    }
}
