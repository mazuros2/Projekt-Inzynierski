package dev.projekt_inzynierski.configurationJWT.Authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String imie;
    private String nazwisko;
    private String email;
    private String login;
    private String haslo;
    private String pesel;
    private LocalDate dataUrodzenia;
}