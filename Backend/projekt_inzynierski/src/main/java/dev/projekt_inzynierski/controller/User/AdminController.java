package dev.projekt_inzynierski.controller.User;

import dev.projekt_inzynierski.DTO.KlubDTO;
import dev.projekt_inzynierski.DTO.Register.RegisterMenadzerDTO;
import dev.projekt_inzynierski.DTO.Register.RegisterSkautDTO;
import dev.projekt_inzynierski.DTO.Register.RegisterTrenerDTO;
import dev.projekt_inzynierski.DTO.Register.RegisterZawodnikDTO;
import dev.projekt_inzynierski.configurationJWT.Authentication.UserRoleValidator;
import dev.projekt_inzynierski.service.User.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;
    private UserRoleValidator userRoleValidator;

    public AdminController(AdminService adminService, UserRoleValidator userRoleValidator) {
        this.adminService = adminService;
        this.userRoleValidator = userRoleValidator;
    }

    @PostMapping("/createTrener")
    public ResponseEntity<String> createTrener(@RequestBody RegisterTrenerDTO request){
        userRoleValidator.userRoleValidator("ROLE_ADMIN", "ROLE_MENADZER_KLUBU");
        adminService.createTrener(request);
        return ResponseEntity.ok("Konto Trenera zostało stworzone!");
    }

    @PostMapping("/createSkaut")
    public ResponseEntity<String> createSkaut(@RequestBody RegisterSkautDTO request){
        userRoleValidator.userRoleValidator("ROLE_ADMIN", "ROLE_MENADZER_KLUBU");
        adminService.createSkaut(request);
        return ResponseEntity.ok("Konto Skauta zostało stworzone!");
    }

    @PostMapping("/createZawodnik")
    public ResponseEntity<String> createZawodnik(@RequestBody RegisterZawodnikDTO request){
        userRoleValidator.userRoleValidator("ROLE_ADMIN", "ROLE_MENADZER_KLUBU");
        adminService.createZawodnik(request);
        return ResponseEntity.ok("Konto Zawodnika zostało stworzone!");
    }

    @PostMapping("/createMenadzer")
    public ResponseEntity<String> createZawodnik(@RequestBody RegisterMenadzerDTO request){
        userRoleValidator.userRoleValidator("ROLE_ADMIN");
        adminService.createMenadzer(request);
        return ResponseEntity.ok("Konto Zawodnika zostało stworzone!");
    }

    @DeleteMapping("/zwolnijTrenera")
    public ResponseEntity<String> zwolnijTrenera(@RequestBody KlubDTO request){
        userRoleValidator.userRoleValidator( "ROLE_ADMIN");
        adminService.deleteTrener(request);
        return ResponseEntity.ok("Trener został zwolniony z klubu");
    }

    @DeleteMapping("/zwolnijSkauta")
    public ResponseEntity<String> zwolnijSkauta(@RequestBody KlubDTO request){
        userRoleValidator.userRoleValidator( "ROLE_ADMIN");
        adminService.deleteSkaut(request);
        return ResponseEntity.ok("Trener został zwolniony z klubu");
    }

    @DeleteMapping("/zwolnijMenadzera")
    public ResponseEntity<String> zwolnijMenadzera(@RequestBody KlubDTO request){
        userRoleValidator.userRoleValidator( "ROLE_ADMIN");
        adminService.deleteMenadzer(request);
        return ResponseEntity.ok("Trener został zwolniony z klubu");
    }

}