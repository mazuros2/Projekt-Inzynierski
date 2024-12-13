package dev.projekt_inzynierski.controller;

import dev.projekt_inzynierski.DTO.TransferDTO;
import dev.projekt_inzynierski.DTO.TrofeumDTO;
import dev.projekt_inzynierski.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @PostMapping("/sendTransfer")
    public ResponseEntity<String> sendTransfer(
            @RequestParam("kwota") int kwota,
            @RequestParam("id_zawodnik") long idZawodnik,
            @RequestParam("id_klubOd") long idKlubOd,
            @RequestParam("id_klubDo") long idKlubDo) {
        try {
            LocalDate dataTransferu = LocalDate.now();
            transferService.sendTransfer(dataTransferu, kwota, idZawodnik, idKlubOd, idKlubDo);
            return ResponseEntity.ok("Transfer zostal wyslany z powodzeniem");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body("wystapil blad przy transferze " + e.getMessage());
        }
    }

    @PostMapping("/odrzuc/{id}")
    public ResponseEntity<String> odrzucTransfer(@PathVariable long id) {
        try {
            transferService.odrzucTransfer(id);
            return ResponseEntity.ok("Udalo sie odrzucic transfer");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("wystapil blad przy odrzucaniu transferu " + e.getMessage());
        }
    }


    @PostMapping("/zaakceptuj")
    public ResponseEntity<String> zaakceptujTransfer(
            @RequestParam("id_transfer") long idTransfer,
            @RequestParam("id_uzytkownik") long idUzytkownik,
            @RequestParam("id_klubOd") long idKlubOd,
            @RequestParam("id_klubDo") long idKlubDo) {
        try {
            LocalDate dataDo = LocalDate.now(); // Zamiana String na LocalDate
            transferService.zaakceptujTransfer(idTransfer, dataDo, idUzytkownik, idKlubOd, idKlubDo);
            return ResponseEntity.ok("Transfer zostal zaakceptowany");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Wystapil blad przy akceptacji transferu " + e.getMessage());
        }
    }

}


