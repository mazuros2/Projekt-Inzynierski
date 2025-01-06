package dev.projekt_inzynierski.service;

import dev.projekt_inzynierski.DTO.TransferDTO;
import dev.projekt_inzynierski.DTO.TrofeumDTO;
import dev.projekt_inzynierski.repository.TransferRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransferService {
    private final TransferRepository transferRepository;
    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }
    public List<TransferDTO> findByZawodnikId(long zawodnikId) {
        return transferRepository.findByZawodnikId(zawodnikId);
    }
    @Transactional
    public void sendTransfer(LocalDate dataTransferu,int kwota,long id_zawodnik,long id_klubOd,long id_klubDo){
        System.out.println("Dane do transferu: " + dataTransferu + ", " + kwota + ", " + id_zawodnik + ", " + id_klubOd + ", " + id_klubDo);
        transferRepository.sendTransfer(dataTransferu,kwota,id_zawodnik,id_klubOd,id_klubDo);
    }
    @Transactional
    public void odrzucTransfer(long id){
        transferRepository.odrzucTransfer(id);
    }
    @Transactional
    public void zaakceptujTransfer(long idTransfer, LocalDate dataDo, long idUzytkownik, long idKlubOd, long idKlubDo) {
        // 1. Aktualizacja statusu transferu
        transferRepository.zaakceptujTransfer(idTransfer);

        // 2. Sprawdzenie obecności w starym klubie i zakończenie relacji
        int updatedRows = transferRepository.zakonczObecnoscWStarymKlubie(dataDo, idUzytkownik, idKlubOd);
        if (updatedRows == 0) {
            throw new IllegalArgumentException("Nie znaleziono obecności zawodnika w starym klubie");
        }

        // 3. Dodanie nowego rekordu obecności w nowym klubie
        transferRepository.dodajNowyObecnyKlub(idUzytkownik, idKlubDo, dataDo);
        // 4. Odrzucenie starych transferow
        transferRepository.ustawInneTransferyZawodnika(idUzytkownik);
    }
}
