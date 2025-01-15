package dev.projekt_inzynierski.controller.User;

import dev.projekt_inzynierski.DTO.MenadzerDTO;
import dev.projekt_inzynierski.DTO.MenadzerIdKlubDTO;
import dev.projekt_inzynierski.DTO.SkautDTO;
import dev.projekt_inzynierski.DTO.ZawodnikIdKlubDTO;
import dev.projekt_inzynierski.service.User.Menadzer_klubuService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Menadzer_klubuController {

    private final Menadzer_klubuService menadzer_klubuService;

    public Menadzer_klubuController(Menadzer_klubuService menadzer_klubuService) {
        this.menadzer_klubuService = menadzer_klubuService;
    }

    @GetMapping("/menadzer/profil/{id}")
    public ResponseEntity<MenadzerDTO> getSkautDetailsById(@PathVariable Long id){
        MenadzerDTO menadzerDetails = menadzer_klubuService.getMenadzerInfoById(id);
        return ResponseEntity.ok(menadzerDetails);
    }
//    @GetMapping("/getIdKlubuMenadzera/{id_menadzera}")
//    public ResponseEntity<Long> getIdKlubuMenadzera(@PathVariable Long id_menadzera){
//        Long idKlubuMenadzera = menadzer_klubuService.getIdKlubuMenadzera(id_menadzera);
//        return ResponseEntity.ok(idKlubuMenadzera);
//    }
    @GetMapping("/getIdKlubuMenadzera/{id_menadzera}")
    public ResponseEntity<?> getIdKlubuMenadzera(@PathVariable Long id_menadzera){
        try {
        MenadzerIdKlubDTO idKlubuMenadzera = menadzer_klubuService.getIdKlubuMenadzera(id_menadzera);
        return ResponseEntity.ok(idKlubuMenadzera);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //metoda do stworzenia profilu zawodnika
    //metoda do stworzenia profilu skauta
    //metoda do stworzenia profilu trenera
}
