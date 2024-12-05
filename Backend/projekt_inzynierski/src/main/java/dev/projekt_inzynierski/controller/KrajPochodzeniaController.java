package dev.projekt_inzynierski.controller;

import dev.projekt_inzynierski.DTO.KrajPochodzeniaDTO;
import dev.projekt_inzynierski.service.Kraj_pochodzeniaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/krajpochodzenia")
public class KrajPochodzeniaController {

   private final Kraj_pochodzeniaService kraj_pochodzeniaService;

    public KrajPochodzeniaController(Kraj_pochodzeniaService kraj_pochodzeniaService) {
        this.kraj_pochodzeniaService = kraj_pochodzeniaService;
    }

    @GetMapping("/getkraje")
    public ResponseEntity<List<KrajPochodzeniaDTO>> getAllCountries() {
        List<KrajPochodzeniaDTO> kraje = kraj_pochodzeniaService.getAllKraje();
        return ResponseEntity.ok(kraje);
    }
}