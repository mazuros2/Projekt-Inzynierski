package dev.projekt_inzynierski.service;

import dev.projekt_inzynierski.repository.TransferRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    private final TransferRepository transferRepository;
    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }
}
