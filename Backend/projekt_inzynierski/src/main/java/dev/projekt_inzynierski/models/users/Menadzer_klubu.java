package dev.projekt_inzynierski.models.users;

import dev.projekt_inzynierski.models.Klub;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Menadzera;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "Menadzer_klubu")
public class Menadzer_klubu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_Uzytkownik;

    @NotBlank
    private String imie;

    @NotBlank
    private String nazwisko;

    @OneToOne
    @JoinColumn(name="id_klub")
    private Klub menadzerKlubu;

    @OneToMany(mappedBy = "menadzer")
    private List<Obserwowani_Zawodnicy_Menadzera> obserwowaniZawodnicy;

}
