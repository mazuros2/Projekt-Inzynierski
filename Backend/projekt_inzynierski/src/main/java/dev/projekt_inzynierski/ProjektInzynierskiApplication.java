package dev.projekt_inzynierski;

import dev.projekt_inzynierski.models.Klub;
import dev.projekt_inzynierski.models.Liga;
import dev.projekt_inzynierski.models.Trofeum;
import dev.projekt_inzynierski.repository.Klub.KlubRepository;
import dev.projekt_inzynierski.repository.Klub.LigaRepository;
import dev.projekt_inzynierski.repository.Klub.TrofeumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ProjektInzynierskiApplication implements CommandLineRunner {

	@Autowired
	private LigaRepository ligaRepository;

	@Autowired
	private KlubRepository klubRepository;

	@Autowired
	private TrofeumRepository trofeumRepository;



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

		Liga IILiga = Liga.builder()
				.nazwa_Ligi("II Liga")
				.poziom_Ligi(3)
				.build();


		ligaRepository.save(Ekstraklasa);
		ligaRepository.save(ILiga);
		ligaRepository.save(IILiga);

		//Kluby Ekstraklasy
		Klub LegiaWarszawa = Klub.builder()
				.nazwa_klubu("Legia Warszawa")
				.rok_zalozenia(LocalDate.of(1916, 1, 1))
				.liga(Ekstraklasa)
				.trofea(new ArrayList<>())
				.logo_url("https://w7.pngwing.com/pngs/496/212/png-transparent-legia-warsaw-2018-19-uefa-champions-league-cork-city-f-c-ekstraklasa-football-logo-sports-football-team-thumbnail.png")
				.build();

		//.logo_url("")

		Klub LechPoznan = Klub.builder()
				.nazwa_klubu("Lech Poznan")
				.rok_zalozenia(LocalDate.of(1922, 1, 1))
				.liga(Ekstraklasa)
				.trofea(new ArrayList<>())
				.logo_url("https://toppng.com/uploads/preview/kks-lech-poznan-sa-vector-logo-11574309108cx5yp3ke7h.png")
				.build();

		Klub GKSKatowice = Klub.builder()
				.nazwa_klubu("GKS Katowice")
				.rok_zalozenia(LocalDate.of(1964, 1, 1))
				.liga(Ekstraklasa)
				.trofea(new ArrayList<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/commons/d/dc/GKS_KATOWICE_LOGO.png")
				.build();


		Klub GornikZabrze = Klub.builder()
				.nazwa_klubu("Gornik Zabrze")
				.rok_zalozenia(LocalDate.of(1948, 1, 1))
				.liga(Ekstraklasa)
				.trofea(new ArrayList<>())
				.logo_url("https://toppng.com/uploads/preview/ssa-gornik-shirt-badge-vector-logo-11574309412s1qabidadm.png")
				.build();

		Klub RakowCzestochowa = Klub.builder()
				.nazwa_klubu("Rakow Czestochowa")
				.rok_zalozenia(LocalDate.of(1921, 1, 1))
				.liga(Ekstraklasa)
				.trofea(new ArrayList<>())
				.logo_url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkolGfLUeNumFwx_mPaq7KONaaRhtLTiv6ow&s")
				.build();

		Klub ZaglebieLubin = Klub.builder()
				.nazwa_klubu("KGHM Zaglebie Lubin")
				.rok_zalozenia(LocalDate.of(1946, 1, 1))
				.liga(Ekstraklasa)
				.trofea(new ArrayList<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/en/thumb/2/20/Zag%C5%82%C4%99bie_Lubin_crest.svg/1200px-Zag%C5%82%C4%99bie_Lubin_crest.svg.png")
				.build();

		Klub SlaskWroclaw = Klub.builder()
				.nazwa_klubu("Slask Wroclaw")
				.rok_zalozenia(LocalDate.of(1947, 1, 1))
				.liga(Ekstraklasa)
				.trofea(new ArrayList<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/commons/thumb/7/70/Herb_%C5%9Al%C4%85ska_Wroc%C5%82aw.svg/1200px-Herb_%C5%9Al%C4%85ska_Wroc%C5%82aw.svg.png")
				.build();

		Klub RadomiakRadom = Klub.builder()
				.nazwa_klubu("Radomiak Radom")
				.rok_zalozenia(LocalDate.of(1910, 1, 1))
				.liga(Ekstraklasa)
				.trofea(new ArrayList<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/commons/2/2b/Herb_radomiaka_300dpi.png")
				.build();

		Klub JagielloniaBialystok = Klub.builder()
				.nazwa_klubu("Jagiellonia Białystok")
				.rok_zalozenia(LocalDate.of(1920, 1, 1))
				.liga(Ekstraklasa)
				.trofea(new ArrayList<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/commons/f/f9/Logo_Jagielloni_Bia%C5%82ystok.png")
				.build();

		Klub PiastGliwice = Klub.builder()
				.nazwa_klubu("Piast Gliwice")
				.rok_zalozenia(LocalDate.of(1945, 1, 1))
				.liga(Ekstraklasa)
				.trofea(new ArrayList<>())
				.logo_url("https://iconape.com/wp-content/png_logo_vector/gks-gliwice-logo.png")
				.build();

		Klub WidzewLodz = Klub.builder()
				.nazwa_klubu("Widzew Łódź")
				.rok_zalozenia(LocalDate.of(1910, 1, 1))
				.liga(Ekstraklasa)
				.trofea(new ArrayList<>())
				.logo_url("https://seeklogo.com/images/W/Widzew_Lodz-logo-70992DF075-seeklogo.com.png")
				.build();


		klubRepository.save(LegiaWarszawa);
		klubRepository.save(LechPoznan);
		klubRepository.save(GKSKatowice);
		klubRepository.save(GornikZabrze);
		klubRepository.save(RakowCzestochowa);
		klubRepository.save(ZaglebieLubin);
		klubRepository.save(SlaskWroclaw);
		klubRepository.save(RadomiakRadom);
		klubRepository.save(WidzewLodz);
		klubRepository.save(JagielloniaBialystok);
		klubRepository.save(PiastGliwice);


		//Kluby I Ligii
		Klub RuchChorzow = Klub.builder()
				.nazwa_klubu("Ruch Chorzow")
				.rok_zalozenia(LocalDate.of(1920, 1, 1))
				.liga(ILiga)
				.trofea(new ArrayList<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/commons/thumb/5/55/Ruch_Chorz%C3%B3w_-_Herb_%282021%29.svg/1200px-Ruch_Chorz%C3%B3w_-_Herb_%282021%29.svg.png")
				.build();

		Klub ZniczPruszkow = Klub.builder()
				.nazwa_klubu("Znicz Pruszkow")
				.rok_zalozenia(LocalDate.of(1922, 1, 1))
				.liga(ILiga)
				.trofea(new ArrayList<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/en/8/84/Znicz_Pruszkow.png")
				.build();

		Klub PogonSiedlce = Klub.builder()
				.nazwa_klubu("Pogon Siedlce")
				.rok_zalozenia(LocalDate.of(1944, 1, 1))
				.liga(ILiga)
				.trofea(new ArrayList<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/en/thumb/a/aa/Herb_Pogo%C5%84.cdr.svg/1200px-Herb_Pogo%C5%84.cdr.svg.png")
				.build();


		Klub GKSTychy = Klub.builder()
				.nazwa_klubu("GKS Tychy")
				.rok_zalozenia(LocalDate.of(1971, 1, 1))
				.liga(ILiga)
				.trofea(new ArrayList<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0a/Gkstychy.png/1200px-Gkstychy.png")
				.build();

		Klub BrukBetTermalica = Klub.builder()
				.nazwa_klubu("Bruk-Bet Termalica")
				.rok_zalozenia(LocalDate.of(1922, 1, 1))
				.liga(ILiga)
				.trofea(new ArrayList<>())
				.logo_url("https://termalica.brukbet.com/wp-content/uploads/2021/11/BBTN-Herb-slider.png")
				.build();

		Klub ArkaGdynia = Klub.builder()
				.nazwa_klubu("Arka Gdynia")
				.rok_zalozenia(LocalDate.of(1929, 1, 1))
				.liga(ILiga)
				.trofea(new ArrayList<>())
				.logo_url("https://arka.gdynia.pl/files/herb/arka_gdynia_mzks_kolor.png")
				.build();

		Klub MiedzLegnica = Klub.builder()
				.nazwa_klubu("Miedz Legnica")
				.rok_zalozenia(LocalDate.of(1971, 1, 1))
				.liga(ILiga)
				.trofea(new ArrayList<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/commons/9/9a/Miedzherb.png")
				.build();

		Klub GornikLeczna = Klub.builder()
				.nazwa_klubu("Gornik Leczna")
				.rok_zalozenia(LocalDate.of(1979, 1, 1))
				.liga(ILiga)
				.trofea(new ArrayList<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/en/thumb/8/82/Herb_gornik_leczna.svg/1200px-Herb_gornik_leczna.svg.png")
				.build();

		Klub WislaKrakow = Klub.builder()
				.nazwa_klubu("Wisła Kraków")
				.rok_zalozenia(LocalDate.of(1906, 1, 1))
				.liga(ILiga)
				.trofea(new ArrayList<>())
				.logo_url("https://www.wikipasy.pl/images/f/f9/Wis%C5%82a_Krak%C3%B3w_stary_herb_7.png")
				.build();

		Klub KoronaKielce = Klub.builder()
				.nazwa_klubu("Korona Kielce")
				.rok_zalozenia(LocalDate.of(1973, 1, 1))
				.liga(ILiga)
				.trofea(new ArrayList<>())
				.logo_url("https://www.wikipasy.pl/images/f/f2/Korona_Kielce_herb.png")
				.build();

		Klub OdraOpole = Klub.builder()
				.nazwa_klubu("Odra Opole")
				.rok_zalozenia(LocalDate.of(1945, 1, 1))
				.liga(ILiga)
				.trofea(new ArrayList<>())
				.logo_url("https://www.wikipasy.pl/images/2/20/Odra_Opole_herb.png")
				.build();

		klubRepository.save(RuchChorzow);
		klubRepository.save(ZniczPruszkow);
		klubRepository.save(PogonSiedlce);
		klubRepository.save(GKSTychy);
		klubRepository.save(BrukBetTermalica);
		klubRepository.save(ArkaGdynia);
		klubRepository.save(MiedzLegnica);
		klubRepository.save(GornikLeczna);
		klubRepository.save(WislaKrakow);
		klubRepository.save(KoronaKielce);
		klubRepository.save(OdraOpole);


		//Kluby z II Ligii
		Klub RekordBB = Klub.builder()
				.nazwa_klubu("Rekord Bielsko-Biala")
				.rok_zalozenia(LocalDate.of(1994, 1, 1))
				.liga(IILiga)
				.trofea(new ArrayList<>())
				.logo_url("https://bts.rekord.com.pl/static/images/logo.png")
				.build();

		Klub PodbeskidzieBB = Klub.builder()
				.nazwa_klubu("TS Podbeskidzie Bielsko-Biala")
				.rok_zalozenia(LocalDate.of(1995, 1, 1))
				.liga(IILiga)
				.trofea(new ArrayList<>())
				.logo_url("https://tspodbeskidzie.pl/static/upload/store/klub/herb/TSP_Herb.png")
				.build();

		Klub KKSKalisz = Klub.builder()
				.nazwa_klubu("KKS 1925 Kalisz")
				.rok_zalozenia(LocalDate.of(1925, 1, 1))
				.liga(IILiga)
				.trofea(new ArrayList<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/commons/6/6a/KKS_herb.png")
				.build();

		Klub ZaglebieSosnowiec = Klub.builder()
				.nazwa_klubu("Zaglebie Sosnowiec")
				.rok_zalozenia(LocalDate.of(1918, 1, 1))
				.liga(IILiga)
				.trofea(new ArrayList<>())
				.logo_url("https://www.wikipasy.pl/images/5/54/Zag%C5%82%C4%99bie_Sosnowiec_herb.png")
				.build();

		Klub KSWieczystaKrakow = Klub.builder()
				.nazwa_klubu("KS Wieczysta Krakow")
				.rok_zalozenia(LocalDate.of(1942, 1, 1))
				.liga(IILiga)
				.trofea(new ArrayList<>())
				.logo_url("https://bazaherbow.pl/wp-content/uploads/2022/05/WieczystaKrakow.png")
				.build();

		Klub ResoviaRzeszow = Klub.builder()
				.nazwa_klubu("Resovia Rzeszow")
				.rok_zalozenia(LocalDate.of(1910, 1, 1))
				.liga(IILiga)
				.trofea(new ArrayList<>())
				.logo_url("https://www.wikipasy.pl/images/4/42/Resovia_Rzesz%C3%B3w_herb.png")
				.build();

		Klub PoloniaBytom = Klub.builder()
				.nazwa_klubu("Polonia Bytom")
				.rok_zalozenia(LocalDate.of(1945, 1, 1))
				.liga(IILiga)
				.trofea(new ArrayList<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/commons/6/69/Polonia-Bytom01.png")
				.build();

		Klub OlimpiaGrudziac = Klub.builder()
				.nazwa_klubu("Olimpia Grudziac")
				.rok_zalozenia(LocalDate.of(1923, 6, 30))
				.liga(IILiga)
				.trofea(new ArrayList<>())
				.logo_url("https://seeklogo.com/images/G/GKS_Olimpia_Grudziadz-logo-00C3DCF6AB-seeklogo.com.png")
				.build();

		Klub MotorLublin = Klub.builder()
				.nazwa_klubu("Motor Lublin")
				.rok_zalozenia(LocalDate.of(1950, 1, 1))
				.liga(IILiga)
				.trofea(new ArrayList<>())
				.logo_url("https://motorlublin.com/wp-content/uploads/2016/03/herb.png")
				.build();

		Klub GarbarniaKrakow = Klub.builder()
				.nazwa_klubu("Garbarnia Kraków")
				.rok_zalozenia(LocalDate.of(1921, 1, 1))
				.liga(IILiga)
				.trofea(new ArrayList<>())
				.logo_url("https://bazaherbow.pl/wp-content/uploads/2021/10/GarbarniaKrakow.png")
				.build();

		Klub StalRzeszow = Klub.builder()
				.nazwa_klubu("Stal Rzeszów")
				.rok_zalozenia(LocalDate.of(1944, 1, 1))
				.liga(IILiga)
				.trofea(new ArrayList<>())
				.logo_url("https://stalrzeszow.pl/wp-content/uploads/2019/07/STAL-RZESZOW.png")
				.build();

		klubRepository.save(RekordBB);
		klubRepository.save(PodbeskidzieBB);
		klubRepository.save(KKSKalisz);
		klubRepository.save(ZaglebieSosnowiec);
		klubRepository.save(KSWieczystaKrakow);
		klubRepository.save(ResoviaRzeszow);
		klubRepository.save(PoloniaBytom);
		klubRepository.save(OlimpiaGrudziac);
		klubRepository.save(MotorLublin);
		klubRepository.save(GarbarniaKrakow);
		klubRepository.save(StalRzeszow);


		//Legia
		Trofeum PucharPolski2023 = Trofeum.builder()
				.data_zdobycia(LocalDate.of(2023, 5, 2))
				.nazwa("Puchar Polski 2023")
				.klub(LegiaWarszawa)
				.build();

		Trofeum MistrzPolski2021 = Trofeum.builder()
				.data_zdobycia(LocalDate.of(2021, 5, 16))
				.nazwa("Mistrz Polski 2021")
				.klub(LegiaWarszawa)
				.build();

		Trofeum MistrzPolski2020 = Trofeum.builder()
				.data_zdobycia(LocalDate.of(2020, 7, 19))
				.nazwa("Mistrz Polski 2020")
				.klub(LegiaWarszawa)
				.build();

		Trofeum MistrzPolski2018 = Trofeum.builder()
				.data_zdobycia(LocalDate.of(2018, 5, 20))
				.nazwa("Mistrz Polski 2018")
				.klub(LegiaWarszawa)
				.build();

		LegiaWarszawa.getTrofea().add(MistrzPolski2018);
		LegiaWarszawa.getTrofea().add(MistrzPolski2020);
		LegiaWarszawa.getTrofea().add(MistrzPolski2021);
		LegiaWarszawa.getTrofea().add(PucharPolski2023);
		trofeumRepository.save(MistrzPolski2018);
		trofeumRepository.save(MistrzPolski2020);
		trofeumRepository.save(MistrzPolski2021);
		trofeumRepository.save(PucharPolski2023);

		//Górnik
		Trofeum MistrzPolski1988= Trofeum.builder()
				.data_zdobycia(LocalDate.of(1988, 6, 18))
				.nazwa("Mistrz Polski 1988")
				.klub(GornikZabrze)
				.build();

		Trofeum MistrzPolski1987 = Trofeum.builder()
				.data_zdobycia(LocalDate.of(1987, 6, 21))
				.nazwa("Mistrz Polski 1987")
				.klub(GornikZabrze)
				.build();

		Trofeum MistrzPolski1986 = Trofeum.builder()
				.data_zdobycia(LocalDate.of(1986, 4, 27))
				.nazwa("Mistrz Polski 1986")
				.klub(GornikZabrze)
				.build();

		Trofeum MistrzPolski1985 = Trofeum.builder()
				.data_zdobycia(LocalDate.of(1985, 6, 23))
				.nazwa("Mistrz Polski 1985")
				.klub(GornikZabrze)
				.build();

		GornikZabrze.getTrofea().add(MistrzPolski1985);
		GornikZabrze.getTrofea().add(MistrzPolski1986);
		GornikZabrze.getTrofea().add(MistrzPolski1987);
		GornikZabrze.getTrofea().add(MistrzPolski1988);
		trofeumRepository.save(MistrzPolski1985);
		trofeumRepository.save(MistrzPolski1986);
		trofeumRepository.save(MistrzPolski1987);
		trofeumRepository.save(MistrzPolski1988);

		//KGHM Zagłębie Lubin
		Trofeum MistrzPolski1991 = Trofeum.builder()
				.data_zdobycia(LocalDate.of(1991, 6, 19))
				.nazwa("Mistrz Polski 1991")
				.klub(ZaglebieLubin)
				.build();

		Trofeum MistrzPolski2007 = Trofeum.builder()
				.data_zdobycia(LocalDate.of(2007, 5, 26))
				.nazwa("Mistrz Polski 2007")
				.klub(ZaglebieLubin)
				.build();

		ZaglebieLubin.getTrofea().add(MistrzPolski1991);
		ZaglebieLubin.getTrofea().add(MistrzPolski2007);
		trofeumRepository.save(MistrzPolski1991);
		trofeumRepository.save(MistrzPolski2007);

		//GKS Katowice
		Trofeum PucharPolski1993 = Trofeum.builder()
				.data_zdobycia(LocalDate.of(1993, 6, 23))
				.nazwa("Puchar Polski 1993")
				.klub(GKSKatowice)
				.build();

		Trofeum PucharPolski1991 = Trofeum.builder()
				.data_zdobycia(LocalDate.of(1991, 6, 23))
				.nazwa("Puchar Polski 1991")
				.klub(GKSKatowice)
				.build();

		Trofeum PucharPolski1986 = Trofeum.builder()
				.data_zdobycia(LocalDate.of(1986, 5, 1))
				.nazwa("Puchar Polski 1986")
				.klub(GKSKatowice)
				.build();

		GKSKatowice.getTrofea().add(PucharPolski1986);
		GKSKatowice.getTrofea().add(PucharPolski1991);
		GKSKatowice.getTrofea().add(PucharPolski1993);
		trofeumRepository.save(PucharPolski1986);
		trofeumRepository.save(PucharPolski1991);
		trofeumRepository.save(PucharPolski1993);

		Trofeum WicemistrzPolski2013 = Trofeum.builder()
				.data_zdobycia(LocalDate.of(2013, 2, 6))
				.nazwa("Wicemistrz Polski 2013")
				.klub(LechPoznan)
				.build();
		Trofeum WicemistrzPolski2014 = Trofeum.builder()
				.data_zdobycia(LocalDate.of(2014, 5, 7))
				.nazwa("Wicemistrz Polski 2014")
				.klub(LechPoznan)
				.build();
		Trofeum WicemistrzPolski2020 = Trofeum.builder()
				.data_zdobycia(LocalDate.of(2020, 5, 21))
				.nazwa("Wicemistrz Polski 2020")
				.klub(LechPoznan)
				.build();
		Trofeum mistrzPolski2022 = Trofeum.builder()
				.data_zdobycia(LocalDate.of(2022, 5, 21))
				.nazwa("Mistrz Polski 2022")
				.klub(LechPoznan)
				.build();
		LechPoznan.getTrofea().add(WicemistrzPolski2013);
		LechPoznan.getTrofea().add(WicemistrzPolski2014);
		LechPoznan.getTrofea().add(WicemistrzPolski2020);
		LechPoznan.getTrofea().add(mistrzPolski2022);
		trofeumRepository.save(WicemistrzPolski2013);
		trofeumRepository.save(WicemistrzPolski2014);
		trofeumRepository.save(WicemistrzPolski2020);
		trofeumRepository.save(mistrzPolski2022);

		Trofeum mistrzPolski1977 = Trofeum.builder()
				.data_zdobycia(LocalDate.of(1977, 6, 1))
				.nazwa("Mistrz Polski 1977")
				.klub(SlaskWroclaw)
				.build();
		Trofeum wicemistrzPolski2024 = Trofeum.builder()
				.data_zdobycia(LocalDate.of(2024, 7, 20))
				.nazwa("Wicemistrz Polski 2024")
				.klub(SlaskWroclaw)
				.build();
		Trofeum mistrzPolski2012 = Trofeum.builder()
				.data_zdobycia(LocalDate.of(2012, 6, 2))
				.nazwa("Mistrz Polski 2012")
				.klub(SlaskWroclaw)
				.build();
		Trofeum wicemistrzPolski2011 = Trofeum.builder()
				.data_zdobycia(LocalDate.of(2011, 10, 28))
				.nazwa("Wicemistrz Polski 2011")
				.klub(SlaskWroclaw)
				.build();
		Trofeum wicemistrzPolski1982 = Trofeum.builder()
				.data_zdobycia(LocalDate.of(1982, 1, 1))
				.nazwa("Wicemistrz Polski 1982")
				.klub(SlaskWroclaw)
				.build();
		SlaskWroclaw.getTrofea().add(mistrzPolski2012);
		SlaskWroclaw.getTrofea().add(wicemistrzPolski2024);
		SlaskWroclaw.getTrofea().add(wicemistrzPolski2011);
		SlaskWroclaw.getTrofea().add(wicemistrzPolski1982);
		trofeumRepository.save(mistrzPolski2012);
		trofeumRepository.save(wicemistrzPolski2024);
		trofeumRepository.save(wicemistrzPolski2011);
		trofeumRepository.save(wicemistrzPolski1982);


		System.out.println("\n");
		List<Trofeum> trofea = LegiaWarszawa.getTrofea();
		System.out.println("Trofea Legii Warszawa");
		for (Trofeum trofeum : trofea) {
			System.out.println("- " + trofeum.getNazwa() + " (Zdobyte: " + trofeum.getData_zdobycia() + ")");
		}

		System.out.println("");
		List<Trofeum> trofeaG = GornikZabrze.getTrofea();
		System.out.println("Trofea Gornika Zabrze");
		for (Trofeum trofeum : trofeaG) {
			System.out.println("- " + trofeum.getNazwa() + " (Zdobyte: " + trofeum.getData_zdobycia() + ")");
		}

		System.out.println("");
		List<Trofeum> trofeaZL = ZaglebieLubin.getTrofea();
		System.out.println("Trofea KGHM Zaglebia Lubin");
		for (Trofeum trofeum : trofeaZL) {
			System.out.println("- " + trofeum.getNazwa() + " (Zdobyte: " + trofeum.getData_zdobycia() + ")");
		}

		System.out.println("");
		List<Trofeum> trofeaGKSK = GKSKatowice.getTrofea();
		System.out.println("Trofea GKS Katowice");
		for (Trofeum trofeum : trofeaGKSK) {
			System.out.println("- " + trofeum.getNazwa() + " (Zdobyte: " + trofeum.getData_zdobycia() + ")");
		}

		List<Trofeum> trofeaLech = LechPoznan.getTrofea();
		System.out.println("Trofea Lecha Poznan");
		for (Trofeum trofeum : trofeaLech) {
			System.out.println("- " + trofeum.getNazwa() + " (Zdobyte: " + trofeum.getData_zdobycia().getYear() + ")");
		}

		List<Trofeum> trofeaSlaskWroclaw = LechPoznan.getTrofea();
		System.out.println("Trofea Slaska Wroclaw");
		for (Trofeum trofeum : trofeaSlaskWroclaw) {
			System.out.println("- " + trofeum.getNazwa() + " (Zdobyte: " + trofeum.getData_zdobycia().getYear() + ")");
		}
		System.out.println("\n");
		System.out.println("\n");


		List<Klub> kluby = klubRepository.findAll();
		System.out.println("Lista klubow:");
		for (Klub klub : kluby) {
			System.out.println("Klub: " + klub.getNazwa_klubu() + ", Rok zalozenia: " + klub.getRok_zalozenia() + ", Liga: " + klub.getLiga().getNazwa_Ligi());
		}
	}
}
