package dev.projekt_inzynierski.repository.User;

import dev.projekt_inzynierski.DTO.KlubByIdDTO;
import dev.projekt_inzynierski.DTO.ZawodnikByIdDTO;
import dev.projekt_inzynierski.DTO.ZawodnikDTO;
import dev.projekt_inzynierski.DTO.ZawodnikIdKlubDTO;
import dev.projekt_inzynierski.models.users.Zawodnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZawodnikRepository extends JpaRepository<Zawodnik,Long> {


    Optional<Zawodnik> findById(long id);

    @Query("SELECT new dev.projekt_inzynierski.DTO.ZawodnikDTO(z.id_Uzytkownik, z.imie, z.nazwisko, z.pozycja.nazwa_pozycji) " +
            "FROM Zawodnik z " +
            "WHERE LOWER(z.imie) LIKE LOWER(:text) " +
            "   OR LOWER(z.nazwisko) LIKE LOWER(:text)")
    List<ZawodnikDTO> findZawodnikByText(@Param("text") String text);

    @Query("SELECT new dev.projekt_inzynierski.DTO.ZawodnikIdKlubDTO(k.id) FROM Obecny_klub ob JOIN Klub k ON k.id=ob.klub.id WHERE ob.zawodnik=:id_zawodnik AND ob.data_Do=null ")
    ZawodnikIdKlubDTO znajdzIdKlubuZawodnika(long id_zawodnik);
}
