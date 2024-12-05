package dev.projekt_inzynierski.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PozycjaDTO {
    private long id_Pozycja;
    private String nazwa_pozycji;
    private String obszar_pozycji;
}
