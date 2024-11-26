package dev.projekt_inzynierski.controller;

import dev.projekt_inzynierski.DTO.TransferDTO;
import dev.projekt_inzynierski.DTO.TrofeumDTO;
import dev.projekt_inzynierski.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class TransferController {
    private final TransferService transferService;
    public TransferController(TransferService transferService) {this.transferService = transferService;}
    @GetMapping("/zawodnik/{zawodnikId}/transfery")
    public ResponseEntity<List<TransferDTO>> getTransferyByZawodnikId(@PathVariable long zawodnikId) {
        List<TransferDTO> transfery = transferService.findByZawodnikId(zawodnikId);
        if (transfery.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(transfery);
    }
}
