package dev.projekt_inzynierski.service.User;

import dev.projekt_inzynierski.DTO.TrenerDTO;
import dev.projekt_inzynierski.models.Kraj_pochodzenia;
import dev.projekt_inzynierski.models.users.Trener;
import dev.projekt_inzynierski.repository.User.TrenerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TrenerService {
    private final TrenerRepository trenerRepository;

    public TrenerService(TrenerRepository trenerRepository) {
        this.trenerRepository = trenerRepository;
    }

    public TrenerDTO getTrenerInfoById(long id){
        Trener trenerinfo = trenerRepository.findById(id)
                            .orElseThrow( () ->
                                    new EntityNotFoundException("Nie można znaleźć trenera o takim id!"));

        String klubTrenera = trenerinfo.getTrenerKlub().getNazwa_klubu(); // tu też trzeba coś zrobić jakby był null

        Set<String> krajePochodzenia = trenerinfo.getKraj_pochodzenia().stream()
                .map(Kraj_pochodzenia::getNazwa)
                .collect(Collectors.toSet());

        return new TrenerDTO(
            trenerinfo.getImie(),
            trenerinfo.getNazwisko(),
            trenerinfo.getData_Urodzenia(),
            krajePochodzenia,
            trenerinfo.getLicencja_trenera(),
            klubTrenera
        );
    }

}
