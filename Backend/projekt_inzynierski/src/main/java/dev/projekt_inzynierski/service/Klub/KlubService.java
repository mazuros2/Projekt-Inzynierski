package dev.projekt_inzynierski.service.Klub;

import dev.projekt_inzynierski.DTO.*;
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

    public List<KlubByIdDTO> getKluby() {
        return klubRepository.getKluby();
    }

    public List<KlubByIdDTO> getKlubywithoutTrener(){
        return klubRepository.getKlubywithoutTrener();
    }

    public List<KlubByIdDTO> getKlubywithoutSkaut(){
        return klubRepository.getKlubywithoutSkaut();
    }

    public List<KlubByIdDTO> getKlubywithoutMenadzer(){
        return klubRepository.getKlubywithoutMenadzer();
    }

    public List<KlubByIdDTO> getKlubyWithMenadzer(){
        return klubRepository.getKlubywithMenadzer();
    }

    public List<KlubByIdDTO> getKlubyWithTrener(){
        return klubRepository.getKlubywithTrener();
    }

    public List<KlubByIdDTO> getKlubyWithSkaut(){
        return klubRepository.getKlubywithSkaut();
    }

    public List<KlubFromLigaDTO> getAllKlubyByLigaId(long ligaId) {
        return klubRepository.findAllByLigaId(ligaId);
    }

    public KlubByIdDTO getKlubById(long id) {
        return klubRepository.findKlubById(id);
    }

    public List<ZawodnikDTO> findZawodnicyByIdKlub(long id_klub) {return klubRepository.findZawodnicyByIdKlub(id_klub);}

    public List<KlubDTO> findKlubByNazwa(String nazwa){
       return klubRepository.findKlubByNazwa("%" + nazwa + "%");
    }
}
