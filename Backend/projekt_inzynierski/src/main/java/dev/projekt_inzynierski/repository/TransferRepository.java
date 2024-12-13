package dev.projekt_inzynierski.repository;

import dev.projekt_inzynierski.DTO.KlubFromLigaDTO;
import dev.projekt_inzynierski.DTO.TransferDTO;
import dev.projekt_inzynierski.models.Klub;
import dev.projekt_inzynierski.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer,Long> {

/*    @Query(("SELECT new dev.projekt_inzynierski.DTO.TransferDTO(t.id, t.data_transferu, t.status, t.kwota) " +
            "FROM Transfer t WHERE t.Zawodnik.id = :zawodnikId"))
    List<TransferDTO> findByZawodnikId(@Param("zawodnikId") long zawodnikId);*/

    @Query("SELECT new dev.projekt_inzynierski.DTO.TransferDTO(" +
            "t.id, t.data_transferu, t.status, t.kwota) " +
            "FROM Transfer t WHERE t.zawodnik.id = :zawodnikId")
    List<TransferDTO> findByZawodnikId(@Param("zawodnikId") long zawodnikId);
    @Modifying
    @Query(value = "INSERT INTO transfer (data_transferu, status, kwota, id_zawodnik) " +
            "VALUES (:dataTransferu, 'oczekujacy', :kwota, :id_zawodnik)",
            nativeQuery = true)
    void sendTransfer(@Param("dataTransferu") LocalDate dataTransferu,
                      @Param("kwota") int kwota,
                      @Param("id_zawodnik") long id_zawodnik);
    @Modifying
    @Query("UPDATE Transfer t SET t.status = 'odrzucony' WHERE t.id=:id")
    void odrzucTransfer(@Param("id") long id);
}
