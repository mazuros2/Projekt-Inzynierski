package models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Table(name = "Kraj_pochodzenia")
public class Kraj_pochodzenia {
    //dodawaj do każdego stringa NotBlank
    //do localeDate @NotNull
    //do int możemy dodać po prostu @Min() i jakaś wartość

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_Kraj;

    @NotBlank
    @Size(max = 120)
    private String nazwa;

    @NotBlank
    @Size(max = 120)
    private String region;
}
