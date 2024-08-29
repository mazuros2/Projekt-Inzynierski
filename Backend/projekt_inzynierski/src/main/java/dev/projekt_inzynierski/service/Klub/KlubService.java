package dev.projekt_inzynierski.service.Klub;

import dev.projekt_inzynierski.models.Klub;
import dev.projekt_inzynierski.repository.Klub.KlubRepository;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KlubService {
    private final KlubRepository klubRepository;

    @Autowired
    public KlubService(KlubRepository klubRepository) {
        this.klubRepository = klubRepository;
    }

    public List<Klub> getKluby() {
        return klubRepository.getKluby();
    }

    public List<Klub> getAllKlubyByLigaId(long ligaId) {
        return klubRepository.findAllByLigaId(ligaId);
    }

    public Klub getKlubById(long id) {
        try {
            return klubRepository.findKlubById(id);
        } catch (NoResultException e) {
            throw new ResourceNotFoundException("Klub not found with id " + id, e);
        }
    }

}
