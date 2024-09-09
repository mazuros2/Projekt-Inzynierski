package dev.projekt_inzynierski.repository.User;

import dev.projekt_inzynierski.DTO.KlubByIdDTO;
import dev.projekt_inzynierski.DTO.ZawodnikByIdDTO;
import dev.projekt_inzynierski.models.users.Zawodnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ZawodnikRepository extends JpaRepository<Zawodnik,Long> {
    @Query("SELECT new dev.projekt_inzynierski.DTO.ZawodnikByIdDTO(" +
            "z.id_Uzytkownik,z.nazwa, z.nazwisko, z.waga, " +
            "z.wzrost,z.pozycja.id_Pozycja, z.pozycja.nazwa_pozycji) " +
            "FROM Zawodnik z WHERE z.id_Uzytkownik = :id")
    ZawodnikByIdDTO findZawodnikById(@Param("id") long id);
}
