package dev.projekt_inzynierski.controller.User;

import dev.projekt_inzynierski.service.User.SkautService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SkautController {
    private final SkautService skautService;

    public SkautController(SkautService skautService) {
        this.skautService = skautService;
    }


    //metoda do wyświetlenia profilu skauta
    // /skaut/profil/id

}
