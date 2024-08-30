package dev.projekt_inzynierski.models.obserwowani_zawodnicy;

import dev.projekt_inzynierski.models.users.Skaut;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import dev.projekt_inzynierski.models.users.Menadzer_klubu;
import dev.projekt_inzynierski.models.users.Zawodnik;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "Obserwowani_Zawodnicy_Menadzera")
public class Obserwowani_Zawodnicy_Menadzera {

    @EmbeddedId
    private ObserwowaniZawodnicyMenadzeraId id;

    @ManyToOne
    @JoinColumn(name = "id_Zawodnik")
    private Zawodnik zawodnik;

    @ManyToOne
    @JoinColumn(name = "id_Menadzer_klubu")
    private Menadzer_klubu menadzer_klubu;
}
