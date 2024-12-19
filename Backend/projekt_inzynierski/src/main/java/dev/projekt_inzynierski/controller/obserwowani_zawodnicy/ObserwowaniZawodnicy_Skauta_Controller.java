package dev.projekt_inzynierski.controller.obserwowani_zawodnicy;

import dev.projekt_inzynierski.DTO.ZawodnikDTO2;
import dev.projekt_inzynierski.configurationJWT.JWTService;
import dev.projekt_inzynierski.service.Obserwowani_Zawodnicy.ObserwowaniZawodnicySkautaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skautingZawodnika/skaut")
public class ObserwowaniZawodnicy_Skauta_Controller {

    private final ObserwowaniZawodnicySkautaService obserwowaniZawodnicySkautaService;
    private final JWTService jwtService;

    public ObserwowaniZawodnicy_Skauta_Controller(ObserwowaniZawodnicySkautaService obserwowaniZawodnicySkautaService, JWTService jwtService) {
        this.obserwowaniZawodnicySkautaService = obserwowaniZawodnicySkautaService;
        this.jwtService = jwtService;
    }

    @PostMapping("/dodajZawodnika/{idZawodnika}")
    public ResponseEntity<String> dodajZawodnikaDoObserwowanych(@PathVariable Long idZawodnika, @RequestHeader(name = "Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtService.extractUserId(token);
        obserwowaniZawodnicySkautaService.dodanieZawodnikaDoListyObs(userId, idZawodnika);

        return ResponseEntity.ok("Zawodnik został dodany do obserwowanych.");
    }

    @DeleteMapping("/usunZawodnika/{idZawodnika}")
    public ResponseEntity<String> usunZawodnikazObserwowanych(@PathVariable long idZawodnika, @RequestHeader(name = "Authorization") String authHeader){
        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtService.extractUserId(token);
        obserwowaniZawodnicySkautaService.usunZawodnikazListy(userId,idZawodnika);

        return ResponseEntity.ok("Zawodnik został usunięty z obserwowanych.");
    }

    @GetMapping("/listaZawodnikow")
    public ResponseEntity<List<ZawodnikDTO2>> wyswietlObserwowanychZawodnikow(@RequestHeader(name = "Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtService.extractUserId(token);
        List<ZawodnikDTO2> zawodnicy = obserwowaniZawodnicySkautaService.GetListaObserwowanychZawodnikow(userId);

        return ResponseEntity.ok(zawodnicy);
    }

}
