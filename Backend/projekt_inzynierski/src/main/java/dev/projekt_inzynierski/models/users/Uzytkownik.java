package dev.projekt_inzynierski.models.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import dev.projekt_inzynierski.models.Kraj_pochodzenia;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Uzytkownik")
@Entity
public class Uzytkownik {

    //dodawaj do każdego stringa NotBlank
    //do localeDate @NotNull
    //do int możemy dodać po prostu @Min() i jakaś wartość

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_Uzytkownik;

    @Size(max = 50)
    @NotBlank
    private String login;

    @Size(max = 50)
    @NotBlank
    private String haslo;

    @Size(max = 50)
    @Email
    private String email;

    @Size(max = 50)
    @NotBlank
    private String imie;

    @Size(max = 50)
    @NotBlank
    private String nazwisko;

    @Column(unique=true)
    private int pesel;

    @NotNull
    private LocalDate data_Urodzenia;

    @ManyToOne
    @JoinColumn(name = "id_Kraj_pochodzenia", nullable = false)
    private Kraj_pochodzenia kraj_pochodzenia;

}
