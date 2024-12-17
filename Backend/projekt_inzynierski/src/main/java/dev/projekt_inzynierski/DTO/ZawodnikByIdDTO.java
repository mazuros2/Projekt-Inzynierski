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

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZawodnikByIdDTO {

    private String imie;
    private String nazwisko;
    private LocalDate dataUrodzenia;
    private Set<String> krajePochodzenia;
    private String pozycja;
    private String obecnyKlub;
    private int wzrost;
    private int waga;
    private String profiloweURL;
}
