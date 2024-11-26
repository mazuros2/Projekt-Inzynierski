package dev.projekt_inzynierski.controller.User;

import dev.projekt_inzynierski.DTO.ZawodnikByIdDTO;
import dev.projekt_inzynierski.models.users.Zawodnik;
import dev.projekt_inzynierski.repository.User.ZawodnikRepository;
import dev.projekt_inzynierski.service.User.ZawodnikService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ZawodnikController {
    private final ZawodnikService zawodnikService;

    public ZawodnikController(ZawodnikService zawodnikService) {
        this.zawodnikService = zawodnikService;
    }

    @GetMapping("/getZawodnik/{id_Uzytkownik}")
    public ResponseEntity<ZawodnikByIdDTO> getZawodnikById(@PathVariable Long id_Uzytkownik) {
        ZawodnikByIdDTO zawodnikById = zawodnikService.getZawodnikById(id_Uzytkownik);
        return ResponseEntity.ok(zawodnikById);
    }

//    @PostMapping("/dodajZawodnik")
//    public ResponseEntity<Zawodnik> addZawodnik(@RequestBody Zawodnik zawodnik) {
//        zawodnikService.addZawodnik(zawodnik);
//        return ResponseEntity.ok(zawodnik);
//    }

    @GetMapping("/zawodnicy/profil/{id}")
    public ResponseEntity<ZawodnikByIdDTO> getZawodnikDetails(@PathVariable Long id) {
        ZawodnikByIdDTO zawodnikDetails = zawodnikService.getZawodnikInfoById(id);
        return ResponseEntity.ok(zawodnikDetails);
    }


}
