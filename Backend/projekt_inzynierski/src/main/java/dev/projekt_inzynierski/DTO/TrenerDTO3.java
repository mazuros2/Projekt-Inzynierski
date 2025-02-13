package dev.projekt_inzynierski.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrenerDTO3 {
        private long id;
        private String imie;
        private String nazwisko;
        private LocalDate dataUrodzenia;
        private String licencjaTrenera;
}
