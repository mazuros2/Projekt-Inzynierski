package dev.projekt_inzynierski.repository;

import dev.projekt_inzynierski.DTO.KlubFromLigaDTO;
import dev.projekt_inzynierski.DTO.TransferDTO;
import dev.projekt_inzynierski.DTO.TransferDTO2;
import dev.projekt_inzynierski.models.Klub;
import dev.projekt_inzynierski.models.Transfer;
import org.springframework.data.domain.Pageable;
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
            "t.id, t.data_transferu, t.status, t.kwota,t.klubDo.nazwa_klubu,t.klubOd.id,t.klubDo.id) " +
            "FROM Transfer t  WHERE t.zawodnik.id_Uzytkownik = :zawodnikId")
    List<TransferDTO> findByZawodnikId(@Param("zawodnikId") long zawodnikId);


    @Modifying
    @Query(value = "INSERT INTO Transfer (data_transferu, status, kwota, id_zawodnik, id_klub_od, id_klub_do) " +
            "VALUES (:dataTransferu, 'oczekujacy', :kwota, :id_zawodnik, COALESCE(:id_klubOd, NULL), :id_klubDo)",
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
    @Query("UPDATE Obecny_klub ok SET ok.data_Do = :dataDo WHERE ok.zawodnik.id_Uzytkownik = :id_uzytkownik AND ok.klub.id = :id_klubOd")
    int zakonczObecnoscWStarymKlubie(@Param("dataDo") LocalDate dataDo, @Param("id_uzytkownik") long idUzytkownik, @Param("id_klubOd") long idKlubOd);

    @Modifying
    @Query(value = "INSERT into Obecny_klub (zawodnik_id,klub_id,data_Od,data_Do) VALUES (:zawodnikId,:klubId,:dataOd,null)",
            nativeQuery = true)
    void dodajNowyObecnyKlub(@Param("zawodnikId")long zawodnikId, @Param("klubId") Long klubId, @Param("dataOd") LocalDate dataOd);

    @Modifying
    @Query(value = "UPDATE Transfer t SET t.status = 'odrzucony' WHERE t.zawodnik.id_Uzytkownik= :zawodnikId AND t.status = 'oczekujacy'" )
    void ustawInneTransferyZawodnika(@Param("zawodnikId") long zawodnikId);

//metoda dla transferow od menadzera klubu
    @Query("SELECT new dev.projekt_inzynierski.DTO.TransferDTO(" +
            "t.id, t.data_transferu, t.status, t.kwota,t.klubDo.nazwa_klubu,t.klubOd.id,t.klubDo.id) " +
            "FROM Transfer t  WHERE t.klubDo.menadzer_klubu.id_Uzytkownik = :menadzerId")
    List<TransferDTO> findByMenadzerId(@Param("menadzerId") long menadzerId);

    @Query("""
    SELECT new dev.projekt_inzynierski.DTO.TransferDTO2(
        t.zawodnik.id_Uzytkownik, t.zawodnik.imie, t.zawodnik.nazwisko, 
        t.klubOd.id, t.klubOd.nazwa_klubu, 
        t.klubDo.id, t.klubDo.nazwa_klubu, 
        t.kwota)
    FROM Transfer t
    WHERE t.status = 'zaakceptowany'
    ORDER BY t.data_transferu DESC
    """)
    List<TransferDTO2> findTop5AcceptedTransfers(Pageable pageable);


}
