package dev.projekt_inzynierski.models.users;

import dev.projekt_inzynierski.models.Klub;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@Table(name = "Trener")
@Entity
@NoArgsConstructor
public class Trener extends Uzytkownik{
    // to id ogarniemy później bo musimy zrobić ogarnąć dziedziczenie po user -- Mazur
    //dodac polaczenie z klubem
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_Uzytkownik;


    @NotBlank
    private String licencja_trenera;


    @OneToOne
    @JoinColumn(name="id_klub")
    private Klub trenerKlub;

}
