package dev.projekt_inzynierski.models.users;

import dev.projekt_inzynierski.configurationJWT.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import dev.projekt_inzynierski.models.Kraj_pochodzenia;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Uzytkownik")
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Uzytkownik implements UserDetails {

    //dodawaj do każdego stringa NotBlank
    //do localeDate @NotNull
    //do int możemy dodać po prostu @Min() i jakaś wartość

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_Uzytkownik;

    @Size(max = 50)
    @NotBlank
    private String login;

    @Size(max = 50)
    @NotBlank
    private String haslo;

    @Size(max = 50)
    @Email
    private String email;

    @Size(max = 50)
    @NotBlank
    private String imie;

    @Size(max = 50)
    @NotBlank
    private String nazwisko;

    @Column(unique=true)
    private int pesel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @NotNull
    private LocalDate data_Urodzenia;

    @ManyToOne
    @JoinColumn(name = "id_Kraj_pochodzenia", nullable = false)
    private Kraj_pochodzenia kraj_pochodzenia;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return haslo;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
