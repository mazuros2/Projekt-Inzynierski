package dev.projekt_inzynierski.controller.User;

import dev.projekt_inzynierski.DTO.ZawodnikByIdDTO;
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
    public ResponseEntity<ZawodnikByIdDTO> getZawodnikById(@PathVariable Long id_Uzytkownik) {
        ZawodnikByIdDTO zawodnikById = zawodnikService.getZawodnikById(id_Uzytkownik);
        return ResponseEntity.ok(zawodnikById);
    }
}
