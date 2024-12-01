package dev.projekt_inzynierski.service.User;

import dev.projekt_inzynierski.DTO.Register.RegisterMenadzerDTO;
import dev.projekt_inzynierski.DTO.Register.RegisterSkautDTO;
import dev.projekt_inzynierski.DTO.Register.RegisterTrenerDTO;
import dev.projekt_inzynierski.DTO.Register.RegisterZawodnikDTO;
import dev.projekt_inzynierski.configurationJWT.Role;
import dev.projekt_inzynierski.models.Klub;
import dev.projekt_inzynierski.models.Kraj_pochodzenia;
import dev.projekt_inzynierski.models.users.Menadzer_klubu;
import dev.projekt_inzynierski.models.users.Skaut;
import dev.projekt_inzynierski.models.users.Trener;
import dev.projekt_inzynierski.models.users.Zawodnik;
import dev.projekt_inzynierski.repository.Klub.KlubRepository;
import dev.projekt_inzynierski.repository.Kraj_pochodzeniaRepository;
import dev.projekt_inzynierski.repository.User.UzytkownikRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AdminService {

    private final UzytkownikRepository uzytkownikRepository;
    private final KlubRepository klubRepository;
    private final Kraj_pochodzeniaRepository kraj_pochodzeniaRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(UzytkownikRepository uzytkownikRepository, KlubRepository klubRepository, Kraj_pochodzeniaRepository kraj_pochodzeniaRepository, PasswordEncoder passwordEncoder) {
        this.uzytkownikRepository = uzytkownikRepository;
        this.klubRepository = klubRepository;
        this.kraj_pochodzeniaRepository = kraj_pochodzeniaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createTrener(RegisterTrenerDTO request){
        Klub klub = klubRepository.findById(request.getIdKlub())
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono klubu o takim id!"));

        Set<Kraj_pochodzenia> krajePochodzenia = new HashSet<>(kraj_pochodzeniaRepository.findAllById(request.getKrajePochodzenia()));

        Trener trener = Trener.builder()
                .imie(request.getImie())
                .nazwisko(request.getNazwisko())
                .email(request.getEmail())
                .login(request.getLogin())
                .haslo(passwordEncoder.encode(request.getHaslo()))
                .pesel(request.getPesel())
                .data_Urodzenia(request.getDataUrodzenia())
                .role(Role.TRENER)
                .licencja_trenera(request.getLicencjaTrenera())
                .trenerKlub(klub)
                .kraj_pochodzenia(krajePochodzenia)
                .build();

        uzytkownikRepository.save(trener);
    }

    public void createZawodnik(RegisterZawodnikDTO request){
        Klub klub = klubRepository.findById(request.getIdKlub())
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono klubu o takim id!"));

        Set<Kraj_pochodzenia> krajePochodzenia = new HashSet<>(kraj_pochodzeniaRepository.findAllById(request.getKrajePochodzenia()));

        Zawodnik zawodnik = Zawodnik.builder()
                .imie(request.getImie())
                .nazwisko(request.getNazwisko())
                .email(request.getEmail())
                .login(request.getLogin())
                .haslo(passwordEncoder.encode(request.getHaslo()))
                .pesel(request.getPesel())
                .data_Urodzenia(request.getDataUrodzenia())
                .role(Role.ZAWODNIK)
                //klub
                //pozycja
                .kraj_pochodzenia(krajePochodzenia)
                .build();

        uzytkownikRepository.save(zawodnik);
    }

    public void createSkaut(RegisterSkautDTO request){
        Klub klub = klubRepository.findById(request.getIdKlub())
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono klubu o takim id!"));

        Set<Kraj_pochodzenia> krajePochodzenia = new HashSet<>(kraj_pochodzeniaRepository.findAllById(request.getKrajePochodzenia()));

        Skaut skaut = Skaut.builder()
                .imie(request.getImie())
                .nazwisko(request.getNazwisko())
                .email(request.getEmail())
                .login(request.getLogin())
                .haslo(passwordEncoder.encode(request.getHaslo()))
                .pesel(request.getPesel())
                .data_Urodzenia(request.getDataUrodzenia())
                .role(Role.SKAUT)
                .skautKlubu(klub)
                .kraj_pochodzenia(krajePochodzenia)
                .build();

        uzytkownikRepository.save(skaut);
    }

    public void createMenadzer(RegisterMenadzerDTO request){
        Klub klub = klubRepository.findById(request.getIdKlub())
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono klubu o takim id!"));

        Set<Kraj_pochodzenia> krajePochodzenia = new HashSet<>(kraj_pochodzeniaRepository.findAllById(request.getKrajePochodzenia()));

        Menadzer_klubu menadzer = Menadzer_klubu.builder()
                .imie(request.getImie())
                .nazwisko(request.getNazwisko())
                .email(request.getEmail())
                .login(request.getLogin())
                .haslo(passwordEncoder.encode(request.getHaslo()))
                .pesel(request.getPesel())
                .data_Urodzenia(request.getDataUrodzenia())
                .role(Role.MENADZER_KLUBU)
                .menadzerKlubu(klub)
                .kraj_pochodzenia(krajePochodzenia)
                .build();

        uzytkownikRepository.save(menadzer);
    }

    //metoda do stworzenia admina ??? <- nie wiem czy to nam sie przyda
}
