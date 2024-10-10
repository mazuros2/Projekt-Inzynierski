package dev.projekt_inzynierski.models;

import dev.projekt_inzynierski.models.users.Menadzer_klubu;
import dev.projekt_inzynierski.models.users.Trener;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "Klub")
public class Klub {

    //dodac polaczenie z trenerem i transferem

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String nazwa_klubu;

    @NotNull
    private LocalDate rok_zalozenia;

    private String logo_url;

    @ManyToOne
    @JoinColumn(name = "id_Ligii", nullable = false)
    private Liga liga;

    @OneToMany(mappedBy = "klub", cascade = CascadeType.ALL)
    private List<Trofeum> trofea;

    @ManyToOne
    @JoinColumn(name="id_trener")
    private Trener trener;

    @ManyToOne
    @JoinColumn(name="id_menadzer_klubu")
    private Menadzer_klubu menadzer_klubu;

    @OneToMany(mappedBy = "klub",cascade = {CascadeType.REMOVE})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Obecny_klub> setObecnyKlub = new HashSet<>();
    //FK keys do dodania
}
