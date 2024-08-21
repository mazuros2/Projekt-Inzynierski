package models.users;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Skaut {

    @Max(50)
    @NotBlank
    private String imie;

    @Max(50)
    @NotBlank
    private String nazwisko;


}
