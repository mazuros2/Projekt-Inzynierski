package dev.projekt_inzynierski.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrofeumDTO {
    private long id;
    private String nazwa;
    private LocalDate data_zdobycia;
}
