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
    private Long id_klub_od;
    private Long id_klub_do;
    private String nazwa_klub_od;
    private String nazwa_klub_do;
    private String imieMen;
    private String nazwiskoMen;
}
