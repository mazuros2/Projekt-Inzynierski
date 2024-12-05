package dev.projekt_inzynierski.controller;

import dev.projekt_inzynierski.DTO.PozycjaDTO;
import dev.projekt_inzynierski.repository.PozycjaRepository;
import dev.projekt_inzynierski.service.PozycjaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pozycja")
public class PozycjaController {

    private final PozycjaService pozycjaService;

    public PozycjaController(PozycjaService pozycjaService) {
        this.pozycjaService = pozycjaService;
    }

    @GetMapping("/getpozycje")
    public ResponseEntity<List<PozycjaDTO>> getPozycje(){
        List<PozycjaDTO> pozycje = pozycjaService.getPozycje();
        return ResponseEntity.ok(pozycje);
    }
}
