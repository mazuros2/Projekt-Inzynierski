package dev.projekt_inzynierski.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KrajPochodzeniaDTO {
    private Long id_Kraj;
    private String nazwa;
    private String region;
}
