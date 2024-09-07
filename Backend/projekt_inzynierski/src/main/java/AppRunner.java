import dev.projekt_inzynierski.models.Liga;
import dev.projekt_inzynierski.repository.Klub.LigaRepository;
import lombok.RequiredArgsConstructor;
import dev.projekt_inzynierski.models.Klub;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import dev.projekt_inzynierski.repository.Klub.KlubRepository;

@RequiredArgsConstructor
@Component
public class AppRunner implements CommandLineRunner {

    private final KlubRepository klubRepository;
    private final LigaRepository ligaRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(" ");
        klubRepository.findAll();
        klubRepository.save(Klub.builder().nazwa_klubu("xd").build());

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



    }
}
