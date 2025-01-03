package dev.projekt_inzynierski.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.projekt_inzynierski.models.users.Zawodnik;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


//@Setter
//@Builder

@Table(name = "Obecny_klub")
@Entity
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Obecny_klub implements java.io.Serializable{

    //embededidjakby nie dzialalo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_obecny_klub;

    @ManyToOne
    @JoinColumn(name = "zawodnik_id", nullable = false)
    private Zawodnik zawodnik;

    @ManyToOne
    @JoinColumn(name="klub_id",nullable = false)
    private Klub klub;

    @NotNull
    private LocalDate data_Od;
    @Nullable
    private LocalDate data_Do;
//ta walidacje trzeba zrobic tez tak jak na masach trzeba bylo przez stworzenie customowych @
    public void setData_Od(LocalDate data_Od) {
        if(data_Od.isAfter(data_Do)){
            throw new IllegalArgumentException("zla data dolaczenia");
        }
        this.data_Od = data_Od;
    }

    public void setData_Do(LocalDate data_Do) {
        if (data_Do.isBefore(data_Od)){
            throw new IllegalArgumentException("zla data odejscia");
        }
        this.data_Do = data_Do;
    }
    public Obecny_klub(Zawodnik zawodnik, Klub klub, LocalDate data_Od, LocalDate data_Do) {
        this.zawodnik = zawodnik;
        this.klub = klub;
        this.data_Od = data_Od;
        this.data_Do = data_Do;
    }


}
