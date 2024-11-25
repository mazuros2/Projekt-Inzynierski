package dev.projekt_inzynierski.configurationJWT;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTAuthFilter jwtAF;

    private final AuthenticationProvider authenticationProvider;



    @Bean
    public SecurityFilterChain secFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeHttpRequests().requestMatchers("/api/authentication/**").permitAll().anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authenticationProvider(authenticationProvider).addFilterBefore(jwtAF, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}