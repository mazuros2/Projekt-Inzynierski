package dev.projekt_inzynierski.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZawodnikDTO {
    private Long id;
    private String imie;
    private String nazwisko;
    private String pozycja;
    //xd
}
