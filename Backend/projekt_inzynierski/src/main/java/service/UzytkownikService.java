package service;

import org.springframework.stereotype.Service;
import repository.UzytkownikRepository;

@Service
public class UzytkownikService {
    private final UzytkownikRepository uzytkownikRepository;

    public UzytkownikService(UzytkownikRepository uzytkownikRepository) {
        this.uzytkownikRepository = uzytkownikRepository;
    }




}
