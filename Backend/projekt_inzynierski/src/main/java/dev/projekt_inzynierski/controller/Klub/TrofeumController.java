package dev.projekt_inzynierski.controller.Klub;

import dev.projekt_inzynierski.DTO.Create.CreateTrofeumDTO;
import dev.projekt_inzynierski.DTO.TrofeumDTO;
import dev.projekt_inzynierski.DTO.TrofeumNazwaKlubuDTO;
import dev.projekt_inzynierski.configurationJWT.Authentication.UserRoleValidator;
import dev.projekt_inzynierski.models.Trofeum;
import dev.projekt_inzynierski.service.Klub.TrofeumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TrofeumController {
    private final TrofeumService trofeumService;
    private UserRoleValidator userRoleValidator;

    public TrofeumController(TrofeumService trofeumService, UserRoleValidator userRoleValidator) {
        this.trofeumService = trofeumService;
        this.userRoleValidator = userRoleValidator;
    }

    @GetMapping("/klub/{klubId}/trofea")
    public ResponseEntity<List<TrofeumDTO>> getTrofeaDlaKlubu(@PathVariable long klubId) {
        List<TrofeumDTO> trofea = trofeumService.getTrofeaDlaKlubu(klubId);
        if (trofea.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(trofea);
    }

    @GetMapping("/trofeum/mistrzpolski")
    public ResponseEntity<List<TrofeumNazwaKlubuDTO>> getMistrzPolski() {
        List<TrofeumNazwaKlubuDTO> mistrzPolski = trofeumService.getMistrzPolski();
        return ResponseEntity.ok(mistrzPolski);
    }

    @GetMapping("/trofeum/pucharpolski")
    public ResponseEntity<List<TrofeumNazwaKlubuDTO>> getPucharPolski() {
        List<TrofeumNazwaKlubuDTO> pucharPolski = trofeumService.getPucharPolski();
        return ResponseEntity.ok(pucharPolski);
    }

    @GetMapping("/api/trofeum/mistrzpolski/ostatnizdobywcy")
    public ResponseEntity<List<TrofeumNazwaKlubuDTO>> getLastFiveMistrzPolski() {
        List<TrofeumNazwaKlubuDTO> mistrzowiePolski = trofeumService.getLastFiveMistrzPolski();
        return ResponseEntity.ok(mistrzowiePolski);
    }

    @GetMapping("/api/trofeum/pucharpolski/ostatnizdobywcy")
    public ResponseEntity<List<TrofeumNazwaKlubuDTO>> getLastFivePucharPolski() {
        List<TrofeumNazwaKlubuDTO> zdobywcyPucharuPolski = trofeumService.getLastFivePucharPolski();
        return ResponseEntity.ok(zdobywcyPucharuPolski);
    }

    @PostMapping("/api/trofeum/createTrofeum")
    public ResponseEntity<String> createTrofeum(@RequestBody CreateTrofeumDTO request){
        userRoleValidator.userRoleValidator("ROLE_ADMIN");
        trofeumService.createTrofeum(request);
        return ResponseEntity.ok("Trofeum zostało stworzone!");
    }

}
