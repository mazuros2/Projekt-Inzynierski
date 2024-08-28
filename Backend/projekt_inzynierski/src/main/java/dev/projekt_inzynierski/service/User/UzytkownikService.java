package dev.projekt_inzynierski.service.User;

import org.springframework.stereotype.Service;
import dev.projekt_inzynierski.repository.User.UzytkownikRepository;

@Service
public class UzytkownikService {
    private final UzytkownikRepository uzytkownikRepository;

    public UzytkownikService(UzytkownikRepository uzytkownikRepository) {
        this.uzytkownikRepository = uzytkownikRepository;
    }




}
