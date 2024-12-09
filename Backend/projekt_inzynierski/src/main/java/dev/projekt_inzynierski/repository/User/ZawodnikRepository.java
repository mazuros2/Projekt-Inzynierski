package dev.projekt_inzynierski.repository.User;

import dev.projekt_inzynierski.DTO.ZawodnikDTO;
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

}
