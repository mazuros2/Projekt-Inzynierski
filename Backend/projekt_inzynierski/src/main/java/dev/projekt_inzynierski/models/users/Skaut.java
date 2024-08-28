package dev.projekt_inzynierski.models.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
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
@Table(name = "Skaut")
@NoArgsConstructor
@Entity
public class Skaut {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_Uzytkownik;


    @NotBlank
    private String imie;

    @Size(max = 50)
    @NotBlank
    private String nazwisko;


}
