package dev.projekt_inzynierski.configurationJWT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Security {


    /**
     * Metoda: public SecurityFilterChain securityFilterChain(HttpSecurity http)
     * Konfiguruje zabezpieczenia aplikacji, w tym mechanizmy uwierzytelniania i autoryzacji.
     *
     * Ta metoda ustawia konfigurację zabezpieczeń, która:
     *     Wyłącza obsługę CSRF (Cross-Site Request Forgery).
     *     Zezwala na żądania typu OPTIONS do wszystkich endpointów
     *     Wymaga uwierzytelnienia dla wszystkich pozostałych żądań
     *     Ustawia politykę sesji na STATELESS, co oznacza, że sesje nie są przechowywane na serwerze
     *     Konfiguruje podstawowe uwierzytelnianie HTTP (HTTP Basic Authentication)
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                        .authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated())
                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .httpBasic(Customizer.withDefaults())
                        .build();
    }

    /**
     * Metoda: public InMemoryUserDetailsManager adminUser()
     * Tworzy użytkownika admina w pamięci do celów uwierzytelniania.
     *
     * Ta metoda konfiguruje użytkownika admina w pamięci z:
     *     username = "admin"
     *     password = "admin" ( "{noop}" oznacza brak hashowania)
     *     uprawnieniami ("read")
     */
    @Bean
    public InMemoryUserDetailsManager adminUser(){
        return new InMemoryUserDetailsManager(User.withUsername("admin")
                                            .password("{noop}admin")
                                            .authorities("read")
                                            .build());
    }
}
