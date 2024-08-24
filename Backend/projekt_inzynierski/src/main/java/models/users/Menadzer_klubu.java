package models.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Table(name = "Menadzer_klubu")
public class Menadzer_klubu {

    @Max(50)
    @NotBlank
    private String imie;

    @Max(50)
    @NotBlank
    private String nazwisko;


}
