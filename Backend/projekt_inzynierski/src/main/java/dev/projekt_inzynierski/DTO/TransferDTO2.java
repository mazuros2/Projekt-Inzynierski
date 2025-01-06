package dev.projekt_inzynierski.DTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferDTO2 {
    private long zawodnikId;
    private String imieZawodnika;
    private String nazwiskoZawodnika;
    private long klubOdId;
    private String nazwaKlubuOd;
    private long klubDoId;
    private String nazwaKlubuDo;
    private int kwota;

}
