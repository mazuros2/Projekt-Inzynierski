package dev.projekt_inzynierski.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.projekt_inzynierski.models.users.Uzytkownik;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_uzytkownik",nullable = false,updatable = false)
    @JsonBackReference
    private Uzytkownik uzytkownik;

    public Kraj_pochodzenia(String nazwa, String region, Uzytkownik uzytkownik) {
        if(uzytkownik == null){
            throw new IllegalArgumentException("Musi byc podany uzytkownik");
        }
        this.nazwa = nazwa;
        this.region = region;
    }
}
