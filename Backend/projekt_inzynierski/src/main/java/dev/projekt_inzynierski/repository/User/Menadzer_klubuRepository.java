package dev.projekt_inzynierski.repository.User;

import dev.projekt_inzynierski.DTO.MenadzerIdKlubDTO;
import dev.projekt_inzynierski.DTO.ZawodnikIdKlubDTO;
import dev.projekt_inzynierski.models.users.Menadzer_klubu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Menadzer_klubuRepository extends JpaRepository<Menadzer_klubu,Long> {
    Optional<Menadzer_klubu> findById(long id);

    @Query("SELECT new dev.projekt_inzynierski.DTO.MenadzerIdKlubDTO(k.id,k.nazwa_klubu) FROM Klub k WHERE  k.menadzer_klubu.id_Uzytkownik=:id_menadzera ")
    Optional <MenadzerIdKlubDTO>getIdKlubuMenadzera(Long id_menadzera);

    @Query("SELECT k.id FROM Klub k WHERE k.menadzer_klubu.id_Uzytkownik = :id_menadzera")
    Optional<Long> getLongIdKlubuMenadzera(Long id_menadzera);

}
