package dev.projekt_inzynierski.service;

import dev.projekt_inzynierski.repository.Badania_LekarskieRepository;
import org.springframework.stereotype.Service;

@Service
public class Badania_LekarskieService {
    private Badania_LekarskieRepository badaniaLekarskieRepository;
    public Badania_LekarskieService(Badania_LekarskieRepository badaniaLekarskieRepository) {
        this.badaniaLekarskieRepository=badaniaLekarskieRepository;
    }
}
