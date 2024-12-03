package dev.projekt_inzynierski.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Obecny_klubId implements Serializable {
    @Column(name="zawodnik_id")
    private long zawodnik_id;
    @Column(name = "klub_id")
    private long klub_id;

}
