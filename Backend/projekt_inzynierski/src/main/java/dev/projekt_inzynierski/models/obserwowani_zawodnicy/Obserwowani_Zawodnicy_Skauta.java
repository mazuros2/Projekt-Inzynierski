package dev.projekt_inzynierski.models.obserwowani_zawodnicy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import dev.projekt_inzynierski.models.users.Skaut;
import dev.projekt_inzynierski.models.users.Zawodnik;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "Obserwowani_Zawodnicy_Skauta")
public class Obserwowani_Zawodnicy_Skauta {

    @EmbeddedId
    private ObserwowaniZawodnicySkautaId id;

    @ManyToOne
    @JoinColumn(name = "id_Zawodnik", insertable = false, updatable = false)
    private Zawodnik zawodnik;

    @ManyToOne
    @JoinColumn(name = "id_Skaut", insertable = false, updatable = false)
    private Skaut skaut;

}
