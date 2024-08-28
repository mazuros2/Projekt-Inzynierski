import lombok.RequiredArgsConstructor;
import dev.projekt_inzynierski.models.Klub;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import dev.projekt_inzynierski.repository.Klub.KlubRepository;

@RequiredArgsConstructor
@Component
public class AppRunner implements CommandLineRunner {

    private final KlubRepository klubRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(" ");
        klubRepository.findAll();
        klubRepository.save(Klub.builder().nazwa_klubu("xd").build());
    }
}
