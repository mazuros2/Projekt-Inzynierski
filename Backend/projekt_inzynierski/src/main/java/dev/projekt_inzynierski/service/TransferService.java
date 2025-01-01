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
    public void zaakceptujTransfer(long id_transfer,LocalDate dataDo,long id_uzytkownik,long id_klubOd,long id_klubDo ){
        transferRepository.zaakceptujTransfer(id_transfer);
        transferRepository.zakonczObecnoscWStarymKlubie(dataDo,id_uzytkownik,id_klubOd);
        transferRepository.dodajNowyObecnyKlub(id_uzytkownik,id_klubDo,dataDo);
    }
}
