package models;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class Uzytkownik {
    @NotNull
    @Id
    private int id_Uzytkownik;
    @Size(max = 50)
    private String login;
    @Size(max = 50)
    private String haslo;
    @Size(max = 50)
    @Email
    private String email;
    private int pesel;
    private LocalDate data_Urodzenia;
}
