package models.users;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Uzytkownik {

    //dodawaj do każdego stringa NotBlank
    //do localeDate @NotNull
    //do int możemy dodać po prostu @Min() i jakaś wartość

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_Uzytkownik;

    @Size(max = 50)
    @NotBlank
    private String login;

    @Size(max = 50)
    @NotBlank
    private String haslo;

    @Size(max = 50)
    @Email
    private String email;

    @Column(unique=true)
    private int pesel;

    @NotNull
    private LocalDate data_Urodzenia;
}
