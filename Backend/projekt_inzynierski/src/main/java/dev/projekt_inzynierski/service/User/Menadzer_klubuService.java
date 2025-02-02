package dev.projekt_inzynierski.service.User;

import dev.projekt_inzynierski.DTO.MenadzerDTO;
import dev.projekt_inzynierski.DTO.MenadzerIdKlubDTO;
import dev.projekt_inzynierski.DTO.Register.RegisterSkautDTO;
import dev.projekt_inzynierski.DTO.Register.RegisterTrenerDTO;
import dev.projekt_inzynierski.DTO.Register.RegisterZawodnikDTO;
import dev.projekt_inzynierski.DTO.SkautDTO;
import dev.projekt_inzynierski.configurationJWT.Role;
import dev.projekt_inzynierski.models.Klub;
import dev.projekt_inzynierski.models.Kraj_pochodzenia;
import dev.projekt_inzynierski.models.Obecny_klub;
import dev.projekt_inzynierski.models.Pozycja;
import dev.projekt_inzynierski.models.users.Menadzer_klubu;
import dev.projekt_inzynierski.models.users.Skaut;
import dev.projekt_inzynierski.models.users.Trener;
import dev.projekt_inzynierski.models.users.Zawodnik;
import dev.projekt_inzynierski.repository.Klub.KlubRepository;
import dev.projekt_inzynierski.repository.Kraj_pochodzeniaRepository;
import dev.projekt_inzynierski.repository.Obecny_klubRepository;
import dev.projekt_inzynierski.repository.PozycjaRepository;
import dev.projekt_inzynierski.repository.User.UzytkownikRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import dev.projekt_inzynierski.repository.User.Menadzer_klubuRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class Menadzer_klubuService {
    private final Menadzer_klubuRepository menadzer_klubuRepository;

    private final UzytkownikRepository uzytkownikRepository;
    private final KlubRepository klubRepository;
    private final Obecny_klubRepository obecny_klubRepository;
    private final Kraj_pochodzeniaRepository kraj_pochodzeniaRepository;
    private final PasswordEncoder passwordEncoder;

    private final PozycjaRepository pozycjaRepository;

    public Menadzer_klubuService(Menadzer_klubuRepository menadzer_klubuRepository,
                                 UzytkownikRepository uzytkownikRepository,
                                 KlubRepository klubRepository,
                                 Obecny_klubRepository obecny_klubRepository,
                                 Kraj_pochodzeniaRepository kraj_pochodzeniaRepository,
                                 PasswordEncoder passwordEncoder,
                                 PozycjaRepository pozycjaRepository) {

        this.menadzer_klubuRepository = menadzer_klubuRepository;
        this.uzytkownikRepository = uzytkownikRepository;
        this.klubRepository = klubRepository;
        this.obecny_klubRepository = obecny_klubRepository;
        this.kraj_pochodzeniaRepository = kraj_pochodzeniaRepository;
        this.passwordEncoder = passwordEncoder;
        this.pozycjaRepository = pozycjaRepository;
    }

    public MenadzerDTO getMenadzerInfoById(long id){
        Menadzer_klubu menadzer = menadzer_klubuRepository.findById(id)
                .orElseThrow( () ->
                        new EntityNotFoundException("Nie można znaleźć Menadzera o takim id!"));

        String klub = menadzer.getMenadzerKlubu().getNazwa_klubu();

        Set<String> krajePochodzenia = menadzer.getKraj_pochodzenia().stream()
                .map(Kraj_pochodzenia::getNazwa)
                .collect(Collectors.toSet());

        return new MenadzerDTO(
                menadzer.getImie(),
                menadzer.getNazwisko(),
                menadzer.getData_Urodzenia(),
                krajePochodzenia,
                klub
        );
    }
    public MenadzerIdKlubDTO getIdKlubuMenadzera(Long id_menadzera){
        return menadzer_klubuRepository.getIdKlubuMenadzera(id_menadzera)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono klubu dla zawodnika o ID: " + id_menadzera));
    }

    public void createTrenerByMenadzer(RegisterTrenerDTO request, Long id_menadzera){
        Map<String, String> errors = new HashMap<>();

        if (uzytkownikRepository.existsByLogin(request.getLogin())) {
            errors.put("login", "Login jest już zajęty!");
        }
        if (uzytkownikRepository.existsByPesel(request.getPesel())) {
            errors.put("pesel", "Pesel jest już zajęty!");
        }
        if (uzytkownikRepository.existsByEmail(request.getEmail())) {
            errors.put("email", "Email jest już zajęty!");
        }

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors.toString());
        }

        Long idKlubuMenadzera = menadzer_klubuRepository.getLongIdKlubuMenadzera(id_menadzera)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono klubu dla menadżera!"));

        Klub klubMenadzera = klubRepository.findById(idKlubuMenadzera)
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
                .trenerKlub(klubMenadzera)
                .kraj_pochodzenia(krajePochodzenia)
                .build();

        uzytkownikRepository.save(trener);
    }

    public void createSkautByMenadzer(RegisterSkautDTO request, Long id_menadzera){
        Map<String, String> errors = new HashMap<>();

        if (uzytkownikRepository.existsByLogin(request.getLogin())) {
            errors.put("login", "Login jest już zajęty!");
        }
        if (uzytkownikRepository.existsByPesel(request.getPesel())) {
            errors.put("pesel", "Pesel jest już zajęty!");
        }
        if (uzytkownikRepository.existsByEmail(request.getEmail())) {
            errors.put("email", "Email jest już zajęty!");
        }

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors.toString());
        }

        Long idKlubuMenadzera = menadzer_klubuRepository.getLongIdKlubuMenadzera(id_menadzera)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono klubu dla menadżera!"));

        Klub klubMenadzera = klubRepository.findById(idKlubuMenadzera)
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
                .skautKlubu(klubMenadzera)
                .kraj_pochodzenia(krajePochodzenia)
                .build();

        uzytkownikRepository.save(skaut);
    }

    public void createZawodnikByMenadzer(RegisterZawodnikDTO request, Long id_menadzera) {
        Map<String, String> errors = new HashMap<>();

        if (uzytkownikRepository.existsByLogin(request.getLogin())) {
            errors.put("login", "Login jest już zajęty!");
        }
        if (uzytkownikRepository.existsByPesel(request.getPesel())) {
            errors.put("pesel", "Pesel jest już zajęty!");
        }
        if (uzytkownikRepository.existsByEmail(request.getEmail())) {
            errors.put("email", "Email jest już zajęty!");
        }

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors.toString());
        }

        Long idKlubuMenadzera = menadzer_klubuRepository.getLongIdKlubuMenadzera(id_menadzera)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono klubu dla menadżera!"));

        Klub klubMenadzera = klubRepository.findById(idKlubuMenadzera)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono klubu o takim id!"));

        Set<Kraj_pochodzenia> krajePochodzenia = new HashSet<>(kraj_pochodzeniaRepository.findAllById(request.getKrajePochodzenia()));

        Pozycja pozycja = pozycjaRepository.findById(request.getPozycjaId())
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono pozycji o takim id!"));

        Zawodnik zawodnik = Zawodnik.builder()
                .imie(request.getImie())
                .nazwisko(request.getNazwisko())
                .email(request.getEmail())
                .login(request.getLogin())
                .haslo(passwordEncoder.encode(request.getHaslo()))
                .pesel(request.getPesel())
                .data_Urodzenia(request.getDataUrodzenia())
                .role(Role.ZAWODNIK)
                .waga(request.getWaga())
                .wzrost(request.getWzrost())
                .pozycja(pozycja)
                .kraj_pochodzenia(krajePochodzenia)
                .obecny_klub(new HashSet<>())
                .build();

        uzytkownikRepository.save(zawodnik);
        klubMenadzera.dodajZawodnika(zawodnik, LocalDate.now());
        klubRepository.save(klubMenadzera);
        uzytkownikRepository.save(zawodnik);

        Obecny_klub ob = Obecny_klub.builder()
                .zawodnik(zawodnik)
                .klub(klubMenadzera)
                .data_Od(LocalDate.now())
                .data_Do(null)
                .build();

        obecny_klubRepository.save(ob);
    }

    public void deleteTrenerByMenadzer(Long id_menadzera){
        Long idKlubuMenadzera = menadzer_klubuRepository.getLongIdKlubuMenadzera(id_menadzera)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono klubu dla menadżera!"));

        Klub klubMenadzera = klubRepository.findById(idKlubuMenadzera)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono klubu o takim id!"));

        Trener trenerDoZwolniena = klubMenadzera.getTrener();

        klubMenadzera.setTrener(null);
        uzytkownikRepository.delete(trenerDoZwolniena);
        klubRepository.save(klubMenadzera);
    }

    public void deleteSkautByMenadzer(Long id_menadzera){
        Long idKlubuMenadzera = menadzer_klubuRepository.getLongIdKlubuMenadzera(id_menadzera)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono klubu dla menadżera!"));

        Klub klubMenadzera = klubRepository.findById(idKlubuMenadzera)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono klubu o takim id!"));

        Skaut skautDoZwolniena = klubMenadzera.getSkaut();

        klubMenadzera.setSkaut(null);
        uzytkownikRepository.delete(skautDoZwolniena);
        klubRepository.save(klubMenadzera);
    }

}
