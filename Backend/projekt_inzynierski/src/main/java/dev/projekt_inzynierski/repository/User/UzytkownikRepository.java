package dev.projekt_inzynierski.repository.User;

import dev.projekt_inzynierski.models.users.Uzytkownik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UzytkownikRepository extends JpaRepository<Uzytkownik,Long> {

    Optional<Uzytkownik> findByLogin(String login);

}
