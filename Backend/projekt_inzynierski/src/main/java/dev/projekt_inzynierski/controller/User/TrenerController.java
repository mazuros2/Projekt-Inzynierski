package dev.projekt_inzynierski.controller.User;

import dev.projekt_inzynierski.DTO.TrenerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import dev.projekt_inzynierski.service.User.TrenerService;

@RestController
public class TrenerController {
    private final TrenerService trenerService;

    public TrenerController(TrenerService trenerService) {
        this.trenerService = trenerService;
    }

    @GetMapping("/trener/profil/{id}")
    public ResponseEntity<TrenerDTO> getTrenerDetailsById(@PathVariable Long id){
        TrenerDTO trenerDetails = trenerService.getTrenerInfoById(id);
        return ResponseEntity.ok(trenerDetails);
    }
}
