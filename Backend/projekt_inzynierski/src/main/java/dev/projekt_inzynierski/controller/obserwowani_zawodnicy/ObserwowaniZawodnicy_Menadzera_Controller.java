package dev.projekt_inzynierski.controller.obserwowani_zawodnicy;
import dev.projekt_inzynierski.DTO.ZawodnikByIdDTO;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Menadzera;
import dev.projekt_inzynierski.service.ObserwowaniZawodnicy.ObserwowaniZawodnicyMenadzeraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skautingZawodnika/menadzer")
public class ObserwowaniZawodnicy_Menadzera_Controller {

    private final ObserwowaniZawodnicyMenadzeraService obserwowaniZawodnicyMenadzeraService;

    public ObserwowaniZawodnicy_Menadzera_Controller(ObserwowaniZawodnicyMenadzeraService obserwowaniZawodnicyMenadzeraService) {
        this.obserwowaniZawodnicyMenadzeraService = obserwowaniZawodnicyMenadzeraService;
    }

    @RequestMapping("/dodajZawodnika")
    public ResponseEntity<String> dodajDoListyObserwowanych(@RequestParam int id_Menadzer, @RequestParam int id_Zawodnik){
        obserwowaniZawodnicyMenadzeraService.dodanieZawodnikaDoListyObs(id_Menadzer,id_Zawodnik);
        return ResponseEntity.ok("Zawodnik zostal dodany do listy obserwowanych!");
    }

    @DeleteMapping("/usunZawodnika")
    public ResponseEntity<String> usunZListyObserwowanych(@RequestParam int id_Menadzer, @RequestParam int id_Zawodnik){
        obserwowaniZawodnicyMenadzeraService.usunZawodnikaZListyObs(id_Menadzer,id_Zawodnik);
        return ResponseEntity.ok("Zawodnik zostal usunięty z listy obserwowanych!");
    }

    @GetMapping("/{id_Menadzer}/listaZawodnikow")
    public ResponseEntity<List<ZawodnikByIdDTO>> getListaObserwowanych(@PathVariable long id_Menadzer){
        List<ZawodnikByIdDTO> listaZawodnikow = obserwowaniZawodnicyMenadzeraService.listaObsZawodnikowMenadzera(id_Menadzer);
        return ResponseEntity.ok(listaZawodnikow);
    }
}
