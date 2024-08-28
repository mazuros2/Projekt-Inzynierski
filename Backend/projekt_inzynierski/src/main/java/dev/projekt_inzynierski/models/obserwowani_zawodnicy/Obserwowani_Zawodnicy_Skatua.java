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
public class Obserwowani_Zawodnicy_Skatua {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_Zawodnik", nullable = false)
    private Zawodnik zawodnik;

    @ManyToOne
    @JoinColumn(name = "id_Skaut", nullable = false)
    private Skaut skaut;

}
