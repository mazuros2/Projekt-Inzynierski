package dev.projekt_inzynierski.service.Klub;

import dev.projekt_inzynierski.DTO.LigaDTO;
import dev.projekt_inzynierski.models.Liga;
import dev.projekt_inzynierski.repository.Klub.LigaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LigaService {
    private final LigaRepository ligaRepository;

    public LigaService(LigaRepository ligaRepository) {
        this.ligaRepository = ligaRepository;
    }

    public List<LigaDTO> getAllLigaNames() {
        return ligaRepository.findAllLigaNames();
    }

    public Liga addLiga(Liga liga){
        return ligaRepository.save(liga);
    };

    public LigaDTO getLigaById(Long idLiga) {
        return ligaRepository.findById(idLiga)
                .map(liga -> new LigaDTO(liga.getId(), liga.getNazwa_Ligi(), liga.getPoziom_Ligi()))
                .orElse(null);
    }
}
