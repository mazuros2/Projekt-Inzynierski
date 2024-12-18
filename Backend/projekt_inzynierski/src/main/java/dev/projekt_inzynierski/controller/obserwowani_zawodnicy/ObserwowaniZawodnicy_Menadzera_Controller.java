package dev.projekt_inzynierski.controller.obserwowani_zawodnicy;
import dev.projekt_inzynierski.DTO.ZawodnikDTO2;
import dev.projekt_inzynierski.configurationJWT.JWTService;
import dev.projekt_inzynierski.service.Obserwowani_Zawodnicy.ObserwowaniZawodnicyMenadzeraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skautingZawodnika/menadzer")
public class ObserwowaniZawodnicy_Menadzera_Controller {

    private final ObserwowaniZawodnicyMenadzeraService obserwowaniZawodnicyMenadzeraService;
    private final JWTService jwtService;

    public ObserwowaniZawodnicy_Menadzera_Controller(ObserwowaniZawodnicyMenadzeraService obserwowaniZawodnicyMenadzeraService, JWTService jwtService) {
        this.obserwowaniZawodnicyMenadzeraService = obserwowaniZawodnicyMenadzeraService;
        this.jwtService = jwtService;
    }

    @PostMapping("/dodajZawodnika/{idZawodnika}")
    public ResponseEntity<String> dodajZawodnikaDoObserwowanych(@PathVariable Long idZawodnika, @RequestHeader(name = "Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtService.extractUserId(token);
        obserwowaniZawodnicyMenadzeraService.dodanieZawodnikaDoListyObs(userId, idZawodnika);

        return ResponseEntity.ok("Zawodnik został dodany do obserwowanych.");
    }

    @GetMapping("/listaZawodnikow")
    public ResponseEntity<List<ZawodnikDTO2>> wyswietlObserwowanychZawodnikow(@RequestHeader(name = "Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtService.extractUserId(token);
        List<ZawodnikDTO2> zawodnicy = obserwowaniZawodnicyMenadzeraService.GetListaObserwowanychZawodnikow(userId);

        return ResponseEntity.ok(zawodnicy);
    }


}
