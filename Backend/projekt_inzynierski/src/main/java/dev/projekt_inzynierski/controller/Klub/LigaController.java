package dev.projekt_inzynierski.controller.Klub;

import dev.projekt_inzynierski.DTO.LigaDTO;
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
    public LigaController(LigaService ligaService) {
        this.ligaService = ligaService;
    }

    @GetMapping("/ligii")
    public ResponseEntity<List<LigaDTO>> getAllLigaNames() {
        List<LigaDTO> ligaNames = ligaService.getAllLigaNames();
        return ResponseEntity.ok(ligaNames);
    }
    @PostMapping("/addLiga")
    public Liga addLiga(@RequestBody Liga liga) {
        return ligaService.addLiga(liga);
    }

}
