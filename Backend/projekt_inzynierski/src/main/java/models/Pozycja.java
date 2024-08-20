package models;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Pozycja {
    @Id
    private int id_Pozycja;
    @Size(max = 50)
    private String nazwa_pozycji;
    @Size(max = 50)
    private String obszar_pozycji;
}
