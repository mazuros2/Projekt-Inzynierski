package dev.projekt_inzynierski.models.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.projekt_inzynierski.models.Badania_lekarskie;
import dev.projekt_inzynierski.models.Obecny_klub;
import dev.projekt_inzynierski.models.Transfer;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Menadzera;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Skauta;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import dev.projekt_inzynierski.models.Pozycja;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Data
@SuperBuilder
@AllArgsConstructor
@Table(name = "Zawodnik")
@Entity
@NoArgsConstructor
public class Zawodnik extends Uzytkownik{
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_Uzytkownik;

    @Min(20)
    private int waga;
    @Min(140)
    private int wzrost;

    @ManyToOne
    @JoinColumn(name = "id_Pozycja", nullable = false)
    private Pozycja pozycja;

    @OneToOne(mappedBy = "zawodnik", cascade = CascadeType.ALL, optional = true)
    private Badania_lekarskie badania_lekarskie;

    @OneToMany(mappedBy = "zawodnik", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transfer> transfery = new ArrayList<>();

    @OneToMany(mappedBy = "zawodnik", cascade = {CascadeType.REMOVE})
    @ToString.Exclude
    @JsonManagedReference
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<Obecny_klub> obecny_klub= new HashSet<>();

    @OneToMany(mappedBy = "zawodnik")
    private List<Obserwowani_Zawodnicy_Skauta> obserwowaniPrzezSkauta;

    @OneToMany(mappedBy = "zawodnik")
    private List<Obserwowani_Zawodnicy_Menadzera> obserwowaniPrzezMenadzera;

    @ElementCollection
    @CollectionTable(name = "poprzednie_kluby", joinColumns = @JoinColumn(name = "zawodnik_id"))
    private List<String> listaPoprzednichKlubow = new ArrayList<>();



    public void dodajObecnyKlub(Obecny_klub ob) {
        System.out.println(ob.getData_Do());
        this.obecny_klub.add(ob);
        if (ob.getData_Do() != null) {
            this.listaPoprzednichKlubow.add(ob.getKlub().getNazwa_klubu());
        }
    }
    public void usunObecnyKlub(Obecny_klub ob, LocalDate data_do) {
        if (obecny_klub.contains(ob)) {
            if (ob.getData_Do() == null) {
                ob.setData_Do(data_do);
                this.listaPoprzednichKlubow.add(ob.getKlub().getNazwa_klubu());
            }
        }
    }


}
