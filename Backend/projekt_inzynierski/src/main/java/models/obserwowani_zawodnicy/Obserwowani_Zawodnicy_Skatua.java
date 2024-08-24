package models.obserwowani_zawodnicy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.users.Menadzer_klubu;
import models.users.Skaut;
import models.users.Zawodnik;

@Getter
@Setter
@AllArgsConstructor
public class Obserwowani_Zawodnicy_Skatua {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @MapsId("zawodnikId")
    @JoinColumn(name = "id_Zawodnik", nullable = false)
    private Zawodnik zawodnik;

    @ManyToOne
    @MapsId("skautId")
    @JoinColumn(name = "id_Skaut", nullable = false)
    private Skaut skaut;

}
