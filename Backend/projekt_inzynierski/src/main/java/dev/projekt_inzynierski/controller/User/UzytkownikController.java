package dev.projekt_inzynierski.controller.User;

import dev.projekt_inzynierski.configurationJWT.Authentication.HasloRequest;
import dev.projekt_inzynierski.service.User.UzytkownikService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UzytkownikController {
    private final UzytkownikService uzytkownikService;

    public UzytkownikController(UzytkownikService uzytkownikService) {
        this.uzytkownikService = uzytkownikService;
    }

    @PostMapping("/api/config/zmianahasla")
    public ResponseEntity<String> zmienHaslo(@RequestBody HasloRequest request){
        uzytkownikService.zmianaHaslaUzytkownika(request.getLogin(), request.getStareHaslo(), request.getNoweHaslo());
        return ResponseEntity.ok("Haslo zostalo pomyslnie zmienione!");
    }

    //metoda do wyświetlenia szczegółów swojego profilu
}
