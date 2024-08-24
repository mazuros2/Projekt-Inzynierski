package models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "Badania_lekarskie")
public class Badania_lekarskie {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //id zawodnika

    @NotNull
    private LocalDate data_Od;
    @NotNull
    private LocalDate data_Do;

    @NotBlank
    private String zatwierdzone_przez;


}
