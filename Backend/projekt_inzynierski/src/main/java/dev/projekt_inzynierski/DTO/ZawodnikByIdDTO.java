package dev.projekt_inzynierski.DTO;

import dev.projekt_inzynierski.models.Badania_lekarskie;
import dev.projekt_inzynierski.models.Obecny_klub;
import dev.projekt_inzynierski.models.Transfer;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Menadzera;
import dev.projekt_inzynierski.models.obserwowani_zawodnicy.Obserwowani_Zawodnicy_Skauta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZawodnikByIdDTO {
    private long id_Uzytkownik;
    private String nazwa;
    private String nazwisko;
    private int waga;
    private int wzrost;
    private long id_Pozycja;
    private String pozycja;
//    private long obecny_klub;
//    private String nazwa_klubu;






}
