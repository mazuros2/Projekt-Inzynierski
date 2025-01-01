package dev.projekt_inzynierski.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferDTO {
    private long id;
    private LocalDate data_transferu;
    private String status;
    private int kwota;
    private String nazwa_klubu;
}
