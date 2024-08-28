package dev.projekt_inzynierski.models;

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
@Table(name = "Trofeum")
@Entity
@NoArgsConstructor
public class Trofeum {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_Klub", nullable = false)
    private Klub klub;

    @NotBlank
    private String nazwa;
    @NotNull
    private LocalDate data_zdobycia;
}
