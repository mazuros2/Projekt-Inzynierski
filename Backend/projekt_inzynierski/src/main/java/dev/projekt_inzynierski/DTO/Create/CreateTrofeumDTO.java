package dev.projekt_inzynierski.DTO.Create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTrofeumDTO {
    private Long klubId;
    private String nazwa;
    private LocalDate dataZdobycia;
}
