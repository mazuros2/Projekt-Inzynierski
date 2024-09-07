import dev.projekt_inzynierski.models.Liga;
import dev.projekt_inzynierski.repository.Klub.LigaRepository;
import lombok.RequiredArgsConstructor;
import dev.projekt_inzynierski.models.Klub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;
import dev.projekt_inzynierski.repository.Klub.KlubRepository;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Component
public class AppRunner implements CommandLineRunner {

    // UWAGA coś jest nie tak z Apprunnerem i nie wyświetlają, zapisują się obiekty
    // więc wszystko robimy w projektInzynierskiApplication

    @Autowired
    private final KlubRepository klubRepository;
    @Autowired
    private final LigaRepository ligaRepository;


    @Override
    public void run(String... args) throws Exception {
        System.out.println(" ");

        Liga Ekstraklasa = Liga.builder()
                .nazwa_Ligi("Ekstraklasa")
                .poziom_Ligi(1)
                .build();

        Liga ILiga = Liga.builder()
                .nazwa_Ligi("I Liga")
                .poziom_Ligi(2)
                .build();


        ligaRepository.save(Ekstraklasa);
        ligaRepository.save(ILiga);

        //Kluby Ekstraklasy
        Klub LegiaWarszawa = Klub.builder()
                .nazwa_klubu("Legia Warszawa")
                .rok_zalozenia(LocalDate.of(1916,1,1))
                .liga(Ekstraklasa)
                .build();

        Klub LechPoznan = Klub.builder()
                .nazwa_klubu("Lech Poznań")
                .rok_zalozenia(LocalDate.of(1922,1,1))
                .liga(Ekstraklasa)
                .build();

        Klub GKSKatowice = Klub.builder()
                .nazwa_klubu("GKS Katowice")
                .rok_zalozenia(LocalDate.of(1964,1,1))
                .liga(Ekstraklasa)
                .build();


        Klub GornikZabrze = Klub.builder()
                        .nazwa_klubu("Górnik Zabrze")
                        .rok_zalozenia(LocalDate.of(1948,1,1))
                        .liga(Ekstraklasa)
                        .build();

        Klub RakowCzestochowa = Klub.builder()
                        .nazwa_klubu("Raków Częstochowa")
                        .rok_zalozenia(LocalDate.of(1921,1,1))
                        .liga(Ekstraklasa)
                        .build();


        klubRepository.save(LegiaWarszawa);
        klubRepository.save(LechPoznan);
        klubRepository.save(GKSKatowice);
        klubRepository.save(GornikZabrze);
        klubRepository.save(RakowCzestochowa);

    }
}
