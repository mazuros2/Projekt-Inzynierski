package dev.projekt_inzynierski.controller.User;

import dev.projekt_inzynierski.configurationJWT.Authentication.HasloRequest;
import dev.projekt_inzynierski.models.users.Uzytkownik;
import dev.projekt_inzynierski.service.User.UzytkownikService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    @GetMapping("/userDetails/{id}")
    public ResponseEntity<?> getUzytkownikById(@PathVariable Long id) {
        Optional<Uzytkownik> uzytkownik = uzytkownikService.findUzytkownikById(id);

        if (uzytkownik.isPresent()) {
            return ResponseEntity.ok(uzytkownik.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //metoda do wyświetlenia szczegółów swojego profilu
}
