package models;

import jakarta.validation.constraints.Size;

public class Zawodnik {
    //id Zawodnik to chyba ma byc id z uzytkownika ale zapisane jako idZawodnik bo FK na diagramie
    @Size(max = 50)
    private String nazwa;
    @Size(max = 50)
    private String nazwisko;
    private int waga;
    private int wzrost;

}
