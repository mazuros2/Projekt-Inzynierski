package dev.projekt_inzynierski.service.User;

import dev.projekt_inzynierski.DTO.*;
import dev.projekt_inzynierski.models.Kraj_pochodzenia;
import dev.projekt_inzynierski.models.users.Trener;
import dev.projekt_inzynierski.repository.User.TrenerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<TrenerDTO2> getAllTrenerzy() {
        List<Trener> trenerzy = trenerRepository.findAll();
        return trenerzy.stream().map( trener -> {
            Set<KrajPochodzeniaDTO> krajePochodzeniaDTO = trener.getKraj_pochodzenia().stream()
                    .map(kraj -> new KrajPochodzeniaDTO(
                            kraj.getId_Kraj(),
                            kraj.getNazwa(),
                            kraj.getRegion()
                    )).collect(Collectors.toSet());

            return new TrenerDTO2(
                    trener.getId_Uzytkownik(),
                    trener.getImie(),
                    trener.getNazwisko(),
                    krajePochodzeniaDTO,
                    trener.getLicencja_trenera(),
                    trener.getTrenerKlub().getNazwa_klubu()
            );

        }).collect(Collectors.toList());
    }

    public TrenerDTO3 findByKlubId(long id_klub) {
        return trenerRepository.findById_Klub(id_klub);
    }

}