package models.users;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Trener {
    // to id ogarniemy później bo musimy zrobić ogarnąć dziedziczenie po user -- Mazur

    @Max(50)
    @NotBlank
    private String licencja_trenera;

    @Max(50)
    @NotBlank
    private String imie;

    @Max(50)
    @NotBlank
    private String nazwisko;


}
