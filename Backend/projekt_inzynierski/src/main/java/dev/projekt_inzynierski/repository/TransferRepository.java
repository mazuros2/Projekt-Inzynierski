package dev.projekt_inzynierski.repository;

import dev.projekt_inzynierski.DTO.KlubFromLigaDTO;
import dev.projekt_inzynierski.DTO.TransferDTO;
import dev.projekt_inzynierski.models.Klub;
import dev.projekt_inzynierski.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer,Long> {

    @Query(("SELECT new dev.projekt_inzynierski.DTO.TransferDTO(t.id, t.data_transferu, t.status, t.kwota) " +
            "FROM Transfer t WHERE t.Zawodnik.id = :zawodnikId"))
    List<TransferDTO> findByZawodnikId(@Param("zawodnikId") long zawodnikId);
}
