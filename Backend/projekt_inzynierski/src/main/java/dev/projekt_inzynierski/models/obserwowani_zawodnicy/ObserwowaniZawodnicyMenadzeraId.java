package dev.projekt_inzynierski.models.obserwowani_zawodnicy;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ObserwowaniZawodnicyMenadzeraId implements Serializable {

    private Long id_Menadzer;
    private Long id_Zawodnik;
}
