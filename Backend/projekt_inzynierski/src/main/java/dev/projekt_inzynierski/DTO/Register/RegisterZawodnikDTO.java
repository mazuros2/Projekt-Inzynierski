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
    private LocalDate dataUrodzenia;
    private String pesel;
    private int wzrost;
    private int waga;
    private Long pozycjaId;
    private Long klubId;
    private Set<Long> krajePochodzenia;
}
