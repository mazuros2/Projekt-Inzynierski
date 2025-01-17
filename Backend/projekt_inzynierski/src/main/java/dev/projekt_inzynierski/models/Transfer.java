package dev.projekt_inzynierski.models;
import dev.projekt_inzynierski.models.users.Zawodnik;
import dev.projekt_inzynierski.models.Klub;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "Transfer")
@Entity
@NoArgsConstructor
public class Transfer {
    //polaczenie transfer klub transfer zawodnik XX
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private LocalDate data_transferu;

    @NotBlank
    private String status;

    @NotNull
    private int kwota;

    @ManyToOne
    @JoinColumn(name = "id_zawodnik", nullable = false)
    private Zawodnik zawodnik;

    @ManyToOne
    @JoinColumn(name = "id_klub_od",nullable = true)
    private Klub klubOd;

    @ManyToOne
    @JoinColumn(name = "id_klub_do",nullable = false)
    private Klub klubDo;

}
