package dev.projekt_inzynierski.models.users;

import dev.projekt_inzynierski.models.Klub;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Menadzera;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "Menadzer_klubu")
public class Menadzer_klubu extends Uzytkownik{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_Uzytkownik;


    @OneToOne
    @JoinColumn(name="id_klub")
    private Klub menadzerKlubu;

    @OneToMany
    private List<Obserwowani_Zawodnicy_Menadzera> obserwowaniZawodnicy;

}
