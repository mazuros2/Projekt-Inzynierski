package dev.projekt_inzynierski.controller.User;

import dev.projekt_inzynierski.service.User.Menadzer_klubuService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Menadzer_klubuController {

    private final Menadzer_klubuService menadzer_klubuService;

    public Menadzer_klubuController(Menadzer_klubuService menadzer_klubuService) {
        this.menadzer_klubuService = menadzer_klubuService;
    }

    //metoda do wyświetlenia profilu menadzera
    // /menadzer/profil/id

}
