package dev.projekt_inzynierski.repository.User;

import dev.projekt_inzynierski.models.users.Menadzer_klubu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Menadzer_klubuRepository extends JpaRepository<Menadzer_klubu,Long> {
    Optional<Menadzer_klubu> findById(long id);

    @Query("SELECT k.id FROM Klub k WHERE  k.menadzer_klubu.id_Uzytkownik=:id_menadzera ")
     Long getIdKlubuMenadzera(Long id_menadzera);
}
