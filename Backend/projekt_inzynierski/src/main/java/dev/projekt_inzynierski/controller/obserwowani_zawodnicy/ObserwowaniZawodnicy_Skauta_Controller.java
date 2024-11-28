package dev.projekt_inzynierski.controller.obserwowani_zawodnicy;

import dev.projekt_inzynierski.DTO.ZawodnikByIdDTO;
import dev.projekt_inzynierski.service.ObserwowaniZawodnicy.ObserwowaniZawodnicySkautaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skautingZawodnika/skaut")
public class ObserwowaniZawodnicy_Skauta_Controller {

    private final ObserwowaniZawodnicySkautaService obserwowaniZawodnicySkautaService;

    public ObserwowaniZawodnicy_Skauta_Controller(ObserwowaniZawodnicySkautaService obserwowaniZawodnicySkautaService) {
        this.obserwowaniZawodnicySkautaService = obserwowaniZawodnicySkautaService;
    }

    @RequestMapping("/dodajZawodnika")
    public ResponseEntity<String> dodajDoListyObserwowanych(@RequestParam int id_Skaut, @RequestParam int id_Zawodnik){
        obserwowaniZawodnicySkautaService.dodanieZawodnikaDoListyObs(id_Skaut,id_Zawodnik);
        return ResponseEntity.ok("Zawodnik został dodany do listy obserwowanych!");
    }

    @DeleteMapping("/usunZawodnika")
    public ResponseEntity<String> usunZListyObserwowanych(@RequestParam int idSkaut, @RequestParam int idZawodnik) {
        obserwowaniZawodnicySkautaService.usunZawodnikaZListyObs(idSkaut, idZawodnik);
        return ResponseEntity.ok("Zawodnik został usunięty z listy obserwowanych!");
    }

    @GetMapping("/{id_Skaut}/listaZawodnikow")
    public ResponseEntity<List<ZawodnikByIdDTO>> getListaObserwowanych(@PathVariable long id_Skaut) {
        List<ZawodnikByIdDTO> listaZawodnikow = obserwowaniZawodnicySkautaService.listaObsZawodnikowSkauta(id_Skaut);
        return ResponseEntity.ok(listaZawodnikow);
    }
}
