package dev.projekt_inzynierski.controller.Klub;

import dev.projekt_inzynierski.DTO.KlubByIdDTO;
import dev.projekt_inzynierski.DTO.KlubFromLigaDTO;
import dev.projekt_inzynierski.models.Klub;
import dev.projekt_inzynierski.service.Klub.KlubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class KlubController {
    @Autowired
    private final KlubService klubService;

    public KlubController(KlubService klubService) {
        this.klubService = klubService;
    }
    @GetMapping("/kluby")
    public ResponseEntity<List<KlubByIdDTO>> getKluby(){
        List<KlubByIdDTO> kluby = klubService.getKluby();
        return ResponseEntity.ok(kluby);
    }

    @GetMapping("/{ligaId}/kluby")
    public ResponseEntity<List<KlubFromLigaDTO>> getKlubyByLigaId(@PathVariable long ligaId) {
        List<KlubFromLigaDTO> kluby = klubService.getAllKlubyByLigaId(ligaId);
        return ResponseEntity.ok(kluby);
    }

    @GetMapping("klub/{id}")
    public ResponseEntity<KlubByIdDTO> getKlubById(@PathVariable long id) {
        KlubByIdDTO klubByIdDTO = klubService.getKlubById(id);
        return ResponseEntity.ok(klubByIdDTO);
    }
}