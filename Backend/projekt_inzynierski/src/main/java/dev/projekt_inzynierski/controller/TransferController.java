package dev.projekt_inzynierski.controller;

import dev.projekt_inzynierski.DTO.TransferDTO;
import dev.projekt_inzynierski.DTO.TransferRequest;
import dev.projekt_inzynierski.DTO.TrofeumDTO;
import dev.projekt_inzynierski.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/{menadzerId}/transfery")
    public ResponseEntity<List<TransferDTO>> getTransferyByMenadzerId(@PathVariable long menadzerId) {
        List<TransferDTO> transfery = transferService.findByMenadzerId(menadzerId);
        if (transfery.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(transfery);
    }
    @PostMapping("/sendTransfer")
    public ResponseEntity<String> sendTransfer(@RequestBody TransferRequest transferRequest) {
        try {
            LocalDate dataTransferu = LocalDate.now();
            transferService.sendTransfer(
                    dataTransferu,
                    transferRequest.getKwota(),
                    transferRequest.getIdZawodnik(),
                    transferRequest.getIdKlubOd(),
                    transferRequest.getIdKlubDo()
            );
            return ResponseEntity.ok("Transfer został wysłany z powodzeniem");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body("Wystąpił błąd przy transferze " + e.getMessage());
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

        System.out.println("Otrzymane parametry:");
        System.out.println("id_transfer: " + idTransfer);
        System.out.println("id_uzytkownik: " + idUzytkownik);
        System.out.println("id_klubOd: " + idKlubOd);
        System.out.println("id_klubDo: " + idKlubDo);

        try {
            LocalDate dataDo = LocalDate.now();
            transferService.zaakceptujTransfer(idTransfer, dataDo, idUzytkownik, idKlubOd, idKlubDo);
            return ResponseEntity.ok("Transfer został zaakceptowany.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Wystąpił błąd przy akceptacji transferu: " + e.getMessage());
        }
    }

//@PostMapping("/zaakceptuj")
//@PreAuthorize("isAuthenticated()")
//public ResponseEntity<String> zaakceptujTransfer(@RequestBody Map<String, Object> payload) {
//    try {
//        long idTransfer = Long.parseLong(payload.get("id_transfer").toString());
//        long idUzytkownik = Long.parseLong(payload.get("id_uzytkownik").toString());
//        long idKlubOd = Long.parseLong(payload.get("id_klubOd").toString());
//        long idKlubDo = Long.parseLong(payload.get("id_klubDo").toString());
//
//        LocalDate dataDo = LocalDate.now();
//        transferService.zaakceptujTransfer(idTransfer, dataDo, idUzytkownik, idKlubOd, idKlubDo);
//        return ResponseEntity.ok("Transfer został zaakceptowany.");
//    } catch (IllegalArgumentException e) {
//        return ResponseEntity.badRequest().body(e.getMessage());
//    } catch (Exception e) {
//        e.printStackTrace();
//        return ResponseEntity.status(500).body("Wystąpił błąd przy akceptacji transferu: " + e.getMessage());
//    }
//}


}


