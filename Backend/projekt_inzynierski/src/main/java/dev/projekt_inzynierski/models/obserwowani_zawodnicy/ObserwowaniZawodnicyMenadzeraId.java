package dev.projekt_inzynierski.models.obserwowani_zawodnicy;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Embeddable
public class ObserwowaniZawodnicyMenadzeraId implements Serializable {

    private int id_Menadzer;
    private int id_Zawodnik;
}
