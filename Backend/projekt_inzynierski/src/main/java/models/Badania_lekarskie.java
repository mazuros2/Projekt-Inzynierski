package models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.users.Zawodnik;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "Badania_lekarskie")
public class Badania_lekarskie {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //id zawodnika
    @ManyToOne
    @JoinColumn(name = "id_Zawodnik", nullable = false)
    private Zawodnik zawodnik;

    @NotNull
    private LocalDate data_Od;
    @NotNull
    private LocalDate data_Do;

    @NotBlank
    private String zatwierdzone_przez;


}
