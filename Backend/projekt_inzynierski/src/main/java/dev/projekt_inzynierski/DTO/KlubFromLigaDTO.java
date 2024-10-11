package dev.projekt_inzynierski.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KlubFromLigaDTO {
    private Long id;
    private String nazwaKlubu;
    private String logo_url;
}
