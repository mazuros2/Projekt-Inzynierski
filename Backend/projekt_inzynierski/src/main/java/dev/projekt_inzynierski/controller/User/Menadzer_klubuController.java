package dev.projekt_inzynierski.controller.User;

import dev.projekt_inzynierski.DTO.MenadzerDTO;
import dev.projekt_inzynierski.DTO.MenadzerIdKlubDTO;
import dev.projekt_inzynierski.DTO.Register.RegisterSkautDTO;
import dev.projekt_inzynierski.DTO.Register.RegisterTrenerDTO;
import dev.projekt_inzynierski.DTO.Register.RegisterZawodnikDTO;
import dev.projekt_inzynierski.DTO.SkautDTO;
import dev.projekt_inzynierski.DTO.ZawodnikIdKlubDTO;
import dev.projekt_inzynierski.configurationJWT.Authentication.UserRoleValidator;
import dev.projekt_inzynierski.configurationJWT.JWTService;
import dev.projekt_inzynierski.models.Klub;
import dev.projekt_inzynierski.repository.Klub.KlubRepository;
import dev.projekt_inzynierski.repository.User.Menadzer_klubuRepository;
import dev.projekt_inzynierski.service.User.Menadzer_klubuService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Menadzer_klubuController {

    private final Menadzer_klubuService menadzer_klubuService;
    private UserRoleValidator userRoleValidator;
    private final JWTService jwtService;
    private final KlubRepository klubRepository;

    private final Menadzer_klubuRepository menadzer_klubuRepository;

    public Menadzer_klubuController(Menadzer_klubuService menadzer_klubuService, UserRoleValidator userRoleValidator, JWTService jwtService, KlubRepository klubRepository, Menadzer_klubuRepository menadzer_klubuRepository) {
        this.menadzer_klubuService = menadzer_klubuService;
        this.userRoleValidator = userRoleValidator;
        this.jwtService = jwtService;
        this.klubRepository = klubRepository;
        this.menadzer_klubuRepository = menadzer_klubuRepository;
    }

    @GetMapping("/menadzer/profil/{id}")
    public ResponseEntity<MenadzerDTO> getSkautDetailsById(@PathVariable Long id) {
        MenadzerDTO menadzerDetails = menadzer_klubuService.getMenadzerInfoById(id);
        return ResponseEntity.ok(menadzerDetails);
    }

    //    @GetMapping("/getIdKlubuMenadzera/{id_menadzera}")
//    public ResponseEntity<Long> getIdKlubuMenadzera(@PathVariable Long id_menadzera){
//        Long idKlubuMenadzera = menadzer_klubuService.getIdKlubuMenadzera(id_menadzera);
//        return ResponseEntity.ok(idKlubuMenadzera);
//    }
    @GetMapping("/getIdKlubuMenadzera/{id_menadzera}")
    public ResponseEntity<?> getIdKlubuMenadzera(@PathVariable Long id_menadzera) {
        try {
            MenadzerIdKlubDTO idKlubuMenadzera = menadzer_klubuService.getIdKlubuMenadzera(id_menadzera);
            return ResponseEntity.ok(idKlubuMenadzera);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PostMapping("api/menadzer/createTrener")
    public ResponseEntity<String> createTrener(@RequestBody RegisterTrenerDTO request, @RequestHeader(name = "Authorization") String authHeader){
        userRoleValidator.userRoleValidator( "ROLE_MENADZER_KLUBU");
        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtService.extractUserId(token);
        menadzer_klubuService.createTrenerByMenadzer(request,userId);
        return ResponseEntity.ok("Konto Trenera zostało stworzone!");
    }

    @PostMapping("api/menadzer/createSkaut")
    public ResponseEntity<String> createSkaut(@RequestBody RegisterSkautDTO request, @RequestHeader(name = "Authorization") String authHeader){
        userRoleValidator.userRoleValidator( "ROLE_MENADZER_KLUBU");
        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtService.extractUserId(token);
        menadzer_klubuService.createSkautByMenadzer(request,userId);
        return ResponseEntity.ok("Konto Skauta zostało stworzone!");
    }

    @PostMapping("api/menadzer/createZawodnik")
    public ResponseEntity<String> createZawodnik(@RequestBody RegisterZawodnikDTO request, @RequestHeader(name = "Authorization") String authHeader){
        userRoleValidator.userRoleValidator( "ROLE_MENADZER_KLUBU");
        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtService.extractUserId(token);
        menadzer_klubuService.createZawodnikByMenadzer(request,userId);
        return ResponseEntity.ok("Konto Zawodnika zostało stworzone!");
    }

    @DeleteMapping("api/menadzer/zwolnijTrenera")
    public ResponseEntity<String> zwolnijTrenera(@RequestHeader(name = "Authorization") String authHeader){
        userRoleValidator.userRoleValidator( "ROLE_MENADZER_KLUBU");
        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtService.extractUserId(token);
        menadzer_klubuService.deleteTrenerByMenadzer(userId);
        return ResponseEntity.ok("Trener został zwolniony z klubu");
    }

    @DeleteMapping("api/menadzer/zwolnijSkauta")
    public ResponseEntity<String> zwolnijSkauta(@RequestHeader(name = "Authorization") String authHeader){
        userRoleValidator.userRoleValidator( "ROLE_MENADZER_KLUBU");
        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtService.extractUserId(token);
        menadzer_klubuService.deleteSkautByMenadzer(userId);
        return ResponseEntity.ok("Skaut został zwolniony z klubu");
    }

    @GetMapping("/api/menadzer/klub/status")
    public ResponseEntity<Map<String, Boolean>> getStatusKlubuMenadzera(@RequestHeader(name = "Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtService.extractUserId(token);
        Long idKlubuMenadzera = menadzer_klubuRepository.getLongIdKlubuMenadzera(userId)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono klubu dla menadżera!"));

        Klub klubMenadzera = klubRepository.findById(idKlubuMenadzera)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono klubu o takim id!"));

        Map<String, Boolean> status = new HashMap<>();
        status.put("hasTrener", klubMenadzera.getTrener() != null);
        status.put("hasSkaut", klubMenadzera.getSkaut() != null);

        return ResponseEntity.ok(status);
    }
}
