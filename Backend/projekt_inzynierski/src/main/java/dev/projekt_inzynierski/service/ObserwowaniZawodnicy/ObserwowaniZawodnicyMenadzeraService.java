package dev.projekt_inzynierski.service.ObserwowaniZawodnicy;

import dev.projekt_inzynierski.repository.Obserwowani_zawodnicy.ObserwowaniZawodnicyMenadzeraRepository;
import org.springframework.stereotype.Service;

@Service
public class ObserwowaniZawodnicyMenadzeraService {
    private ObserwowaniZawodnicyMenadzeraRepository obserwowaniZawodnicyMenadzeraRepository;

    public ObserwowaniZawodnicyMenadzeraService(ObserwowaniZawodnicyMenadzeraRepository obserwowaniZawodnicyMenadzeraRepository) {
        this.obserwowaniZawodnicyMenadzeraRepository=obserwowaniZawodnicyMenadzeraRepository;
    }
}
