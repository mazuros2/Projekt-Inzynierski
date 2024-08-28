package dev.projekt_inzynierski.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "Klub")
public class Klub {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String nazwa_klubu;

    @NotNull
    private LocalDate rok_zalozenia;


    @ManyToOne
    @JoinColumn(name = "id_Ligii", nullable = false)
    private Liga liga;

    @OneToMany(mappedBy = "klub", cascade = CascadeType.ALL)
    private List<Trofeum> trofea;


    //FK keys do dodania
}
