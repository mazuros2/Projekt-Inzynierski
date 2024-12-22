package dev.projekt_inzynierski.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
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

    @Size(min = 3, max = 20)
    @Column(unique = true)
    private String login;

    @Size(min = 6, max = 100)
    private String haslo;

    @Email
    private String email;
    private String profiloweURL;
}
