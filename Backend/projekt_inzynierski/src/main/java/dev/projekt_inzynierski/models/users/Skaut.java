package dev.projekt_inzynierski.models.users;

import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Skauta;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "Skaut")
@NoArgsConstructor
@Entity
public class Skaut extends Uzytkownik {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_Uzytkownik;


    @OneToMany(mappedBy = "skaut")
    private List<Obserwowani_Zawodnicy_Skauta> obserwowaniZawodnicy;

}
