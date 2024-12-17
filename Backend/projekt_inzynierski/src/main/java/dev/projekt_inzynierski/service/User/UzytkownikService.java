package dev.projekt_inzynierski.service.User;

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

}
