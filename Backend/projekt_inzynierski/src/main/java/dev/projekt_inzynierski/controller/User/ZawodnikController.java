package dev.projekt_inzynierski.controller.User;

import dev.projekt_inzynierski.models.users.Zawodnik;
import dev.projekt_inzynierski.repository.User.ZawodnikRepository;
import dev.projekt_inzynierski.service.User.ZawodnikService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ZawodnikController {
    private final ZawodnikService zawodnikService;
    private final ZawodnikRepository zawodnikRepository;

    public ZawodnikController(ZawodnikService zawodnikService,ZawodnikRepository zawodnikRepository) {
        this.zawodnikService = zawodnikService;
        this.zawodnikRepository=zawodnikRepository;
    }

    @GetMapping("/getZawodnik/{id_Uzytkownik}")
    public ResponseEntity<Zawodnik> getZawodnikById(@PathVariable Long id_Uzytkownik) {
        Optional<Zawodnik> zawodnik = zawodnikRepository.findById(id_Uzytkownik);
        return zawodnik.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
