package dev.projekt_inzynierski.controller.User;

import dev.projekt_inzynierski.DTO.ZawodnikByIdDTO;
import dev.projekt_inzynierski.DTO.ZawodnikDTO;
import dev.projekt_inzynierski.DTO.ZawodnikDTO2;
import dev.projekt_inzynierski.DTO.ZawodnikIdKlubDTO;
import dev.projekt_inzynierski.models.users.Zawodnik;
import dev.projekt_inzynierski.service.User.ZawodnikService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ZawodnikController {
    private final ZawodnikService zawodnikService;

    public ZawodnikController(ZawodnikService zawodnikService) {
        this.zawodnikService = zawodnikService;
    }

    @GetMapping("/zawodnicy/profil/{id}")
    public ResponseEntity<ZawodnikByIdDTO> getZawodnikDetails(@PathVariable Long id) {
        ZawodnikByIdDTO zawodnikDetails = zawodnikService.getZawodnikInfoById(id);
        return ResponseEntity.ok(zawodnikDetails);
    }

/*    @GetMapping("/zawodnicy/wyszukaj")
    public ResponseEntity<List<ZawodnikDTO>> getZawodnikByText(@RequestParam("text") String text){
        List<ZawodnikDTO> zawodnicy = zawodnikService.findZawodnikByText(text);
        return ResponseEntity.ok(zawodnicy);
    }
*/

    @GetMapping("/zawodnicy")
    public ResponseEntity<List<ZawodnikDTO2>> getAllZawodnicy(){
        List<ZawodnikDTO2> zawodnicy = zawodnikService.getAllZawodnicy();
        return ResponseEntity.ok(zawodnicy);
    }
    @GetMapping("/findKlubIdByZawodnik/{id_zawodnik}")
    public ResponseEntity<?> getIdKlubByZawodnik(@PathVariable Long id_zawodnik) {
        try {
            ZawodnikIdKlubDTO zklid = zawodnikService.znajdzIdKlubuZawodnika(id_zawodnik);
            return ResponseEntity.ok(zklid);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
