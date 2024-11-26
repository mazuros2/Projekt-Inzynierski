package dev.projekt_inzynierski.controller.User;

import dev.projekt_inzynierski.DTO.SkautDTO;
import dev.projekt_inzynierski.service.User.SkautService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SkautController {
    private final SkautService skautService;

    public SkautController(SkautService skautService) {
        this.skautService = skautService;
    }


    @GetMapping("/skaut/profil/{id}")
    public ResponseEntity<SkautDTO> getSkautDetailsById(@PathVariable Long id){
        SkautDTO skautDetails = skautService.getSkautInfoById(id);
        return ResponseEntity.ok(skautDetails);
    }
}
