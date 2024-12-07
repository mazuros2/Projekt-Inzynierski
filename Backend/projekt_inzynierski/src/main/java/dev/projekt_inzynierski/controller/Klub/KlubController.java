package dev.projekt_inzynierski.controller.Klub;

import dev.projekt_inzynierski.DTO.KlubByIdDTO;
import dev.projekt_inzynierski.DTO.KlubFromLigaDTO;
import dev.projekt_inzynierski.DTO.ZawodnikDTO;
import dev.projekt_inzynierski.models.Klub;
import dev.projekt_inzynierski.service.Klub.KlubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("klub/{id_klub}/zawodnicy")
    public ResponseEntity<List<ZawodnikDTO>> findZawodnicyByIdKlub(@PathVariable long id_klub){
        List<ZawodnikDTO> zawodnikDTO = klubService.findZawodnicyByIdKlub(id_klub);
        return ResponseEntity.ok(zawodnikDTO);
    }

    @GetMapping("klub/wyszukaj")
    public ResponseEntity<List<KlubByIdDTO>> findKlubByNazwa(@RequestParam("nazwa") String nazwa){
        List<KlubByIdDTO> listaKlubow =  klubService.findKlubByNazwa(nazwa);
        return ResponseEntity.ok(listaKlubow);
    }

}