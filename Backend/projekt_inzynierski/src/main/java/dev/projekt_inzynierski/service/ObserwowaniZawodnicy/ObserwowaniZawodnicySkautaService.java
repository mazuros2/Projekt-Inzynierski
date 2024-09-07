package dev.projekt_inzynierski.service.ObserwowaniZawodnicy;

import dev.projekt_inzynierski.repository.Obserwowani_zawodnicy.ObserwowaniZawodnicySkautaRepository;
import org.springframework.stereotype.Service;

@Service
public class ObserwowaniZawodnicySkautaService {
    private ObserwowaniZawodnicySkautaRepository obserwowaniZawodnicySkautaRepository;

    public ObserwowaniZawodnicySkautaService(ObserwowaniZawodnicySkautaRepository obserwowaniZawodnicySkautaRepository) {
        this.obserwowaniZawodnicySkautaRepository=obserwowaniZawodnicySkautaRepository;
    }
}
