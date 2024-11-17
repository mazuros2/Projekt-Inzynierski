package dev.projekt_inzynierski.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.projekt_inzynierski.models.users.Uzytkownik;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "Kraj_pochodzenia")
@Entity
@NoArgsConstructor
public class Kraj_pochodzenia {
    //dodawaj do każdego stringa NotBlank
    //do localeDate @NotNull
    //do int możemy dodać po prostu @Min() i jakaś wartość

    //stworzyc jako kompozycje

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_Kraj;

    @NotBlank
    @Size(max = 120)
    private String nazwa;

    @NotBlank
    @Size(max = 120)
    private String region;

    @ManyToMany(mappedBy = "kraj_pochodzenia")
    private Set<Uzytkownik> uzytkownicy = new HashSet<>();

    public Kraj_pochodzenia(String nazwa, String region, Uzytkownik uzytkownik) {
        if(uzytkownik == null){
            throw new IllegalArgumentException("Musi byc podany uzytkownik");
        }
        this.nazwa = nazwa;
        this.region = region;
    }
}
