package dev.projekt_inzynierski.controller.User;

import dev.projekt_inzynierski.DTO.RegisterTrenerDTO;
import dev.projekt_inzynierski.service.User.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/createTrener")
    public ResponseEntity<String> createTrener(@RequestBody RegisterTrenerDTO request){
        adminService.createTrener(request);
        return ResponseEntity.ok("Konto Trenera zostało stworzone!");
    }

    @PostMapping("/createSkaut")
    public ResponseEntity<String> createSkaut(@RequestBody RegisterSkautDTO request){
        adminService.createSkaut(request);
        return ResponseEntity.ok("Konto Skauta zostało stworzone!");
    }

    @PostMapping("/createZawodnik")
    public ResponseEntity<String> createZawodnik(@RequestBody RegisterZawodnikDTO request){
        adminService.createZawodnik(request);
        return ResponseEntity.ok("Konto Zawodnika zostało stworzone!");
    }

    @PostMapping("/createMenadzer")
    public ResponseEntity<String> createZawodnik(@RequestBody RegisterMenadzerDTO request){
        adminService.createZawodnik(request);
        return ResponseEntity.ok("Konto Zawodnika zostało stworzone!");
    }


}
