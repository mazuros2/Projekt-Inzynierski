package dev.projekt_inzynierski.models;

import dev.projekt_inzynierski.models.users.Zawodnik;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "Obecny_klub")
@Entity
@NoArgsConstructor
public class Obecny_klub {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //klub FK PK
    //Zawodnik FK PK
    @ManyToOne
    @JoinColumn(name = "zawodnik_id", nullable = false)
    private Zawodnik zawodnik;


    @NotNull
    private LocalDate data_Od;

    private LocalDate data_Do;

}
