package dev.projekt_inzynierski;

import dev.projekt_inzynierski.configurationJWT.Role;
import dev.projekt_inzynierski.models.*;
import dev.projekt_inzynierski.models.users.Skaut;
import dev.projekt_inzynierski.models.users.Trener;
import dev.projekt_inzynierski.models.users.Uzytkownik;
import dev.projekt_inzynierski.models.users.Zawodnik;
import dev.projekt_inzynierski.repository.Klub.KlubRepository;
import dev.projekt_inzynierski.repository.Klub.LigaRepository;
import dev.projekt_inzynierski.repository.Klub.TrofeumRepository;
import dev.projekt_inzynierski.repository.Kraj_pochodzeniaRepository;
import dev.projekt_inzynierski.repository.PozycjaRepository;
import dev.projekt_inzynierski.repository.User.SkautRepository;
import dev.projekt_inzynierski.repository.User.TrenerRepository;
import dev.projekt_inzynierski.repository.User.UzytkownikRepository;
import dev.projekt_inzynierski.repository.User.ZawodnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ProjektInzynierskiApplication implements CommandLineRunner {

	@Autowired
	private LigaRepository ligaRepository;

	@Autowired
	private KlubRepository klubRepository;

	@Autowired
	private TrofeumRepository trofeumRepository;

	@Autowired
	private UzytkownikRepository uzytkownikRepository;

	@Autowired
	private Kraj_pochodzeniaRepository kraj_pochodzeniaRepository;


	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private PozycjaRepository pozycjaRepository;
	@Autowired
	private ZawodnikRepository zawodnikRepository;


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

		Pozycja napastnikSN = Pozycja.builder()
				.nazwa_pozycji("Środkowy napastnik")
				.obszar_pozycji("Napastnik")
				.build();

		Pozycja bramkarzBR = Pozycja.builder()
				.nazwa_pozycji("Bramkarz")
				.obszar_pozycji("Bramkarz")
				.build();

		Pozycja obroncaLO = Pozycja.builder()
				.nazwa_pozycji("Lewy obrońca")
				.obszar_pozycji("Obrona")
				.build();

		Pozycja obroncaSO= Pozycja.builder()
				.nazwa_pozycji("Środkowy obrońca")
				.obszar_pozycji("Obrona")
				.build();

		Pozycja obroncaPO= Pozycja.builder()
				.nazwa_pozycji("Prawy obrońca")
				.obszar_pozycji("Obrona")
				.build();
		Pozycja pomocnik= Pozycja.builder()
				.nazwa_pozycji("Pomocnik")
				.obszar_pozycji("Pomocnik")
				.build();
		pozycjaRepository.save(pomocnik);
		pozycjaRepository.save(napastnikSN);
		pozycjaRepository.save(bramkarzBR);
		pozycjaRepository.save(obroncaLO);
		pozycjaRepository.save(obroncaSO);
		pozycjaRepository.save(obroncaPO);
		Kraj_pochodzenia ES = Kraj_pochodzenia.builder()
				.region("Europa")
				.nazwa("Hiszpania")
				.build();

		Kraj_pochodzenia CZ = Kraj_pochodzenia.builder()
				.region("Europa")
				.nazwa("Czechy")
				.build();

		Kraj_pochodzenia CM = Kraj_pochodzenia.builder()
				.region("Afryka")
				.nazwa("Kamerun")
				.build();

		Kraj_pochodzenia JP = Kraj_pochodzenia.builder()
				.region("Azja")
				.nazwa("Japonia")
				.build();

		Kraj_pochodzenia AL = Kraj_pochodzenia.builder()
				.region("Europa")
				.nazwa("Albania")
				.build();

		Kraj_pochodzenia PT = Kraj_pochodzenia.builder()
				.region("Europa")
				.nazwa("Portugalia")
				.build();

		Kraj_pochodzenia CH = Kraj_pochodzenia.builder()
				.region("Europa")
				.nazwa("Szwajcaria")
				.build();

		Kraj_pochodzenia AR = Kraj_pochodzenia.builder()
				.region("Ameryka Południowa")
				.nazwa("Argentyna")
				.build();

		Kraj_pochodzenia FR = Kraj_pochodzenia.builder()
				.region("Europa")
				.nazwa("Francja")
				.build();

		Kraj_pochodzenia CO = Kraj_pochodzenia.builder()
				.region("Ameryka Południowa")
				.nazwa("Kolumbia")
				.build();


		kraj_pochodzeniaRepository.saveAll(List.of(
				ES, CZ, CM, JP, AL, PT, CH, AR, FR, CO
		));


		Kraj_pochodzenia PL = Kraj_pochodzenia.builder()
				.region("Europa")
				.nazwa("Polska")
				.build();

		Kraj_pochodzenia SR = Kraj_pochodzenia.builder()
				.region("Europa")
				.nazwa("Serbia")
				.build();

		Kraj_pochodzenia BR = Kraj_pochodzenia.builder()
				.region("Ameryka Południowa")
				.nazwa("Brazylia")
				.build();

		kraj_pochodzeniaRepository.save(PL);
		kraj_pochodzeniaRepository.save(SR);
		kraj_pochodzeniaRepository.save(BR);


		Uzytkownik adminTest = Uzytkownik.builder()
				.login("admin")
				.email("admin@gmail.com")
				.imie("Admin")
				.kraj_pochodzenia(Set.of(PL))
				.nazwisko("Adminowski")
				.data_Urodzenia(LocalDate.now())
				.pesel(12)
				.haslo("admin")
				.role(Role.ADMIN)
				.build();


		Uzytkownik skautTest = Uzytkownik.builder()
				.login("skaut")
				.email("skaut@gmail.com")
				.imie("Skaut")
				.nazwisko("Skautowski")
				.kraj_pochodzenia(Set.of(PL,BR))
				.data_Urodzenia(LocalDate.now())
				.pesel(123)
				.haslo("skaut")
				.role(Role.SKAUT)
				.build();

		uzytkownikRepository.save(adminTest);
		uzytkownikRepository.save(skautTest);

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


		//Zawodnicy
		Zawodnik KT = Zawodnik.builder()
				.login("KT1")
				.email("KacperTobiasz1@gmail.com")
				.imie("Kacper")
				.nazwisko("Tobiasz")
				.data_Urodzenia(LocalDate.of(2002, 11, 4))
				.pesel(123456789)
				.haslo(passwordEncoder.encode("zawodnik1"))
				.role(Role.ZAWODNIK)
				.waga(80)
				.wzrost(191)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(bramkarzBR)
				.build();

		Zawodnik PW = Zawodnik.builder()
				.login("PW13")
				.email("PawelWszolek13@gmail.com")
				.imie("Paweł")
				.nazwisko("Wszołek")
				.data_Urodzenia(LocalDate.of(1992, 5, 30))
				.pesel(123456789)
				.haslo(passwordEncoder.encode("prawy1"))
				.role(Role.ZAWODNIK)
				.waga(78)
				.wzrost(186)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(obroncaPO)
				.build();

		Zawodnik RP = Zawodnik.builder()
				.login("RP12")
				.email("RadovanPankov12@gmail.com")
				.imie("Radovan")
				.nazwisko("Pankov")
				.data_Urodzenia(LocalDate.of(1995, 8, 5))
				.pesel(123456789)
				.haslo(passwordEncoder.encode("obronca1"))
				.role(Role.ZAWODNIK)
				.waga(80)
				.wzrost(185)
				.kraj_pochodzenia(Set.of(SR))
				.pozycja(obroncaPO)
				.build();

		Obecny_klub KlubKT = Obecny_klub.builder()
				.zawodnik(KT)
				.klub(LegiaWarszawa)
				.data_Od(LocalDate.now())
				.build();

		Obecny_klub KlubPW = Obecny_klub.builder()
				.zawodnik(PW)
				.klub(LegiaWarszawa)
				.data_Od(LocalDate.now())
				.build();

		Obecny_klub KlubRP = Obecny_klub.builder()
				.zawodnik(RP)
				.klub(LegiaWarszawa)
				.data_Od(LocalDate.now())
				.build();

		KT.setObecny_klub(List.of(KlubKT));
		PW.setObecny_klub(List.of(KlubPW));
		RP.setObecny_klub(List.of(KlubRP));


		//Sztab klubu
		Trener GF = Trener.builder()
				.login("GF1")
				.email("GF1@gmail.com")
				.imie("Goncalo")
				.nazwisko("Feio")
				.data_Urodzenia(LocalDate.of(1990, 1, 17))
				.pesel(987654321)
				.haslo(passwordEncoder.encode("trener123"))
				.role(Role.TRENER)
				.kraj_pochodzenia(Set.of(PL))
				.licencja_trenera("UEFA PRO")
				.trenerKlub(LegiaWarszawa)
				.build();

		Skaut skaut = Skaut.builder()
				.login("skaut1")
				.email("skaut1@gmail.com")
				.imie("Michał")
				.nazwisko("Wiśniewski")
				.data_Urodzenia(LocalDate.of(1990, 7, 25))
				.pesel(567891234)
				.haslo(passwordEncoder.encode("skaut123"))
				.role(Role.SKAUT)
				.kraj_pochodzenia(Set.of(PL))
				.skautKlubu(LegiaWarszawa)
				.build();

		uzytkownikRepository.save(KT);
		uzytkownikRepository.save(PW);
		uzytkownikRepository.save(RP);
		uzytkownikRepository.save(GF);
		uzytkownikRepository.save(skaut);


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

		Zawodnik kacperTobiasz = Zawodnik.builder()
				.login("k.tobiasz")
				.email("k.tobiasz@legia.pl")
				.imie("Kacper")
				.nazwisko("Tobiasz")
				.waga(78)
				.wzrost(191)
				.pozycja(bramkarzBR)
				.data_Urodzenia(LocalDate.of(2002, 11, 4))
				.pesel(100200300)
				.haslo(passwordEncoder.encode("haslo123"))
				.role(Role.ZAWODNIK)
				.kraj_pochodzenia(Set.of(PL))
				.build();

		Zawodnik arturJedrzejczyk = Zawodnik.builder()
				.login("a.jedrzejczyk")
				.email("a.jedrzejczyk@legia.pl")
				.imie("Artur")
				.nazwisko("Jędrzejczyk")
				.waga(78)
				.wzrost(189)
				.pozycja(obroncaSO)
				.data_Urodzenia(LocalDate.of(1987, 11, 4))
				.pesel(100200301)
				.haslo(passwordEncoder.encode("haslo123"))
				.role(Role.ZAWODNIK)
				.kraj_pochodzenia(Set.of(PL))
				.build();

		Zawodnik bartoszKapustka = Zawodnik.builder()
				.login("b.kapustka")
				.email("b.kapustka@legia.pl")
				.imie("Bartosz")
				.nazwisko("Kapustka")
				.waga(67)
				.wzrost(179)
				.pozycja(pomocnik)
				.data_Urodzenia(LocalDate.of(1996, 12, 23))
				.pesel(100200302)
				.haslo(passwordEncoder.encode("haslo123"))
				.role(Role.ZAWODNIK)
				.kraj_pochodzenia(Set.of(PL))
				.build();

		Zawodnik tomasPekhart = Zawodnik.builder()
				.login("t.pekhart")
				.email("t.pekhart@legia.pl")
				.imie("Tomas")
				.nazwisko("Pekhart")
				.waga(82)
				.wzrost(194)
				.pozycja(napastnikSN)
				.data_Urodzenia(LocalDate.of(1989, 5, 26))
				.pesel(100200303)
				.haslo(passwordEncoder.encode("haslo123"))
				.role(Role.ZAWODNIK)
				.kraj_pochodzenia(Set.of(CZ))
				.build();

		Zawodnik marcGual = Zawodnik.builder()
				.login("m.gual")
				.email("m.gual@legia.pl")
				.imie("Marc")
				.nazwisko("Gual")
				.waga(73)
				.wzrost(184)
				.pozycja(napastnikSN)
				.data_Urodzenia(LocalDate.of(1996, 3, 13))
				.pesel(100200304)
				.haslo(passwordEncoder.encode("haslo123"))
				.role(Role.ZAWODNIK)
				.kraj_pochodzenia(Set.of(ES))
				.build();
		zawodnikRepository.saveAll(List.of(
				kacperTobiasz,
				arturJedrzejczyk,
				bartoszKapustka,
				tomasPekhart,
				marcGual
		));
		Zawodnik GabrielKobylak = Zawodnik.builder()
				.login("g.kobylak")
				.haslo(passwordEncoder.encode("password"))
				.email("gabriel.kobylak@legia.pl")
				.imie("Gabriel")
				.nazwisko("Kobylak")
				.pesel(20020220)
				.role(Role.ZAWODNIK)
				.data_Urodzenia(LocalDate.of(2002, 2, 20))
				.kraj_pochodzenia(Set.of(PL))
				.waga(79)
				.wzrost(184)
				.pozycja(bramkarzBR)
				.build();

		Zawodnik MarcelMendesDudzinski = Zawodnik.builder()
				.login("m.mendes")
				.haslo(passwordEncoder.encode("password"))
				.email("marcel.mendes@legia.pl")
				.imie("Marcel")
				.nazwisko("Mendes-Dudziński")
				.pesel(20050514)
				.role(Role.ZAWODNIK)
				.data_Urodzenia(LocalDate.of(2005, 5, 14))
				.kraj_pochodzenia(Set.of(PL))
				.waga(85)
				.wzrost(197)
				.pozycja(bramkarzBR)

				.build();

		Zawodnik RubenVinagre = Zawodnik.builder()
				.login("r.vinagre")
				.haslo(passwordEncoder.encode("password"))
				.email("ruben.vinagre@legia.pl")
				.imie("Ruben")
				.nazwisko("Vinagre")
				.pesel(19990409)
				.role(Role.ZAWODNIK)
				.data_Urodzenia(LocalDate.of(1999, 4, 9))
				.kraj_pochodzenia(Set.of(PT))
				.waga(71)
				.wzrost(174)
				.pozycja(obroncaLO)

				.build();

		Zawodnik JurgenCelhaka = Zawodnik.builder()
				.login("j.celhaka")
				.haslo(passwordEncoder.encode("password"))
				.email("jurgen.celhaka@legia.pl")
				.imie("Jurgen")
				.nazwisko("Celhaka")
				.pesel(20001206)
				.role(Role.ZAWODNIK)
				.data_Urodzenia(LocalDate.of(2000, 12, 6))
				.kraj_pochodzenia(Set.of(AL))
				.waga(75)
				.wzrost(182)
				.pozycja(pomocnik)

				.build();

		Zawodnik JuergenElitim = Zawodnik.builder()
				.login("j.elitim")
				.haslo(passwordEncoder.encode("password"))
				.email("juergen.elitim@legia.pl")
				.imie("Juergen")
				.nazwisko("Elitim")
				.pesel(19990713)
				.role(Role.ZAWODNIK)
				.data_Urodzenia(LocalDate.of(1999, 7, 13))
				.kraj_pochodzenia(Set.of(CO))
				.waga(72)
				.wzrost(173)
				.pozycja(pomocnik)

				.build();

		Zawodnik JeanPierreNsame = Zawodnik.builder()
				.login("j.nsame")
				.haslo(passwordEncoder.encode("password"))
				.email("jeanpierre.nsame@legia.pl")
				.imie("Jean-Pierre")
				.nazwisko("Nsame")
				.pesel(19930501)
				.role(Role.ZAWODNIK)
				.data_Urodzenia(LocalDate.of(1993, 5, 1))
				.kraj_pochodzenia(Set.of(CM))
				.waga(88)
				.wzrost(188)
				.pozycja(napastnikSN)
				.build();

		Zawodnik TomasPekhart = Zawodnik.builder()
				.login("t.pekhart")
				.haslo(passwordEncoder.encode("password"))
				.email("tomas.pekhart@legia.pl")
				.imie("Tomas")
				.nazwisko("Pekhart")
				.pesel(19890526)
				.role(Role.ZAWODNIK)
				.data_Urodzenia(LocalDate.of(1989, 5, 26))
				.kraj_pochodzenia(Set.of(CZ))
				.waga(82)
				.wzrost(194)
				.pozycja(napastnikSN)
				.build();


		uzytkownikRepository.saveAll(List.of(
				GabrielKobylak,
				MarcelMendesDudzinski,
				RubenVinagre,
				JurgenCelhaka,
				JuergenElitim,
				JeanPierreNsame,
				TomasPekhart
		));

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
