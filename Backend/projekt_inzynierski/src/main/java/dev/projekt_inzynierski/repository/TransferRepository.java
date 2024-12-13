package dev.projekt_inzynierski.repository;

import dev.projekt_inzynierski.DTO.KlubFromLigaDTO;
import dev.projekt_inzynierski.DTO.TransferDTO;
import dev.projekt_inzynierski.models.Klub;
import dev.projekt_inzynierski.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
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
    @Query(value = "INSERT INTO Transfer (data_transferu, status, kwota, id_zawodnik,id_klubOd,id_klubDo) " +
            "VALUES (:dataTransferu, 'oczekujacy', :kwota, :id_zawodnik, :id_klubOd,:id_klubDo)",
            nativeQuery = true)
    void sendTransfer(@Param("dataTransferu") LocalDate dataTransferu,
                      @Param("kwota") int kwota,
                      @Param("id_zawodnik") long id_zawodnik,
                      @Param("id_klubOd") long id_klubOd,
                      @Param("id_klubDo") long id_klubDo);
    @Modifying
    @Query("UPDATE Transfer t SET t.status = 'odrzucony' WHERE t.id=:id")
    void odrzucTransfer(@Param("id") long id);

    @Modifying
    @Query("UPDATE Transfer t set t.status ='zaakceptowany' WHERE t.id=:id_transfer")
    void zaakceptujTransfer(@Param("id_transfer") long id_transfer);

    @Modifying
    @Query("UPDATE Obecny_klub ok SET ok.data_Do =:dataDo WHERE ok.zawodnik.id_Uzytkownik=:id_uzytkownik AND ok.klub.id=:id_klubOd")
    void zakonczObecnoscWStarymKlubie(@Param("dataDo") LocalDate dataDo,@Param("id_uzytkownik") long id_uzytkownik, @Param("id_klubOd") long id_klubOd);

    @Modifying
    @Query(value = "INSERT into Obecny_klub ok (zawodnik_id,klub_id,data_Od,data_Do) VALUES (:zawodnikId,:klubId,:dataOd,null)",
            nativeQuery = true)
    void dodajNowyObecnyKlub(@Param("zawodnikId")long zawodnikId, @Param("klubId") Long klubId, @Param("dataOd") LocalDate dataOd);
}
