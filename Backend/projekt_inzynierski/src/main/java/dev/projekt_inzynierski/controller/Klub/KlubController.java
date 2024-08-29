package dev.projekt_inzynierski.controller.Klub;

import dev.projekt_inzynierski.models.Klub;
import dev.projekt_inzynierski.service.Klub.KlubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KlubController {
    @Autowired
    private final KlubService klubService;

    public KlubController(KlubService klubService) {
        this.klubService = klubService;
    }
    @GetMapping("/getKluby")
    public List<Klub> getKluby(){
        List<Klub> kluby = klubService.getKluby();
        return kluby;
    }

    @GetMapping("/{ligaId}/kluby")
    public ResponseEntity<List<Klub>> getKlubyByLigaId(@PathVariable long ligaId) {
        List<Klub> kluby = klubService.getAllKlubyByLigaId(ligaId);
        return ResponseEntity.ok(kluby);
    }

    @GetMapping("klub/{id}")
    public ResponseEntity<Klub> getKlubById(@PathVariable long id) {
        Klub klub = klubService.getKlubById(id);
        return ResponseEntity.ok(klub);
    }

}
