package dev.projekt_inzynierski.models.obserwowani_zawodnicy;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class ObserwowaniZawodnicySkautaId implements Serializable {

    private int idSkaut;
    private int idZawodnik;
}
