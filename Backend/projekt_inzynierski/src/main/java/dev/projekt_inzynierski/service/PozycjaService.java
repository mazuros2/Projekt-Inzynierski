package dev.projekt_inzynierski.service;

import dev.projekt_inzynierski.DTO.PozycjaDTO;
import dev.projekt_inzynierski.repository.PozycjaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PozycjaService {
    private final PozycjaRepository pozycjaRepository;

    public PozycjaService(PozycjaRepository pozycjaRepository) {
        this.pozycjaRepository = pozycjaRepository;
    }

    public List<PozycjaDTO> getPozycje(){
       return pozycjaRepository.findAll().stream()
               .map(pozycja -> new PozycjaDTO(pozycja.getId_Pozycja(),pozycja.getNazwa_pozycji(), pozycja.getObszar_pozycji()))
               .collect(Collectors.toList());
    }

}
