package dev.projekt_inzynierski.service;

import dev.projekt_inzynierski.DTO.KrajPochodzeniaDTO;
import dev.projekt_inzynierski.repository.Kraj_pochodzeniaRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Kraj_pochodzeniaService {
    private Kraj_pochodzeniaRepository kraj_pochodzeniaRepository;
    public Kraj_pochodzeniaService(Kraj_pochodzeniaRepository kraj_pochodzeniaRepository) {
        this.kraj_pochodzeniaRepository=kraj_pochodzeniaRepository;
    }

    public List<KrajPochodzeniaDTO> getAllKraje() {
        return kraj_pochodzeniaRepository.findAll().stream()
                .map(kraj -> new KrajPochodzeniaDTO(kraj.getId_Kraj(), kraj.getNazwa(), kraj.getRegion()))
                .sorted(Comparator.comparing(KrajPochodzeniaDTO::getNazwa))
                .collect(Collectors.toList());
    }

}
