package models.users;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public class Menadzer_klubu {

    @Max(50)
    @NotBlank
    private String imie;

    @Max(50)
    @NotBlank
    private String nazwisko;


}
