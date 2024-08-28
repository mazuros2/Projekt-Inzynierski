package dev.projekt_inzynierski.controller.Klub;

import dev.projekt_inzynierski.service.Klub.LigaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LigaController {
    private final LigaService ligaService;

    public LigaController(LigaService ligaService) {
        this.ligaService = ligaService;
    }

    @GetMapping("/ligii")
    public ResponseEntity<List<String>> getAllLigaNames() {
        List<String> ligaNames = ligaService.getAllLigaNames();
        return ResponseEntity.ok(ligaNames);
    }

}
