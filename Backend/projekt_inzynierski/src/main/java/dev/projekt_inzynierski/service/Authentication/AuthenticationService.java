package dev.projekt_inzynierski.service.Authentication;
import dev.projekt_inzynierski.configurationJWT.Authentication.AuthenticationRequest;
import dev.projekt_inzynierski.configurationJWT.Authentication.AuthenticationResponse;
import dev.projekt_inzynierski.configurationJWT.Authentication.RegisterRequest;
import dev.projekt_inzynierski.configurationJWT.JWTService;
import dev.projekt_inzynierski.configurationJWT.Role;
import dev.projekt_inzynierski.models.users.Uzytkownik;
import dev.projekt_inzynierski.repository.User.UzytkownikRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UzytkownikRepository uzytkownikRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

        return
    }

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var uzytkownik = Uzytkownik.builder()
                                    .imie(registerRequest.getImie())
                                    .nazwisko(registerRequest.getNazwisko())
                                    .email(registerRequest.getEmail())
                                    .login(registerRequest.getLogin())
                                    .haslo(passwordEncoder.encode(registerRequest.getHaslo()))
                                    .pesel(registerRequest.getPesel())
                                    .data_Urodzenia(registerRequest.getDataUrodzenia())
                                    .role(Role.ADMIN)
                                    .build();

        uzytkownikRepository.save(uzytkownik);

        var jwtToken = jwtService.tokenGenerator(uzytkownik);


        return AuthenticationResponse.builder()
                                     .jwtToken(jwtToken)
                                     .build();
    }
}
