package dev.projekt_inzynierski.models;

import dev.projekt_inzynierski.models.users.Menadzer_klubu;
import dev.projekt_inzynierski.models.users.Skaut;
import dev.projekt_inzynierski.models.users.Trener;
import dev.projekt_inzynierski.models.users.Zawodnik;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "Klub")
public class Klub {
    @Transient
    private Set<Zawodnik> zawodnicy = new HashSet<>();
    public Set<Zawodnik> getZawodnicy() {
        return setObecnyKlub.stream()
                .filter(ob -> ob.getData_Do() == null)
                .map(Obecny_klub::getZawodnik)
                .collect(Collectors.toSet());
    }
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
    @JoinColumn(name="id_skaut")
    private Skaut skaut;

    @ManyToOne
    @JoinColumn(name="id_menadzer_klubu")
    private Menadzer_klubu menadzer_klubu;

    @OneToMany(mappedBy = "klub",cascade = {CascadeType.REMOVE})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Obecny_klub> setObecnyKlub = new HashSet<>();


    public void dodajObecnyKlub(Obecny_klub obecnyKlub){
        if(setObecnyKlub.contains(obecnyKlub)){
            throw new IllegalArgumentException("To uczestnictwo juz istnieje!");
        }
        if(obecnyKlub.getKlub()==this){
            setObecnyKlub.add(obecnyKlub);

        }else {
            throw new IllegalArgumentException("Ten obecny klub jest juz dodany");
        }
    }

    public void dodajZawodnika(Zawodnik zawodnik, LocalDate data_Od){
        if(zawodnik==null){
            throw new IllegalArgumentException("zawodnik nie moze byc null!");
        }
        Obecny_klub ob = new Obecny_klub(zawodnik,this,data_Od,null);
        System.out.println(ob.getKlub());
        zawodnik.dodajObecnyKlub(ob);
        this.dodajObecnyKlub(ob);
        zawodnicy.add(zawodnik);
    }
    public void usunZawodnika(Zawodnik zawodnik, LocalDate data_do) {
        if (zawodnik == null) {
            throw new IllegalArgumentException("Zawodnik nie moze byc null");
        }
        for (Obecny_klub ob : setObecnyKlub) {
            if (ob.getZawodnik().equals(zawodnik) && ob.getData_Do() == null) {
                ob.setData_Do(data_do);
                zawodnicy.remove(zawodnik);
                zawodnik.usunObecnyKlub(ob,data_do);
                break;
            }
        }
    }
}
