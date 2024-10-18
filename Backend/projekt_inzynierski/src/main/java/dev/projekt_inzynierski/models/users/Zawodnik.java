package dev.projekt_inzynierski.models.users;

import dev.projekt_inzynierski.models.Badania_lekarskie;
import dev.projekt_inzynierski.models.Obecny_klub;
import dev.projekt_inzynierski.models.Transfer;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Menadzera;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Skauta;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import dev.projekt_inzynierski.models.Pozycja;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@Table(name = "Zawodnik")
@Entity
@NoArgsConstructor
public class Zawodnik extends Uzytkownik{
    //trzeba ogarnac bo to ma byc dziedziczone
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_Uzytkownik;

    //@Size(max = 50)
   // @NotBlank
    @Min(20)
    private int waga;
    @Min(140)
    private int wzrost;

    @ManyToOne
    @JoinColumn(name = "id_Pozycja", nullable = false)
    private Pozycja pozycja;

    @OneToOne(mappedBy = "zawodnik", cascade = CascadeType.ALL, optional = true)
    private Badania_lekarskie badania_lekarskie;

    @OneToOne(mappedBy = "zawodnik", cascade = CascadeType.ALL, optional = true)
    private Transfer transfer;

    @OneToMany(mappedBy = "zawodnik", cascade = CascadeType.ALL)
    private List<Obecny_klub> obecny_klub;

    @OneToMany(mappedBy = "zawodnik")
    private List<Obserwowani_Zawodnicy_Skauta> obserwowaniPrzezSkauta;

    @OneToMany(mappedBy = "zawodnik")
    private List<Obserwowani_Zawodnicy_Menadzera> obserwowaniPrzezMenadzera;

}
