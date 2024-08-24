package models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pozycja {
    //dodawaj do każdego stringa NotBlank
    //do localeDate @NotNull
    //do int możemy dodać po prostu @Min() i jakaś wartość

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_Pozycja;
    @Size(max = 50)
    @NotBlank
    private String nazwa_pozycji;
    @Size(max = 50)
    @NotBlank
    private String obszar_pozycji;
}
