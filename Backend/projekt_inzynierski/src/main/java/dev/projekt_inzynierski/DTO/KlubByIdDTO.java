package dev.projekt_inzynierski.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KlubByIdDTO {
    private Long id;
    private String nazwaKlubu;
    private LocalDate rokZalozenia;
    private String logo_url;
    private Long ligaId;
    private String ligaNazwaLigi;
}


