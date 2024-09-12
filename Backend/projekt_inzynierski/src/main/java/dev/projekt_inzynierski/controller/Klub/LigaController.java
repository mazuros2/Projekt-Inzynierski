package dev.projekt_inzynierski.controller.Klub;

import dev.projekt_inzynierski.models.Liga;
import dev.projekt_inzynierski.repository.Klub.LigaRepository;
import dev.projekt_inzynierski.service.Klub.LigaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class LigaController {
    private final LigaService ligaService;
    private final LigaRepository ligaRepository;
    public LigaController(LigaService ligaService,LigaRepository ligaRepository) {
        this.ligaService = ligaService;
        this.ligaRepository = ligaRepository;
    }

    @GetMapping("/ligii")
    public ResponseEntity<List<String>> getAllLigaNames() {
        List<String> ligaNames = ligaService.getAllLigaNames();
        return ResponseEntity.ok(ligaNames);
    }
    @PostMapping("/addLiga")
    Liga addLiga(@RequestBody Liga liga) {
        return ligaRepository.save(liga);
    }

}
