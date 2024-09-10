package dev.projekt_inzynierski.controller.Klub;

import dev.projekt_inzynierski.DTO.TrofeumDTO;
import dev.projekt_inzynierski.models.Trofeum;
import dev.projekt_inzynierski.service.Klub.TrofeumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrofeumController {
    private final TrofeumService trofeumService;

    public TrofeumController(TrofeumService trofeumService) {
        this.trofeumService = trofeumService;
    }

    @GetMapping("/klub/{klubId}/trofea")
    public ResponseEntity<List<TrofeumDTO>> getTrofeaDlaKlubu(@PathVariable long klubId) {
        List<TrofeumDTO> trofea = trofeumService.getTrofeaDlaKlubu(klubId);
        if (trofea.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(trofea);
    }

}
