package dev.projekt_inzynierski.service.User;

import dev.projekt_inzynierski.DTO.KlubDTO;
import dev.projekt_inzynierski.DTO.Register.RegisterMenadzerDTO;
import dev.projekt_inzynierski.DTO.Register.RegisterSkautDTO;
import dev.projekt_inzynierski.DTO.Register.RegisterTrenerDTO;
import dev.projekt_inzynierski.DTO.Register.RegisterZawodnikDTO;
import dev.projekt_inzynierski.configurationJWT.Role;
import dev.projekt_inzynierski.models.*;
import dev.projekt_inzynierski.models.users.Menadzer_klubu;
import dev.projekt_inzynierski.models.users.Skaut;
import dev.projekt_inzynierski.models.users.Trener;
import dev.projekt_inzynierski.models.users.Zawodnik;
import dev.projekt_inzynierski.repository.Klub.KlubRepository;
import dev.projekt_inzynierski.repository.Kraj_pochodzeniaRepository;
import dev.projekt_inzynierski.repository.Obecny_klubRepository;
import dev.projekt_inzynierski.repository.PozycjaRepository;
import dev.projekt_inzynierski.repository.User.UzytkownikRepository;
import org.springframework.cglib.core.Local;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class AdminService {

    private final UzytkownikRepository uzytkownikRepository;
    private final KlubRepository klubRepository;
    private final Obecny_klubRepository obecny_klubRepository;
    private final Kraj_pochodzeniaRepository kraj_pochodzeniaRepository;
    private final PasswordEncoder passwordEncoder;

    private final PozycjaRepository pozycjaRepository;

    public AdminService(UzytkownikRepository uzytkownikRepository, KlubRepository klubRepository, Obecny_klubRepository obecny_klubRepository, Kraj_pochodzeniaRepository kraj_pochodzeniaRepository, PasswordEncoder passwordEncoder, PozycjaRepository pozycjaRepository) {
        this.uzytkownikRepository = uzytkownikRepository;
        this.klubRepository = klubRepository;
        this.obecny_klubRepository = obecny_klubRepository;
        this.kraj_pochodzeniaRepository = kraj_pochodzeniaRepository;
        this.passwordEncoder = passwordEncoder;
        this.pozycjaRepository = pozycjaRepository;
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

    public void createZawodnik(RegisterZawodnikDTO request) {
        Klub klub = klubRepository.findById(request.getKlubId())
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
        klub.dodajZawodnika(zawodnik, LocalDate.now());
        klubRepository.save(klub);
        uzytkownikRepository.save(zawodnik);

        Obecny_klub ob = Obecny_klub.builder()
                .zawodnik(zawodnik)
                .klub(klub)
                .data_Od(LocalDate.now())
                .data_Do(null)
                .build();

        obecny_klubRepository.save(ob);
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

    public void deleteTrener(KlubDTO request){
        Klub klub = klubRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono klubu o takim id!"));

        Trener trenerDoZwolniena = klub.getTrener();

        klub.setTrener(null);
        uzytkownikRepository.delete(trenerDoZwolniena);
        klubRepository.save(klub);
    }


    public void deleteSkaut(KlubDTO request){
        Klub klub = klubRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono klubu o takim id!"));

        Skaut skautDoZwolniena = klub.getSkaut();

        klub.setSkaut(null);
        uzytkownikRepository.delete(skautDoZwolniena);
        klubRepository.save(klub);
    }

    public void deleteMenadzer(KlubDTO request){
        Klub klub = klubRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono klubu o takim id!"));

        Menadzer_klubu menadzerDoZwolniena = klub.getMenadzer_klubu();

        klub.setMenadzer_klubu(null);
        uzytkownikRepository.delete(menadzerDoZwolniena);
        klubRepository.save(klub);
    }


}
