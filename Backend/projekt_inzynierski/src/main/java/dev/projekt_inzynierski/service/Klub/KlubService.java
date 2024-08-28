package dev.projekt_inzynierski.service.Klub;

import dev.projekt_inzynierski.models.Klub;
import dev.projekt_inzynierski.repository.Klub.KlubRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KlubService {
    private final KlubRepository klubRepository;

    public KlubService(KlubRepository klubRepository) {
        this.klubRepository = klubRepository;
    }

    public List<Klub> getAllKlubyByLigaId(long ligaId) {
        return klubRepository.findAllByLigaId(ligaId);
    }


}
