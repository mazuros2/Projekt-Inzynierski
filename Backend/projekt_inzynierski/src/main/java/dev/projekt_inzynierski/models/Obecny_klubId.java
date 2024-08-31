package dev.projekt_inzynierski.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class Obecny_klubId implements Serializable {
    @Column(name="zawodnik_id")
    private long zawodnik_id;
    @Column(name = "klub_id")
    private long klub_id;
}
