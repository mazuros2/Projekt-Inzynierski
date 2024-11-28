package dev.projekt_inzynierski.controller.obserwowani_zawodnicy;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Menadzera;
import dev.projekt_inzynierski.service.ObserwowaniZawodnicy.ObserwowaniZawodnicyMenadzeraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/skautingZawodnika/menadzer")
public class ObserwowaniZawodnicy_Menadzera_Controller {

    private final ObserwowaniZawodnicyMenadzeraService obserwowaniZawodnicyMenadzeraService;

    public ObserwowaniZawodnicy_Menadzera_Controller(ObserwowaniZawodnicyMenadzeraService obserwowaniZawodnicyMenadzeraService) {
        this.obserwowaniZawodnicyMenadzeraService = obserwowaniZawodnicyMenadzeraService;
    }

    @RequestMapping("/dodajZawodnika")
    public ResponseEntity<String> dodajDoListyObserwowanych(@RequestBody int id_Menadzer, @RequestBody int id_Zawodnik){
        obserwowaniZawodnicyMenadzeraService.dodanieZawodnikaDoListyObs(id_Menadzer,id_Zawodnik);
        return ResponseEntity.ok("Zawodnik został dodany do listy obserwowanych!");
    }

}
