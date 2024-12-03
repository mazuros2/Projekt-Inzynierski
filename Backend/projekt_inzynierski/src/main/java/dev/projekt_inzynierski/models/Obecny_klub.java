package dev.projekt_inzynierski.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.projekt_inzynierski.models.users.Zawodnik;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "Obecny_klub")
@Entity
@NoArgsConstructor
public class Obecny_klub implements java.io.Serializable{

    //mozna te polaczenia zrobic tez w tej klasie obecnyklubId
    @EmbeddedId
    private Obecny_klubId id;
    @ManyToOne
    @JoinColumn(name = "zawodnik_id", nullable = false, insertable = false, updatable = false)
    private Zawodnik zawodnik;

    @ManyToOne
    @JoinColumn(name="klub_id",nullable = false, insertable = false, updatable = false)
    private Klub klub;

    @NotNull
    private LocalDate data_Od;

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


}
