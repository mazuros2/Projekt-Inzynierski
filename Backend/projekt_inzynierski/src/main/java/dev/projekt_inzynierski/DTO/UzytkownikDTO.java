package dev.projekt_inzynierski.DTO;

import dev.projekt_inzynierski.configurationJWT.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UzytkownikDTO {

    private long id_Uzytkownik;
    private String login;
    private String haslo;
    private String email;
    private String imie;
    private String nazwisko;
    private String pesel;
    private Role role;

}
