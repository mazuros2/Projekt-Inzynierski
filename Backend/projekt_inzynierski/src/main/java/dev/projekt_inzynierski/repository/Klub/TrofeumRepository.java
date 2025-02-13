package dev.projekt_inzynierski.repository.Klub;

import dev.projekt_inzynierski.DTO.TrofeumDTO;
import dev.projekt_inzynierski.DTO.TrofeumNazwaKlubuDTO;
import dev.projekt_inzynierski.models.Trofeum;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TrofeumRepository extends JpaRepository<Trofeum,Long> {

    @Query("SELECT new dev.projekt_inzynierski.DTO.TrofeumDTO(t.id, t.nazwa, t.data_zdobycia) " +
            "FROM Trofeum t WHERE t.klub.id = :klubId")
    List<TrofeumDTO> findAllByKlubId(@Param("klubId") long klubId);

    @Query("SELECT new dev.projekt_inzynierski.DTO.TrofeumNazwaKlubuDTO(" +
            "t.nazwa, k.nazwa_klubu, t.data_zdobycia) " +
            "FROM Trofeum t INNER JOIN Klub k ON t.klub.id = k.id " +
            "WHERE t.nazwa LIKE 'Mistrz Polski%'" +
            "ORDER BY t.data_zdobycia DESC")
    List<TrofeumNazwaKlubuDTO> findMistrzPolski();

    @Query("SELECT new dev.projekt_inzynierski.DTO.TrofeumNazwaKlubuDTO(" +
            "t.nazwa, k.nazwa_klubu, t.data_zdobycia) " +
            "FROM Trofeum t INNER JOIN Klub k ON t.klub.id = k.id " +
            "WHERE t.nazwa LIKE 'Puchar Polski%'" +
            "ORDER BY t.data_zdobycia DESC")
    List<TrofeumNazwaKlubuDTO> findPucharPolski();

    @Query("SELECT new dev.projekt_inzynierski.DTO.TrofeumNazwaKlubuDTO(" +
            "t.nazwa, k.nazwa_klubu, t.data_zdobycia) " +
            "FROM Trofeum t INNER JOIN Klub k ON t.klub.id = k.id " +
            "WHERE t.nazwa LIKE 'Mistrz Polski%'" +
            "ORDER BY t.data_zdobycia DESC")
    List<TrofeumNazwaKlubuDTO> getLastFiveMistrzPolski(Pageable pageable);


    @Query("SELECT new dev.projekt_inzynierski.DTO.TrofeumNazwaKlubuDTO(" +
            "t.nazwa, k.nazwa_klubu, t.data_zdobycia) " +
            "FROM Trofeum t INNER JOIN Klub k ON t.klub.id = k.id " +
            "WHERE t.nazwa LIKE 'Puchar Polski%'" +
            "ORDER BY t.data_zdobycia DESC")
    List<TrofeumNazwaKlubuDTO> getLastFivePucharPolski(Pageable pageable);


}
