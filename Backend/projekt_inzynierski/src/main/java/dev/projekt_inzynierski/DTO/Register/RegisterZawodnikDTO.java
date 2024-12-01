package dev.projekt_inzynierski.DTO.Register;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class RegisterZawodnikDTO {
    private String imie;
    private String nazwisko;
    private String email;
    private String login;
    private String haslo;
    private int pesel;
    private LocalDate dataUrodzenia;
    private Long idKlub;
    private Set<Long> krajePochodzenia;
    private String pozycja;
    private int wzrost;
    private int waga;
}
