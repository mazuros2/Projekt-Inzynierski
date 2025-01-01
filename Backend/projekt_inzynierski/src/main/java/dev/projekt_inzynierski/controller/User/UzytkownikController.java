package dev.projekt_inzynierski.controller.User;

import dev.projekt_inzynierski.DTO.UzytkownikDTO2;
import dev.projekt_inzynierski.configurationJWT.Authentication.HasloRequest;
import dev.projekt_inzynierski.configurationJWT.JWTService;
import dev.projekt_inzynierski.models.users.Uzytkownik;
import dev.projekt_inzynierski.service.User.UzytkownikService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UzytkownikController {
    private final UzytkownikService uzytkownikService;
    private final JWTService jwtService;

    public UzytkownikController(UzytkownikService uzytkownikService, JWTService jwtService) {
        this.uzytkownikService = uzytkownikService;
        this.jwtService = jwtService;
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

    @PutMapping("/api/uzytkownik/zmienDane")
    public ResponseEntity<String> zmienDaneUzytkownika(@RequestBody UzytkownikDTO2 uzytkownikDTO, @RequestHeader(name = "Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtService.extractUserId(token);
        uzytkownikService.zmienDaneUzytkownika(userId, uzytkownikDTO);
        return ResponseEntity.ok("Dane użytkownika zostały zaktualizowane.");
    }

}
