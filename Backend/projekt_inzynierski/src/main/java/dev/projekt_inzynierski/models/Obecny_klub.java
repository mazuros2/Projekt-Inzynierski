package dev.projekt_inzynierski.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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

    @NotNull
    private LocalDate data_Od;

    private LocalDate data_Do;

}
