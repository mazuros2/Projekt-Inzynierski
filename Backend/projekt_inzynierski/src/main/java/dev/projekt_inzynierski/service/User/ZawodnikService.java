package dev.projekt_inzynierski.service.User;

import dev.projekt_inzynierski.DTO.KlubByIdDTO;
import dev.projekt_inzynierski.DTO.ZawodnikByIdDTO;
import dev.projekt_inzynierski.models.users.Zawodnik;
import org.springframework.stereotype.Service;
import dev.projekt_inzynierski.repository.User.ZawodnikRepository;

import java.util.Optional;

@Service
public class ZawodnikService {
    private final ZawodnikRepository zawodnikRepository;

    public ZawodnikService(ZawodnikRepository zawodnikRepository) {
        this.zawodnikRepository = zawodnikRepository;
    }
    public ZawodnikByIdDTO getZawodnikById(long id) {
        return zawodnikRepository.findZawodnikById(id);
    }

//    public Zawodnik addZawodnik(Zawodnik zawodnik){
//        return zawodnikRepository.addZawodnik(zawodnik);
//    }
}
