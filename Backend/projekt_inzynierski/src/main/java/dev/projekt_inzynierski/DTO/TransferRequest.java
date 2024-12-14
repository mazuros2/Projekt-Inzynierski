package dev.projekt_inzynierski.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {
    private int kwota;
    private long idZawodnik;
    private long idKlubOd;
    private long idKlubDo;
}

