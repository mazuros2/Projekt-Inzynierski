package dev.projekt_inzynierski.models;

import dev.projekt_inzynierski.models.users.Zawodnik;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import models.users.Zawodnik;

import java.util.List;


@Data
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Pozycja")
public class Pozycja {
    //dodawaj do każdego stringa NotBlank
    //do localeDate @NotNull
    //do int możemy dodać po prostu @Min() i jakaś wartość

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_Pozycja;
    @Size(max = 50)
    @NotBlank
    private String nazwa_pozycji;
    @Size(max = 50)
    @NotBlank
    private String obszar_pozycji;

    @OneToMany(mappedBy = "pozycja", cascade = CascadeType.ALL)
    private List<Zawodnik> zawodnicy;
}
