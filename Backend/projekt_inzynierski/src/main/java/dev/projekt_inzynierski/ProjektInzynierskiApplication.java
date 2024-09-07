package dev.projekt_inzynierski;

import dev.projekt_inzynierski.models.Klub;
import dev.projekt_inzynierski.models.Liga;
import dev.projekt_inzynierski.repository.Klub.KlubRepository;
import dev.projekt_inzynierski.repository.Klub.LigaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class ProjektInzynierskiApplication implements CommandLineRunner {

	@Autowired
	private LigaRepository ligaRepository;

	@Autowired
	private KlubRepository klubRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjektInzynierskiApplication.class, args);
	}

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
				.nazwa_klubu("Lech Poznan")
				.rok_zalozenia(LocalDate.of(1922,1,1))
				.liga(Ekstraklasa)
				.build();

		Klub GKSKatowice = Klub.builder()
				.nazwa_klubu("GKS Katowice")
				.rok_zalozenia(LocalDate.of(1964,1,1))
				.liga(Ekstraklasa)
				.build();


		Klub GornikZabrze = Klub.builder()
				.nazwa_klubu("Gornik Zabrze")
				.rok_zalozenia(LocalDate.of(1948,1,1))
				.liga(Ekstraklasa)
				.build();

		Klub RakowCzestochowa = Klub.builder()
				.nazwa_klubu("Rakow Czestochowa")
				.rok_zalozenia(LocalDate.of(1921,1,1))
				.liga(Ekstraklasa)
				.build();

		Klub ZaglebieLubin = Klub.builder()
				.nazwa_klubu("KGHM Zagłębie Lubin")
				.rok_zalozenia(LocalDate.of(1946,1,1))
				.liga(Ekstraklasa)
				.build();


		Klub SlaskWroclaw = Klub.builder()
				.nazwa_klubu("Slask Wroclaw")
				.rok_zalozenia(LocalDate.of(1947,1,1))
				.liga(Ekstraklasa)
				.build();

		Klub RadomiakRadom = Klub.builder()
				.nazwa_klubu("Radomiak Radom")
				.rok_zalozenia(LocalDate.of(1910,1,1))
				.liga(Ekstraklasa)
				.build();

		klubRepository.save(LegiaWarszawa);
		klubRepository.save(LechPoznan);
		klubRepository.save(GKSKatowice);
		klubRepository.save(GornikZabrze);
		klubRepository.save(RakowCzestochowa);
		klubRepository.save(ZaglebieLubin);
		klubRepository.save(SlaskWroclaw);
		klubRepository.save(RadomiakRadom);

		//Kluby I Ligii
		Klub RuchChorzow = Klub.builder()
				.nazwa_klubu("Ruch Chorzow")
				.rok_zalozenia(LocalDate.of(1920,1,1))
				.liga(ILiga)
				.build();

		Klub ZniczPruszkow = Klub.builder()
				.nazwa_klubu("Znicz Pruszkow")
				.rok_zalozenia(LocalDate.of(1922,1,1))
				.liga(ILiga)
				.build();

		Klub PogonSiedlce = Klub.builder()
				.nazwa_klubu("Pogon Siedlce")
				.rok_zalozenia(LocalDate.of(1944,1,1))
				.liga(ILiga)
				.build();


		Klub GKSTychy = Klub.builder()
				.nazwa_klubu("GKS Tychy")
				.rok_zalozenia(LocalDate.of(1971,1,1))
				.liga(ILiga)
				.build();

		Klub BrukBetTermalica = Klub.builder()
				.nazwa_klubu("Bruk-Bet Termalica")
				.rok_zalozenia(LocalDate.of(1922,1,1))
				.liga(ILiga)
				.build();

		Klub ArkaGdynia = Klub.builder()
				.nazwa_klubu("Arka Gdynia")
				.rok_zalozenia(LocalDate.of(1929,1,1))
				.liga(ILiga)
				.build();

		Klub MiedzLegnica = Klub.builder()
				.nazwa_klubu("Miedz Legnica")
				.rok_zalozenia(LocalDate.of(1971,1,1))
				.liga(ILiga)
				.build();

		Klub GornikLeczna = Klub.builder()
				.nazwa_klubu("Gornik Leczna")
				.rok_zalozenia(LocalDate.of(1979,1,1))
				.liga(ILiga)
				.build();


		klubRepository.save(RuchChorzow);
		klubRepository.save(ZniczPruszkow);
		klubRepository.save(PogonSiedlce);
		klubRepository.save(GKSTychy);
		klubRepository.save(BrukBetTermalica);
		klubRepository.save(ArkaGdynia);
		klubRepository.save(MiedzLegnica);
		klubRepository.save(GornikLeczna);

		List<Klub> kluby = klubRepository.findAll();
		System.out.println("Lista klubow:");
		for (Klub klub : kluby) {
			System.out.println("Klub: " + klub.getNazwa_klubu() + ", Rok zalozenia: " + klub.getRok_zalozenia()+ " Liga: " + klub.getLiga().getNazwa_Ligi());
		}
	}
}
