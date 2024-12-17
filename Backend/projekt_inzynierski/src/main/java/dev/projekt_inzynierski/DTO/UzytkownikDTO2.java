package dev.projekt_inzynierski.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UzytkownikDTO2 {
    private long id;
    private String login;
    private String haslo;
    private String email;
    private String profiloweURL;
}
