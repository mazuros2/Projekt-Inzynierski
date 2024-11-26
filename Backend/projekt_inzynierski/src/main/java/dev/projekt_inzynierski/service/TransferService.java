package dev.projekt_inzynierski.service;

import dev.projekt_inzynierski.DTO.TransferDTO;
import dev.projekt_inzynierski.DTO.TrofeumDTO;
import dev.projekt_inzynierski.repository.TransferRepository;
import org.springframework.stereotype.Service;

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
}
