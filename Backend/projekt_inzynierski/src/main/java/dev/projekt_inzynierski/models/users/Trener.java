package dev.projekt_inzynierski.models.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Table(name = "Trener")
@Entity
@NoArgsConstructor
public class Trener {
    // to id ogarniemy później bo musimy zrobić ogarnąć dziedziczenie po user -- Mazur

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_Uzytkownik;


    @NotBlank
    private String licencja_trenera;


    @NotBlank
    private String imie;


    @NotBlank
    private String nazwisko;


}
