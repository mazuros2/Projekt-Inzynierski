package dev.projekt_inzynierski.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZawodnikDTO2 {
    private Long id;
    private String imie;
    private String nazwisko;
    private Set<KrajPochodzeniaDTO> krajPochodzenia;
    private PozycjaDTO pozycja;
    private String obecnyKlub;
}
