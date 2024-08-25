package models.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.Badania_lekarskie;
import models.Pozycja;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@Table(name = "Zawodnik")
public class Zawodnik {
    //id Zawodnik to chyba ma byc id z uzytkownika ale zapisane jako idZawodnik bo FK na diagramie

    /*
    * to id ogarniemy później bo musimy zrobić ogarnąć dziedziczenie po user
    *
    * dodawaj do każdego stringa NotBlank --Mazur
    * do localeDate @NotNull
    * do int możemy dodać po prostu @Min() i jakaś wartość
    *                                                               --Mazur
    */

    @Size(max = 50)
    @NotBlank
    private String nazwa;
    @Size(max = 50)
    @NotBlank
    private String nazwisko;
    @Min(20)
    private int waga;
    @Min(140)
    private int wzrost;

    @ManyToOne
    @JoinColumn(name = "id_Pozycja", nullable = false)
    private Pozycja pozycja;

    @OneToMany(mappedBy = "zawodnik", cascade = CascadeType.ALL)
    private List<Badania_lekarskie> badaniaLekarskie;
}
