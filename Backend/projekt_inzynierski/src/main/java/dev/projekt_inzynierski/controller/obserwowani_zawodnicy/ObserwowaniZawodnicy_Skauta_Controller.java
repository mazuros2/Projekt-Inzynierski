package dev.projekt_inzynierski.controller.obserwowani_zawodnicy;

import dev.projekt_inzynierski.service.ObserwowaniZawodnicy.ObserwowaniZawodnicySkautaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/skautingZawodnika/skaut")
public class ObserwowaniZawodnicy_Skauta_Controller {

    private final ObserwowaniZawodnicySkautaService obserwowaniZawodnicySkautaService;

    public ObserwowaniZawodnicy_Skauta_Controller(ObserwowaniZawodnicySkautaService obserwowaniZawodnicySkautaService) {
        this.obserwowaniZawodnicySkautaService = obserwowaniZawodnicySkautaService;
    }

    @RequestMapping("/dodajZawodnika")
    public ResponseEntity<String> dodajDoListyObserwowanych(@RequestBody int id_Skaut, @RequestBody int id_Zawodnik){
        obserwowaniZawodnicySkautaService.dodanieZawodnikaDoListyObs(id_Skaut,id_Zawodnik);
        return ResponseEntity.ok("Zawodnik został dodany do listy obserwowanych!");
    }



}
