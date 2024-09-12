package dev.projekt_inzynierski.configurationJWT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    /**
     * Tworzy i konfiguruje filtr CORS dla aplikacji.
     * Konfiguracja jest stosowana do wszystkich endpointów aplikacji
     *
     * Ta metoda konfiguruje filtr CORS, który umożliwia:
     *     - Akceptowanie żądań z pochodzenia z front-endu działającego na porcie 3000
     *     - Akceptowanie wszystkich nagłówków
     *     - Akceptowanie wszystkich metod HTTP (GET, POST, PUT, DELETE, itd)
     *     - Przesyłanie cookies, tokenów i innych danych uwierzytelniających
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:3000");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}
