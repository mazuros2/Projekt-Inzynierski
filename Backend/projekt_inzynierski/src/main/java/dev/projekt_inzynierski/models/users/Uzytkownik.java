package dev.projekt_inzynierski.models.users;

import dev.projekt_inzynierski.configurationJWT.Role;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import dev.projekt_inzynierski.models.Kraj_pochodzenia;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Uzytkownik")
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Uzytkownik implements UserDetails {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_Uzytkownik;

    @Size(max = 50)
    @NotBlank
    @Column(unique=true)
    private String login;

    @NotBlank
    private String haslo;

    @Size(max = 50)
    @Email
    @Column(unique=true)
    private String email;

    @Size(max = 50)
    @NotBlank
    private String imie;

    @Size(max = 50)
    @NotBlank
    private String nazwisko;

    @Size(max = 11)
    @Column(unique=true)
    private String pesel;

    @Nullable
    private String profiloweURL;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @NotNull
    private LocalDate data_Urodzenia;

    @ManyToMany
    @JoinTable(
            name = "uzytkownik_kraj",
            joinColumns = @JoinColumn(name = "id_uzytkownik"),
            inverseJoinColumns = @JoinColumn(name = "id_kraj")
    )
    private Set<Kraj_pochodzenia> kraj_pochodzenia = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Zwraca rolę w formacie akceptowanym przez Spring Security
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
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
