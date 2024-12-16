package dev.projekt_inzynierski.repository.User;

import dev.projekt_inzynierski.DTO.TrenerDTO3;
import dev.projekt_inzynierski.models.users.Trener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrenerRepository extends JpaRepository<Trener,Long> {

    Optional<Trener> findById(long id);

    @Query("""
    SELECT new dev.projekt_inzynierski.DTO.TrenerDTO3(
        t.id_Uzytkownik,
        t.imie,
        t.nazwisko,
        t.data_Urodzenia,
        t.licencja_trenera
    )
    FROM Trener t
    WHERE t.trenerKlub.id = :id_klub
""")
    TrenerDTO3 findById_Klub(@Param("id_klub") long idKlub);


}
