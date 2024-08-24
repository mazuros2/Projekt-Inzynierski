package models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@Table(name = "Liga")
public class Liga {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank
    private String nazwa_Ligi;
    @NotNull
    private int poziom_Ligi;

    @OneToMany(mappedBy = "liga", cascade = CascadeType.ALL)
    private List<Klub> kluby;

}
