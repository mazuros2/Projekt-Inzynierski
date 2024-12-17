package dev.projekt_inzynierski.service.Authentication;

import dev.projekt_inzynierski.configurationJWT.Authentication.AuthenticationRequest;
import dev.projekt_inzynierski.configurationJWT.Authentication.AuthenticationResponse;
import dev.projekt_inzynierski.configurationJWT.Authentication.RegisterRequest;
import dev.projekt_inzynierski.configurationJWT.JWTService;
import dev.projekt_inzynierski.configurationJWT.Role;
import dev.projekt_inzynierski.models.users.Uzytkownik;
import dev.projekt_inzynierski.repository.User.UzytkownikRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UzytkownikRepository uzytkownikRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authMangager;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        // Logowanie użytkownika
        authMangager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getLogin(),
                        authenticationRequest.getHaslo()
                )
        );

        var uzytkownik = uzytkownikRepository.findByLogin(authenticationRequest.getLogin())
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono użytkownika"));

        // Przekazujemy ID użytkownika do tokena
        var jwtToken = jwtService.tokenGenerator(new HashMap<>(), uzytkownik, uzytkownik.getId_Uzytkownik());

        return AuthenticationResponse.builder()
                .jwtToken(jwtToken)
                .build();
    }

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        // Rejestracja nowego użytkownika
        var uzytkownik = Uzytkownik.builder()
                .imie(registerRequest.getImie())
                .nazwisko(registerRequest.getNazwisko())
                .email(registerRequest.getEmail())
                .login(registerRequest.getLogin())
                .haslo(passwordEncoder.encode(registerRequest.getHaslo()))
                .pesel(registerRequest.getPesel())
                .data_Urodzenia(registerRequest.getDataUrodzenia())
                .role(Role.ADMIN) // Domyślnie ADMIN, można to dynamicznie zmieniać
                .build();

        uzytkownikRepository.save(uzytkownik);

        // Przekazujemy ID użytkownika do tokena
        var jwtToken = jwtService.tokenGenerator(new HashMap<>(), uzytkownik, uzytkownik.getId_Uzytkownik());

        return AuthenticationResponse.builder()
                .jwtToken(jwtToken)
                .build();
    }
}
