package dev.projekt_inzynierski.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenadzerDTO {
    private String imie;
    private String nazwisko;
    private LocalDate dataUrodzenia;
    private Set<String> krajePochodzenia;
    private String klub;
}
