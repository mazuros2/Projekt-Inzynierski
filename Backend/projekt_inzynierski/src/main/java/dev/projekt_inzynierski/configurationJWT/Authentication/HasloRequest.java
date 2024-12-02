package dev.projekt_inzynierski.configurationJWT.Authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HasloRequest {
    private String login;
    private String stareHaslo;
    private String noweHaslo;
}
