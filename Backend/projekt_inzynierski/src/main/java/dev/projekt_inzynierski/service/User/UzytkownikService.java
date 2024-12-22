package dev.projekt_inzynierski.service.User;

import dev.projekt_inzynierski.DTO.UzytkownikDTO2;
import dev.projekt_inzynierski.models.users.Uzytkownik;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import dev.projekt_inzynierski.repository.User.UzytkownikRepository;

import java.util.Optional;

@Service
public class UzytkownikService {
    private final UzytkownikRepository uzytkownikRepository;
    private final PasswordEncoder passwordEncoder;

    public UzytkownikService(UzytkownikRepository uzytkownikRepository,PasswordEncoder passwordEncoder) {
        this.uzytkownikRepository = uzytkownikRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void zmianaHaslaUzytkownika(String login, String stareHaslo, String noweHaslo){
        Uzytkownik uzytkownik = uzytkownikRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Nie istnieje Uzytkownik o takim loginie!"));

        if(!passwordEncoder.matches(stareHaslo, uzytkownik.getPassword())){
            throw new RuntimeException("Stare haslo jest niepoprawne!");
        }

        uzytkownik.setHaslo(passwordEncoder.encode(noweHaslo));
        uzytkownikRepository.save(uzytkownik);
    }

    public Optional<Uzytkownik> findUzytkownikById(Long id) {
        return uzytkownikRepository.findById(id);
    }

    public void zmienDaneUzytkownika(long id, UzytkownikDTO2 uzytkownikDTO2) {
        Uzytkownik uzytkownik = uzytkownikRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Użytkownik o podanym ID nie istnieje!"));

        if (uzytkownikDTO2.getLogin() != null) {
            uzytkownik.setLogin(uzytkownikDTO2.getLogin());
        }

        if (uzytkownikDTO2.getHaslo() != null) {
            uzytkownik.setHaslo(passwordEncoder.encode(uzytkownikDTO2.getHaslo()));
        }

        if (uzytkownikDTO2.getEmail() != null) {
            uzytkownik.setEmail(uzytkownikDTO2.getEmail());
        }

        if (uzytkownikDTO2.getProfiloweURL() != null) {
            uzytkownik.setProfiloweURL(uzytkownikDTO2.getProfiloweURL());
        }
        uzytkownikRepository.save(uzytkownik);
    }

}
