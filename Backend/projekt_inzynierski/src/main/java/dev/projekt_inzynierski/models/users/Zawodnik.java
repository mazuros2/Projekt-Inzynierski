package dev.projekt_inzynierski.models.users;

import dev.projekt_inzynierski.models.Badania_lekarskie;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import dev.projekt_inzynierski.models.Pozycja;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@Table(name = "Zawodnik")
@Entity
@NoArgsConstructor
public class Zawodnik {
    //trzeba ogarnac bo to ma byc dziedziczone
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_Uzytkownik;

    @Size(max = 50)
    @NotBlank
    private String nazwa;
    @Size(max = 50)
    @NotBlank
    private String nazwisko;
    @Min(20)
    private int waga;
    @Min(140)
    private int wzrost;

    @ManyToOne
    @JoinColumn(name = "id_Pozycja", nullable = false)
    private Pozycja pozycja;

    @OneToMany(mappedBy = "zawodnik", cascade = CascadeType.ALL)
    private List<Badania_lekarskie> badaniaLekarskie;
}
