package dev.projekt_inzynierski.configurationJWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;
    private final UserDetailsService usrDetailsService;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userLogin;

        // Sprawdź, czy nagłówek Authorization istnieje i zaczyna się od "Bearer "
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Wyodrębnij token JWT
        jwtToken = authorizationHeader.substring(7);
        userLogin = jwtService.extractLogin(jwtToken);

        // Jeśli login jest dostępny i brak aktualnej autentykacji w SecurityContext
        if (userLogin != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails detailsUser = this.usrDetailsService.loadUserByUsername(userLogin);

            // Walidacja tokena
            if (jwtService.tokenValidation(jwtToken, detailsUser)) {
                // Wyodrębnij rolę użytkownika z tokena
                String userRole = jwtService.extractRole(jwtToken);
                System.out.println("Rola użytkownika: " + userRole); // Logowanie roli dla debugowania

                // Utwórz obiekt autoryzacji i ustaw w SecurityContext
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        detailsUser,
                        null,
                        detailsUser.getAuthorities() // Rola użytkownika będzie uwzględniona w Authorities
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Kontynuuj przetwarzanie żądania
        filterChain.doFilter(request, response);
    }
}
