package models;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Kraj_pochodzenia {
    @NotNull
    @Id
    private int id_Kraj;
    @Size(max = 120)
    private String nazwa;
    @Size(max = 120)
    private String region;
}
