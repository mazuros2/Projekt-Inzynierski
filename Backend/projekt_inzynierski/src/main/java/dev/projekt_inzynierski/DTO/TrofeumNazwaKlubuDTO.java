package dev.projekt_inzynierski.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrofeumNazwaKlubuDTO {
    private String nazwaTrofeum;
    private String nazwaKlubu;
    private LocalDate dataZdobycia;

}
