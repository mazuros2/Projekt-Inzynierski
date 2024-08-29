package dev.projekt_inzynierski.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import dev.projekt_inzynierski.models.users.Zawodnik;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "Badania_lekarskie")
@Entity
@NoArgsConstructor
public class Badania_lekarskie {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //id zawodnika
    @OneToOne
    @JoinColumn(name = "zawodnik_id", nullable = false)
    private Zawodnik zawodnik;

    @NotNull
    private LocalDate data_Od;
    @NotNull
    private LocalDate data_Do;

    @NotBlank
    private String zatwierdzone_przez;



}
