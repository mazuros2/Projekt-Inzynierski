package dev.projekt_inzynierski;

import dev.projekt_inzynierski.configurationJWT.Role;
import dev.projekt_inzynierski.models.*;
import dev.projekt_inzynierski.models.users.*;
import dev.projekt_inzynierski.repository.Klub.KlubRepository;
import dev.projekt_inzynierski.repository.Klub.LigaRepository;
import dev.projekt_inzynierski.repository.Klub.TrofeumRepository;
import dev.projekt_inzynierski.repository.Kraj_pochodzeniaRepository;
import dev.projekt_inzynierski.repository.Obecny_klubRepository;
import dev.projekt_inzynierski.repository.PozycjaRepository;
import dev.projekt_inzynierski.repository.User.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
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
	private SkautRepository skautRepository;
	@Autowired
	private ZawodnikRepository zawodnikRepository;

	@Autowired
	private TrenerRepository trenerRepository;

	@Autowired
	private Menadzer_klubuRepository menadzer_klubuRepository;

	@Autowired
	private Kraj_pochodzeniaRepository kraj_pochodzeniaRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private PozycjaRepository pozycjaRepository;


	@Autowired
	private Obecny_klubRepository obecny_klubRepository;


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

		ligaRepository.saveAll(List.of(
				Ekstraklasa,ILiga,IILiga
		));

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

		Pozycja pomocnikSPD= Pozycja.builder()
				.nazwa_pozycji("Defensywny pomocnik")
				.obszar_pozycji("Pomocnik")
				.build();

		Pozycja pomocnikLP= Pozycja.builder()
				.nazwa_pozycji("Lewy pomocnik")
				.obszar_pozycji("Pomocnik")
				.build();

		Pozycja pomocnik= Pozycja.builder()
				.nazwa_pozycji("Pomocnik")
				.obszar_pozycji("Pomocnik")
				.build();

		Pozycja pomocnikPP= Pozycja.builder()
				.nazwa_pozycji("Prawy pomocnik")
				.obszar_pozycji("Pomocnik")
				.build();

		Pozycja pomocnikSPO= Pozycja.builder()
				.nazwa_pozycji("Ofensywny pomocnik")
				.obszar_pozycji("Pomocnik")
				.build();

		Pozycja napastnikLN= Pozycja.builder()
				.nazwa_pozycji("Lewy napastnik")
				.obszar_pozycji("Napastnik")
				.build();

		Pozycja napastnikSN = Pozycja.builder()
				.nazwa_pozycji("Środkowy napastnik")
				.obszar_pozycji("Napastnik")
				.build();

		Pozycja napastnikPN= Pozycja.builder()
				.nazwa_pozycji("Prawy napastnik")
				.obszar_pozycji("Napastnik")
				.build();

		pozycjaRepository.saveAll(List.of(
				bramkarzBR,
				obroncaLO, obroncaSO, obroncaPO,
				pomocnikSPD,pomocnikLP,pomocnik,pomocnikPP,pomocnikSPO,
				napastnikLN,napastnikSN,napastnikPN
		));


		Kraj_pochodzenia ES = Kraj_pochodzenia.builder()
				.region("Europa")
				.nazwa("Hiszpania")
				.build();

		Kraj_pochodzenia ANG = Kraj_pochodzenia.builder()
				.region("Europa")
				.nazwa("Anglia")
				.build();

		Kraj_pochodzenia CZ = Kraj_pochodzenia.builder()
				.region("Europa")
				.nazwa("Czechy")
				.build();

		Kraj_pochodzenia DK = Kraj_pochodzenia.builder()
				.region("Europa")
				.nazwa("Dania")
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

		Kraj_pochodzenia SK = Kraj_pochodzenia.builder()
				.region("Europa")
				.nazwa("Słowacja")
				.build();

		Kraj_pochodzenia IT = Kraj_pochodzenia.builder()
				.region("Europa")
				.nazwa("Włochy")
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

		Kraj_pochodzenia DE = Kraj_pochodzenia.builder()
				.region("Europa")
				.nazwa("Niemcy")
				.build();


		Kraj_pochodzenia PL = Kraj_pochodzenia.builder()
				.region("Europa")
				.nazwa("Polska")
				.build();

		Kraj_pochodzenia UA = Kraj_pochodzenia.builder()
				.region("Europa")
				.nazwa("Ukraina")
				.build();

		Kraj_pochodzenia CHR = Kraj_pochodzenia.builder()
				.region("Europa")
				.nazwa("Chorwacja")
				.build();

		Kraj_pochodzenia SR = Kraj_pochodzenia.builder()
				.region("Europa")
				.nazwa("Serbia")
				.build();

		Kraj_pochodzenia SE = Kraj_pochodzenia.builder()
				.region("Europa")
				.nazwa("Szwecja")
				.build();

		Kraj_pochodzenia SY = Kraj_pochodzenia.builder()
				.region("Azja")
				.nazwa("Syria")
				.build();

		Kraj_pochodzenia GN = Kraj_pochodzenia.builder()
				.region("Afryka")
				.nazwa("Gwinea")
				.build();

		Kraj_pochodzenia BR = Kraj_pochodzenia.builder()
				.region("Ameryka Południowa")
				.nazwa("Brazylia")
				.build();

		kraj_pochodzeniaRepository.saveAll(List.of(
				ES, CZ, CM, JP, AL, PT, CH, AR, FR, CO, PL, SR,BR, CHR, SY, SE, SK,IT, GN, ANG, UA, DE, DK
		));

		Uzytkownik adminTest = Uzytkownik.builder()
				.login("admin")
				.email("admin@gmail.com")
				.imie("Admin")
				.kraj_pochodzenia(Set.of(PL))
				.nazwisko("Adminowski")
				.data_Urodzenia(LocalDate.now())
				.pesel(12)
				.haslo(passwordEncoder.encode("admin"))
				.role(Role.ADMIN)
				.profiloweURL("https://cdn-icons-png.flaticon.com/512/149/149071.png")
				.build();


		Uzytkownik skautTest = Uzytkownik.builder()
				.login("skaut")
				.email("skaut@gmail.com")
				.imie("Skaut")
				.nazwisko("Skautowski")
				.kraj_pochodzenia(Set.of(PL,BR))
				.data_Urodzenia(LocalDate.now())
				.pesel(123)
				.haslo(passwordEncoder.encode("skaut"))
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
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/commons/a/a4/Legia_Warsaw_logo.png")
				.build();

		Klub LechPoznan = Klub.builder()
				.nazwa_klubu("Lech Poznan")
				.rok_zalozenia(LocalDate.of(1922, 1, 1))
				.liga(Ekstraklasa)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://www.wikipasy.pl/images/1/10/Lech_Pozna%C5%84_herb.png")
				.build();

		Klub GKSKatowice = Klub.builder()
				.nazwa_klubu("GKS Katowice")
				.rok_zalozenia(LocalDate.of(1964, 1, 1))
				.liga(Ekstraklasa)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/commons/d/dc/GKS_KATOWICE_LOGO.png")
				.build();

		Klub GornikZabrze = Klub.builder()
				.nazwa_klubu("Górnik Zabrze")
				.rok_zalozenia(LocalDate.of(1948, 1, 1))
				.liga(Ekstraklasa)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Gornik_Zabrze.png/347px-Gornik_Zabrze.png")
				.build();

		Klub RakowCzestochowa = Klub.builder()
				.nazwa_klubu("Rakow Czestochowa")
				.rok_zalozenia(LocalDate.of(1921, 1, 1))
				.liga(Ekstraklasa)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/RKS_Rak%C3%B3w_Cz%C4%99stochowa.svg/1200px-RKS_Rak%C3%B3w_Cz%C4%99stochowa.svg.png")
				.build();

		Klub ZaglebieLubin = Klub.builder()
				.nazwa_klubu("KGHM Zaglebie Lubin")
				.rok_zalozenia(LocalDate.of(1946, 1, 1))
				.liga(Ekstraklasa)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/en/thumb/2/20/Zag%C5%82%C4%99bie_Lubin_crest.svg/1200px-Zag%C5%82%C4%99bie_Lubin_crest.svg.png")
				.build();

		Klub SlaskWroclaw = Klub.builder()
				.nazwa_klubu("Slask Wroclaw")
				.rok_zalozenia(LocalDate.of(1947, 1, 1))
				.liga(Ekstraklasa)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/commons/thumb/7/70/Herb_%C5%9Al%C4%85ska_Wroc%C5%82aw.svg/1200px-Herb_%C5%9Al%C4%85ska_Wroc%C5%82aw.svg.png")
				.build();

		Klub RadomiakRadom = Klub.builder()
				.nazwa_klubu("Radomiak Radom")
				.rok_zalozenia(LocalDate.of(1910, 1, 1))
				.liga(Ekstraklasa)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/commons/2/2b/Herb_radomiaka_300dpi.png")
				.build();

		Klub JagielloniaBialystok = Klub.builder()
				.nazwa_klubu("Jagiellonia Białystok")
				.rok_zalozenia(LocalDate.of(1920, 1, 1))
				.liga(Ekstraklasa)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/commons/f/f9/Logo_Jagielloni_Bia%C5%82ystok.png")
				.build();

		Klub PiastGliwice = Klub.builder()
				.nazwa_klubu("Piast Gliwice")
				.rok_zalozenia(LocalDate.of(1945, 1, 1))
				.liga(Ekstraklasa)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://iconape.com/wp-content/png_logo_vector/gks-gliwice-logo.png")
				.build();

		Klub WidzewLodz = Klub.builder()
				.nazwa_klubu("Widzew Łódź")
				.rok_zalozenia(LocalDate.of(1910, 1, 1))
				.liga(Ekstraklasa)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://seeklogo.com/images/W/Widzew_Lodz-logo-70992DF075-seeklogo.com.png")
				.build();

		klubRepository.saveAll(List.of(
				LegiaWarszawa, LechPoznan, GKSKatowice, GornikZabrze, RakowCzestochowa, ZaglebieLubin,
				SlaskWroclaw, RadomiakRadom, WidzewLodz, JagielloniaBialystok, PiastGliwice
		));

		//Kluby I Ligii
		Klub RuchChorzow = Klub.builder()
				.nazwa_klubu("Ruch Chorzow")
				.rok_zalozenia(LocalDate.of(1920, 1, 1))
				.liga(ILiga)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/commons/thumb/5/55/Ruch_Chorz%C3%B3w_-_Herb_%282021%29.svg/1200px-Ruch_Chorz%C3%B3w_-_Herb_%282021%29.svg.png")
				.build();

		Klub ZniczPruszkow = Klub.builder()
				.nazwa_klubu("Znicz Pruszkow")
				.rok_zalozenia(LocalDate.of(1922, 1, 1))
				.liga(ILiga)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/en/8/84/Znicz_Pruszkow.png")
				.build();

		Klub PogonSiedlce = Klub.builder()
				.nazwa_klubu("Pogon Siedlce")
				.rok_zalozenia(LocalDate.of(1944, 1, 1))
				.liga(ILiga)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/en/thumb/a/aa/Herb_Pogo%C5%84.cdr.svg/1200px-Herb_Pogo%C5%84.cdr.svg.png")
				.build();


		Klub GKSTychy = Klub.builder()
				.nazwa_klubu("GKS Tychy")
				.rok_zalozenia(LocalDate.of(1971, 1, 1))
				.liga(ILiga)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0a/Gkstychy.png/1200px-Gkstychy.png")
				.build();

		Klub BrukBetTermalica = Klub.builder()
				.nazwa_klubu("Bruk-Bet Termalica")
				.rok_zalozenia(LocalDate.of(1922, 1, 1))
				.liga(ILiga)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://termalica.brukbet.com/wp-content/uploads/2021/11/BBTN-Herb-slider.png")
				.build();

		Klub ArkaGdynia = Klub.builder()
				.nazwa_klubu("Arka Gdynia")
				.rok_zalozenia(LocalDate.of(1929, 1, 1))
				.liga(ILiga)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://arka.gdynia.pl/files/herb/arka_gdynia_mzks_kolor.png")
				.build();

		Klub MiedzLegnica = Klub.builder()
				.nazwa_klubu("Miedz Legnica")
				.rok_zalozenia(LocalDate.of(1971, 1, 1))
				.liga(ILiga)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/commons/9/9a/Miedzherb.png")
				.build();

		Klub GornikLeczna = Klub.builder()
				.nazwa_klubu("Gornik Leczna")
				.rok_zalozenia(LocalDate.of(1979, 1, 1))
				.liga(ILiga)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/en/thumb/8/82/Herb_gornik_leczna.svg/1200px-Herb_gornik_leczna.svg.png")
				.build();

		Klub WislaKrakow = Klub.builder()
				.nazwa_klubu("Wisła Kraków")
				.rok_zalozenia(LocalDate.of(1906, 1, 1))
				.liga(ILiga)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://www.wikipasy.pl/images/f/f9/Wis%C5%82a_Krak%C3%B3w_stary_herb_7.png")
				.build();

		Klub KoronaKielce = Klub.builder()
				.nazwa_klubu("Korona Kielce")
				.rok_zalozenia(LocalDate.of(1973, 1, 1))
				.liga(ILiga)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://www.wikipasy.pl/images/f/f2/Korona_Kielce_herb.png")
				.build();

		Klub OdraOpole = Klub.builder()
				.nazwa_klubu("Odra Opole")
				.rok_zalozenia(LocalDate.of(1945, 1, 1))
				.liga(ILiga)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://www.wikipasy.pl/images/2/20/Odra_Opole_herb.png")
				.build();

		klubRepository.saveAll(List.of(
				RuchChorzow, ZniczPruszkow, PogonSiedlce, GKSTychy, BrukBetTermalica,
				ArkaGdynia, MiedzLegnica, GornikLeczna, WislaKrakow, KoronaKielce, OdraOpole
		));

		//Kluby z II Ligii
		Klub RekordBB = Klub.builder()
				.nazwa_klubu("Rekord Bielsko-Biala")
				.rok_zalozenia(LocalDate.of(1994, 1, 1))
				.liga(IILiga)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://bts.rekord.com.pl/static/images/logo.png")
				.build();

		Klub PodbeskidzieBB = Klub.builder()
				.nazwa_klubu("TS Podbeskidzie Bielsko-Biala")
				.rok_zalozenia(LocalDate.of(1995, 1, 1))
				.liga(IILiga)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://tspodbeskidzie.pl/static/upload/store/klub/herb/TSP_Herb.png")
				.build();

		Klub KKSKalisz = Klub.builder()
				.nazwa_klubu("KKS 1925 Kalisz")
				.rok_zalozenia(LocalDate.of(1925, 1, 1))
				.liga(IILiga)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/commons/6/6a/KKS_herb.png")
				.build();

		Klub ZaglebieSosnowiec = Klub.builder()
				.nazwa_klubu("Zaglebie Sosnowiec")
				.rok_zalozenia(LocalDate.of(1918, 1, 1))
				.liga(IILiga)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://www.wikipasy.pl/images/5/54/Zag%C5%82%C4%99bie_Sosnowiec_herb.png")
				.build();

		Klub KSWieczystaKrakow = Klub.builder()
				.nazwa_klubu("KS Wieczysta Krakow")
				.rok_zalozenia(LocalDate.of(1942, 1, 1))
				.liga(IILiga)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://bazaherbow.pl/wp-content/uploads/2022/05/WieczystaKrakow.png")
				.build();

		Klub ResoviaRzeszow = Klub.builder()
				.nazwa_klubu("Resovia Rzeszow")
				.rok_zalozenia(LocalDate.of(1910, 1, 1))
				.liga(IILiga)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://www.wikipasy.pl/images/4/42/Resovia_Rzesz%C3%B3w_herb.png")
				.build();

		Klub PoloniaBytom = Klub.builder()
				.nazwa_klubu("Polonia Bytom")
				.rok_zalozenia(LocalDate.of(1945, 1, 1))
				.liga(IILiga)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://upload.wikimedia.org/wikipedia/commons/6/69/Polonia-Bytom01.png")
				.build();

		Klub OlimpiaGrudziac = Klub.builder()
				.nazwa_klubu("Olimpia Grudziac")
				.rok_zalozenia(LocalDate.of(1923, 6, 30))
				.liga(IILiga)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://seeklogo.com/images/G/GKS_Olimpia_Grudziadz-logo-00C3DCF6AB-seeklogo.com.png")
				.build();

		Klub MotorLublin = Klub.builder()
				.nazwa_klubu("Motor Lublin")
				.rok_zalozenia(LocalDate.of(1950, 1, 1))
				.liga(IILiga)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://motorlublin.com/wp-content/uploads/2016/03/herb.png")
				.build();

		Klub GarbarniaKrakow = Klub.builder()
				.nazwa_klubu("Garbarnia Kraków")
				.rok_zalozenia(LocalDate.of(1921, 1, 1))
				.liga(IILiga)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://bazaherbow.pl/wp-content/uploads/2021/10/GarbarniaKrakow.png")
				.build();

		Klub StalRzeszow = Klub.builder()
				.nazwa_klubu("Stal Rzeszów")
				.rok_zalozenia(LocalDate.of(1944, 1, 1))
				.liga(IILiga)
				.trofea(new ArrayList<>())
				.setObecnyKlub(new HashSet<>())
				.logo_url("https://stalrzeszow.pl/wp-content/uploads/2019/07/STAL-RZESZOW.png")
				.build();

		klubRepository.saveAll(List.of(
				RekordBB, PodbeskidzieBB, KKSKalisz, ZaglebieSosnowiec, KSWieczystaKrakow, ResoviaRzeszow,
				PoloniaBytom, OlimpiaGrudziac, MotorLublin, GarbarniaKrakow, StalRzeszow
		));


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
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.build();

		Zawodnik PW = Zawodnik.builder()
				.login("PW13")
				.email("PawelWszolek13@gmail.com")
				.imie("Paweł")
				.nazwisko("Wszołek")
				.data_Urodzenia(LocalDate.of(1992, 5, 30))
				.pesel(123456)
				.haslo(passwordEncoder.encode("prawy1"))
				.role(Role.ZAWODNIK)
				.waga(78)
				.wzrost(186)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(obroncaPO)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.build();

		Zawodnik RP = Zawodnik.builder()
				.login("RP12")
				.email("RadovanPankov12@gmail.com")
				.imie("Radovan")
				.nazwisko("Pankov")
				.data_Urodzenia(LocalDate.of(1995, 8, 5))
				.pesel(1234567)
				.haslo(passwordEncoder.encode("obronca1"))
				.role(Role.ZAWODNIK)
				.waga(80)
				.wzrost(185)
				.kraj_pochodzenia(Set.of(SR))
				.pozycja(obroncaPO)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.build();

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
				.kraj_pochodzenia(Set.of(PT))
				.licencja_trenera("UEFA PRO")
				.profiloweURL("https://img.a.transfermarkt.technology/portrait/big/40996-1697407122.JPG?lm=1")
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


		uzytkownikRepository.save(GF);
		uzytkownikRepository.save(skaut);
		LegiaWarszawa.setTrener(GF);
		LegiaWarszawa.setSkaut(skaut);
		klubRepository.save(LegiaWarszawa);


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
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
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
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
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
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.build();

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
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://legia.net/uploads/historia/gabriel-kobylak-historia-pilkarz01-18-08-661.jpg")
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
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
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
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
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
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
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
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
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
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
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
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.build();


		zawodnikRepository.saveAll(List.of(
				GabrielKobylak, MarcelMendesDudzinski, RubenVinagre, JurgenCelhaka, PW, KT, RP,
				JuergenElitim, JeanPierreNsame, TomasPekhart, arturJedrzejczyk, bartoszKapustka, marcGual
		));

		LegiaWarszawa.dodajZawodnika(TomasPekhart,LocalDate.now());
		LegiaWarszawa.dodajZawodnika(GabrielKobylak,LocalDate.now());
		LegiaWarszawa.dodajZawodnika(MarcelMendesDudzinski,LocalDate.now());
		LegiaWarszawa.dodajZawodnika(RubenVinagre,LocalDate.now());
		LegiaWarszawa.dodajZawodnika(JurgenCelhaka,LocalDate.now());
		LegiaWarszawa.dodajZawodnika(JuergenElitim,LocalDate.now());
		LegiaWarszawa.dodajZawodnika(JeanPierreNsame,LocalDate.now());
		LegiaWarszawa.dodajZawodnika(arturJedrzejczyk,LocalDate.now());
		LegiaWarszawa.dodajZawodnika(bartoszKapustka,LocalDate.now());
		LegiaWarszawa.dodajZawodnika(marcGual,LocalDate.now());
		LegiaWarszawa.dodajZawodnika(PW,LocalDate.now());
		LegiaWarszawa.dodajZawodnika(KT,LocalDate.now());
		LegiaWarszawa.dodajZawodnika(RP,LocalDate.now());

		klubRepository.save(LegiaWarszawa);

		zawodnikRepository.saveAll(List.of(
				GabrielKobylak, MarcelMendesDudzinski, RubenVinagre, JurgenCelhaka, JuergenElitim,
				JeanPierreNsame, TomasPekhart, arturJedrzejczyk, bartoszKapustka, marcGual, PW, KT, RP
		));

		for (Obecny_klub ob : LegiaWarszawa.getSetObecnyKlub()) {
			obecny_klubRepository.save(ob);
		}


		//Górnik Zabrze
		Zawodnik MateuszJelen = Zawodnik.builder()
				.login("MateuszJelen")
				.email("MateuszJelen@gmail.com")
				.imie("Mateusz")
				.nazwisko("Jelen")
				.data_Urodzenia(LocalDate.of(2007, 2, 2))
				.pesel(9070973)
				.haslo(passwordEncoder.encode("bramkarz1"))
				.role(Role.ZAWODNIK)
				.waga(81)
				.wzrost(188)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(bramkarzBR)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://img.a.transfermarkt.technology/portrait/big/1073749-1725472890.png?lm=1")
				.build();

		Zawodnik DominikSzala = Zawodnik.builder()
				.login("DominikSzala")
				.email("DominikSzala@gmail.com")
				.imie("Dominik")
				.nazwisko("Szala")
				.data_Urodzenia(LocalDate.of(2006, 4, 24))
				.pesel(5432879)
				.haslo(passwordEncoder.encode("obronca1"))
				.role(Role.ZAWODNIK)
				.waga(80)
				.wzrost(188)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(obroncaSO)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://encrypted-tbn2.gstatic.com/licensed-image?q=tbn:ANd9GcQgqmZ86zhDTqnkIVy2Sn0_yoiGsXTnoji9EAt2nI1_MVfRpTC2NVnx37M8_-t17kC78hNwO3Hn2mQ2dJA")
				.build();

		Zawodnik KryspinSzczesniak = Zawodnik.builder()
				.login("KryspinSzczesniak")
				.email("KryspinSzczesniak@gmail.com")
				.imie("Kryspin")
				.nazwisko("Szczesniak")
				.data_Urodzenia(LocalDate.of(2001, 1, 8))
				.pesel(4132165)
				.haslo(passwordEncoder.encode("obronca2"))
				.role(Role.ZAWODNIK)
				.waga(80)
				.wzrost(186)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(obroncaSO)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://s7.tvp.pl/images2/7/1/9/uid_7198b4f42e6f449eac191fd50d972e04_width_200_play_0_pos_0_gs_0_height_240_kryspin-szczesniak-fot-gornik-leczna.jpg")
				.build();

		Zawodnik Josema = Zawodnik.builder()
				.login("Josema")
				.email("Josema@gmail.com")
				.imie("Jose")
				.nazwisko("Manuel Sanchez")
				.data_Urodzenia(LocalDate.of(1996, 6, 6))
				.pesel(5423631)
				.haslo(passwordEncoder.encode("obronca3"))
				.role(Role.ZAWODNIK)
				.waga(76)
				.wzrost(182)
				.kraj_pochodzenia(Set.of(ES))
				.pozycja(obroncaSO)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://img.a.transfermarkt.technology/portrait/big/255892-1721899552.png?lm=1")
				.build();

		Zawodnik NikodemZielonka = Zawodnik.builder()
				.login("NikodemZielonka")
				.email("NikodemZielonka@gmail.com")
				.imie("Nikodem")
				.nazwisko("Zielonka")
				.data_Urodzenia(LocalDate.of(2004, 8, 17))
				.pesel(904817123)
				.haslo(passwordEncoder.encode("pomocnik1"))
				.role(Role.ZAWODNIK)
				.waga(78)
				.wzrost(184)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(pomocnikSPO)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://s6.tvp.pl/images2/f/3/2/uid_f3264e36275c830888da28739a68137a1644774665056_width_200_play_0_pos_0_gs_0_height_240_nikodem-zielonka-fot-400mm.jpg")
				.build();

		Zawodnik KamilLukoszek = Zawodnik.builder()
				.login("KamilLukoszek")
				.email("KamilLukoszek@gmail.com")
				.imie("Kamil")
				.nazwisko("Lukoszek")
				.data_Urodzenia(LocalDate.of(2002, 4, 4))
				.pesel(802404567)
				.haslo(passwordEncoder.encode("napastnik1"))
				.role(Role.ZAWODNIK)
				.waga(70)
				.wzrost(175)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(napastnikLN)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://gornikzabrzecdn.stellis.one/imgsize-m/documents/4541152/0a433aa7-8516-7d92-c170-d74ec760a3bb")
				.build();

		Zawodnik YosukeFurukawa = Zawodnik.builder()
				.login("YosukeFurukawa")
				.email("YosukeFurukawa@gmail.com")
				.imie("Yosuke")
				.nazwisko("Furukawa")
				.data_Urodzenia(LocalDate.of(2003, 7, 16))
				.pesel(903716678)
				.haslo(passwordEncoder.encode("napastnik2"))
				.role(Role.ZAWODNIK)
				.profiloweURL("https://gornikzabrzecdn.stellis.one/imgsize-m/documents/4541152/c4368902-240c-2662-4535-930a96c3919a")
				.waga(66)
				.wzrost(174)
				.kraj_pochodzenia(Set.of(JP))
				.pozycja(napastnikLN)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.build();

		zawodnikRepository.saveAll(List.of(
				MateuszJelen,DominikSzala,KryspinSzczesniak,Josema,
				NikodemZielonka,KamilLukoszek,YosukeFurukawa
		));

		GornikZabrze.dodajZawodnika(MateuszJelen,LocalDate.now());
		GornikZabrze.dodajZawodnika(DominikSzala,LocalDate.now());
		GornikZabrze.dodajZawodnika(KryspinSzczesniak,LocalDate.now());
		GornikZabrze.dodajZawodnika(Josema,LocalDate.now());
		GornikZabrze.dodajZawodnika(NikodemZielonka,LocalDate.now());
		GornikZabrze.dodajZawodnika(KamilLukoszek,LocalDate.now());
		GornikZabrze.dodajZawodnika(YosukeFurukawa,LocalDate.now());
		klubRepository.save(GornikZabrze);

		zawodnikRepository.saveAll(List.of(
				MateuszJelen,DominikSzala,KryspinSzczesniak,Josema,
				NikodemZielonka,KamilLukoszek,YosukeFurukawa
		));

		for (Obecny_klub ob : GornikZabrze.getSetObecnyKlub()) {
			obecny_klubRepository.save(ob);
		}

		//Raków
		Zawodnik ArielMosor = Zawodnik.builder()
				.login("ArielMosor")
				.email("ArielMosor@gmail.com")
				.imie("Ariel")
				.nazwisko("Mosor")
				.data_Urodzenia(LocalDate.of(2003, 2, 19))
				.pesel(903219123)
				.haslo(passwordEncoder.encode("obronca1"))
				.role(Role.ZAWODNIK)
				.waga(75)
				.wzrost(184)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(obroncaSO)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://tmssl.akamaized.net/images/foto/galerie/ariel-mosor-piast-gliwice-1655217977-86835.jpg")
				.build();

		Zawodnik KacperTrelowski = Zawodnik.builder()
				.login("KacperTrelowski")
				.email("KacperTrelowski@gmail.com")
				.imie("Kacper")
				.nazwisko("Trelowski")
				.data_Urodzenia(LocalDate.of(2003, 8, 19))
				.pesel(903819456)
				.haslo(passwordEncoder.encode("bramkarz1"))
				.role(Role.ZAWODNIK)
				.waga(85)
				.wzrost(193)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(bramkarzBR)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSQ46g6dqIzWBipZIKx-Q1RI0TghQFK0FHl-5Of5i_OaYgfvqvJPRPdG5VqlyiCrBgU6aMLAbwqhIHwgaDXAWFIuA")
				.build();

		Zawodnik AntoniBurkiewicz = Zawodnik.builder()
				.login("AntoniBurkiewicz")
				.email("AntoniBurkiewicz@gmail.com")
				.imie("Antoni")
				.nazwisko("Burkiewicz")
				.data_Urodzenia(LocalDate.of(2008, 4, 21))
				.pesel(908421789)
				.haslo(passwordEncoder.encode("pomocnik1"))
				.role(Role.ZAWODNIK)
				.waga(63)
				.wzrost(177)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(pomocnikSPD)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://img.a.transfermarkt.technology/portrait/big/1190453-1727729543.png?lm=1")
				.build();

		Zawodnik FranTudor = Zawodnik.builder()
				.login("FranTudor")
				.email("FranTudor@gmail.com")
				.imie("Fran")
				.nazwisko("Tudor")
				.data_Urodzenia(LocalDate.of(1995, 9, 27))
				.pesel(995927234)
				.haslo(passwordEncoder.encode("pomocnik2"))
				.role(Role.ZAWODNIK)
				.waga(72)
				.wzrost(173)
				.kraj_pochodzenia(Set.of(CHR))
				.pozycja(pomocnikPP)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://encrypted-tbn0.gstatic.com/licensed-image?q=tbn:ANd9GcTi38VeHuRIH-4IhpTnuSyVB_Tuh-11q1a_50rM43-Qekq1xK6s8D1mdQqoU3XR0ko_Iswtu0200VDZPMc")
				.build();

		Zawodnik PatrykMakuch = Zawodnik.builder()
				.login("PatrykMakuch")
				.email("PatrykMakuch@gmail.com")
				.imie("Patryk")
				.nazwisko("Makuch")
				.data_Urodzenia(LocalDate.of(1999, 4, 11))
				.pesel(999411678)
				.haslo(passwordEncoder.encode("napastnik1"))
				.role(Role.ZAWODNIK)
				.waga(80)
				.wzrost(187)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(napastnikSN)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://pliki.rakow.com/articles/800x2000/20240711-j-z-9625-_1720708279626.jpg")
				.build();

		Zawodnik TomaszWalczak = Zawodnik.builder()
				.login("TomaszWalczak")
				.email("TomaszWalczak@gmail.com")
				.imie("Tomasz")
				.nazwisko("Walczak")
				.data_Urodzenia(LocalDate.of(2005, 8, 17))
				.pesel(905817345)
				.haslo(passwordEncoder.encode("napastnik2"))
				.role(Role.ZAWODNIK)
				.waga(83)
				.wzrost(192)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(napastnikSN)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://s3.tvp.pl/images2/3/3/c/uid_33cf81fbf69b40dd94d0e8b77ecde4fd_width_200_play_0_pos_0_gs_0_height_240_tomasz-walczak-fot-rakow-czestochowa.jpg")
				.build();

		zawodnikRepository.saveAll(List.of(
				ArielMosor,TomaszWalczak,PatrykMakuch,FranTudor,
				AntoniBurkiewicz,KacperTrelowski
		));

		RakowCzestochowa.dodajZawodnika(ArielMosor,LocalDate.now());
		RakowCzestochowa.dodajZawodnika(TomaszWalczak,LocalDate.now());
		RakowCzestochowa.dodajZawodnika(PatrykMakuch,LocalDate.now());
		RakowCzestochowa.dodajZawodnika(FranTudor,LocalDate.now());
		RakowCzestochowa.dodajZawodnika(AntoniBurkiewicz,LocalDate.now());
		RakowCzestochowa.dodajZawodnika(KacperTrelowski,LocalDate.now());

		klubRepository.save(RakowCzestochowa);

		zawodnikRepository.saveAll(List.of(
				ArielMosor,TomaszWalczak,PatrykMakuch,FranTudor,
				AntoniBurkiewicz,KacperTrelowski
		));

		for (Obecny_klub ob : RakowCzestochowa.getSetObecnyKlub()) {
			obecny_klubRepository.save(ob);
		}


		//GKS Katowice
		Zawodnik DawidKudla = Zawodnik.builder()
				.login("DawidKudla")
				.email("DawidKudla@gmail.com")
				.imie("Dawid")
				.nazwisko("Kudla")
				.data_Urodzenia(LocalDate.of(1992, 3, 21))
				.pesel(992321456)
				.haslo(passwordEncoder.encode("bramkarz1"))
				.role(Role.ZAWODNIK)
				.waga(83)
				.wzrost(190)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(bramkarzBR)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcR6pIQYx1OspSHC1fyUvwsVUaoCzP23Fm2CZT4rP0hvltHW-CKb4Fh2nzZaeoZUq0yH-zXkSGGjltzeR31S7nInJw")
				.build();

		Zawodnik AleksanderKomor = Zawodnik.builder()
				.login("AleksanderKomor")
				.email("AleksanderKomor@gmail.com")
				.imie("Aleksander")
				.nazwisko("Komor")
				.data_Urodzenia(LocalDate.of(1994, 6, 24))
				.pesel(994624789)
				.haslo(passwordEncoder.encode("obronca1"))
				.role(Role.ZAWODNIK)
				.waga(83)
				.wzrost(190)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(obroncaSO)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://www.gkskatowice.eu/media/players/1170/524-66ad2543e8bd6.png")
				.build();

		Zawodnik OskarRepka = Zawodnik.builder()
				.login("OskarRepka")
				.email("OskarRepka@gmail.com")
				.imie("Oskar")
				.nazwisko("Repka")
				.data_Urodzenia(LocalDate.of(1999, 1, 3))
				.pesel(999103345)
				.haslo(passwordEncoder.encode("pomocnik1"))
				.role(Role.ZAWODNIK)
				.waga(83)
				.wzrost(190)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(pomocnikSPD)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://www.gkskatowice.eu/media/players/1124/524-66a1455d1acb4.png")
				.build();

		Zawodnik AdrianDanek = Zawodnik.builder()
				.login("AdrianDanek")
				.email("AdrianDanek@gmail.com")
				.imie("Adrian")
				.nazwisko("Danek")
				.data_Urodzenia(LocalDate.of(1994, 8, 1))
				.pesel(994801567)
				.haslo(passwordEncoder.encode("pomocnik2"))
				.role(Role.ZAWODNIK)
				.waga(75)
				.wzrost(180)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(pomocnikPP)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://www.gkskatowice.eu/media/players/1173/524-66a14fb883164.png")
				.build();

		Zawodnik SebastianBergier = Zawodnik.builder()
				.login("SebastianBergier")
				.email("SebastianBergier@gmail.com")
				.imie("Sebastian")
				.nazwisko("Bergier")
				.data_Urodzenia(LocalDate.of(1999, 12, 20))
				.pesel(999122045)
				.haslo(passwordEncoder.encode("napastnik1"))
				.role(Role.ZAWODNIK)
				.waga(80)
				.wzrost(185)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(napastnikSN)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://www.gkskatowice.eu/media/news/17637/large_pic_63e75cf22e6b7-64ee540c6745d.png")
				.build();

		zawodnikRepository.saveAll(List.of(
				SebastianBergier,AdrianDanek,OskarRepka,
				AleksanderKomor,DawidKudla
		));

		GKSKatowice.dodajZawodnika(SebastianBergier,LocalDate.now());
		GKSKatowice.dodajZawodnika(AdrianDanek,LocalDate.now());
		GKSKatowice.dodajZawodnika(OskarRepka,LocalDate.now());
		GKSKatowice.dodajZawodnika(DawidKudla,LocalDate.now());
		GKSKatowice.dodajZawodnika(AleksanderKomor,LocalDate.now());

		klubRepository.save(GKSKatowice);

		zawodnikRepository.saveAll(List.of(
				SebastianBergier,AdrianDanek,OskarRepka,
				AleksanderKomor,DawidKudla
		));

		for (Obecny_klub ob : GKSKatowice.getSetObecnyKlub()) {
			obecny_klubRepository.save(ob);
		}

		//Lech Poznań
		Zawodnik BartoszSalamon = Zawodnik.builder()
				.login("BartoszSalamon")
				.email("BartoszSalamon@gmail.com")
				.imie("Bartosz")
				.nazwisko("Salamon")
				.data_Urodzenia(LocalDate.of(1991, 5, 1))
				.pesel(991501234)
				.haslo(passwordEncoder.encode("obronca1"))
				.role(Role.ZAWODNIK)
				.waga(89)
				.wzrost(194)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(obroncaSO)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.build();

		Zawodnik BartoszMrozek = Zawodnik.builder()
				.login("BartoszMrozek")
				.email("BartoszMrozek@gmail.com")
				.imie("Bartosz")
				.nazwisko("Mrozek")
				.data_Urodzenia(LocalDate.of(2000, 2, 23))
				.pesel(200223567)
				.haslo(passwordEncoder.encode("bramkarz1"))
				.role(Role.ZAWODNIK)
				.waga(84)
				.wzrost(191)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(bramkarzBR)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.build();

		Zawodnik JoelPereira = Zawodnik.builder()
				.login("JoelPereira")
				.email("JoelPereira@gmail.com")
				.imie("Joel")
				.nazwisko("Pereira")
				.data_Urodzenia(LocalDate.of(1996, 9, 28))
				.pesel(996928345)
				.haslo(passwordEncoder.encode("obronca2"))
				.role(Role.ZAWODNIK)
				.waga(74)
				.wzrost(178)
				.kraj_pochodzenia(Set.of(PT))
				.pozycja(obroncaPO)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.build();

		Zawodnik RadoslawMurawski = Zawodnik.builder()
				.login("RadoslawMurawski")
				.email("RadoslawMurawski@gmail.com")
				.imie("Radosław")
				.nazwisko("Murawski")
				.data_Urodzenia(LocalDate.of(1994, 4, 22))
				.pesel(994422567)
				.haslo(passwordEncoder.encode("pomocnik1"))
				.role(Role.ZAWODNIK)
				.waga(70)
				.wzrost(173)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(pomocnikSPD)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.build();

		Zawodnik FilipSzymczak = Zawodnik.builder()
				.login("FilipSzymczak")
				.email("FilipSzymczak@gmail.com")
				.imie("Filip")
				.nazwisko("Szymczak")
				.data_Urodzenia(LocalDate.of(2002, 5, 6))
				.pesel(200205607) // Losowy PESEL
				.haslo(passwordEncoder.encode("napastnik1"))
				.role(Role.ZAWODNIK)
				.waga(82) // Oszacowano
				.wzrost(187)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(napastnikSN)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.build();

		Zawodnik MikaelIshak = Zawodnik.builder()
				.login("MikaelIshak")
				.email("MikaelIshak@gmail.com")
				.imie("Mikael")
				.nazwisko("Ishak")
				.data_Urodzenia(LocalDate.of(1993, 3, 31))
				.pesel(993331789)
				.haslo(passwordEncoder.encode("napastnik2"))
				.role(Role.ZAWODNIK)
				.waga(80)
				.wzrost(185)
				.kraj_pochodzenia(Set.of(SE, SY))
				.pozycja(napastnikSN)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.build();

		zawodnikRepository.saveAll(List.of(
				MikaelIshak,FilipSzymczak,RadoslawMurawski,
				JoelPereira,BartoszMrozek,BartoszSalamon
		));

		LechPoznan.dodajZawodnika(MikaelIshak,LocalDate.now());
		LechPoznan.dodajZawodnika(FilipSzymczak,LocalDate.now());
		LechPoznan.dodajZawodnika(RadoslawMurawski,LocalDate.now());
		LechPoznan.dodajZawodnika(BartoszSalamon,LocalDate.now());
		LechPoznan.dodajZawodnika(BartoszMrozek,LocalDate.now());
		LechPoznan.dodajZawodnika(JoelPereira,LocalDate.now());

		klubRepository.save(LechPoznan);
		zawodnikRepository.saveAll(List.of(
				MikaelIshak,FilipSzymczak,RadoslawMurawski,
				JoelPereira,BartoszMrozek,BartoszSalamon
		));

		for (Obecny_klub ob : LechPoznan.getSetObecnyKlub()) {
			obecny_klubRepository.save(ob);
		}


		//piast gliwice
		Zawodnik FrantisekPlach = Zawodnik.builder()
				.login("FrantisekPlach")
				.email("FrantisekPlach@gmail.com")
				.imie("Frantisek")
				.nazwisko("Plach")
				.data_Urodzenia(LocalDate.of(1992, 3, 8))
				.pesel(920308345)
				.haslo(passwordEncoder.encode("bramkarz1"))
				.role(Role.ZAWODNIK)
				.waga(85)
				.wzrost(192)
				.kraj_pochodzenia(Set.of(SK))
				.pozycja(bramkarzBR)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://piast-gliwice.eu/static/thumbnail/news/med/88475.jpg")
				.build();

		Zawodnik TomasHuk = Zawodnik.builder()
				.login("TomasHuk")
				.email("TomasHuk@gmail.com")
				.imie("Tomas")
				.nazwisko("Huk")
				.data_Urodzenia(LocalDate.of(1994, 12, 22))
				.pesel(941222567)
				.haslo(passwordEncoder.encode("obronca1"))
				.role(Role.ZAWODNIK)
				.waga(82)
				.wzrost(184)
				.kraj_pochodzenia(Set.of(SK))
				.pozycja(obroncaSO)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://piast-gliwice.eu/static/thumbnail/news/med/97350.jpg")
				.build();

		Zawodnik FilipKarbowy = Zawodnik.builder()
				.login("FilipKarbowy")
				.email("FilipKarbowy@gmail.com")
				.imie("Filip")
				.nazwisko("Karbowy")
				.data_Urodzenia(LocalDate.of(1997, 9, 3))
				.pesel(970903123)
				.haslo(passwordEncoder.encode("pomocnik1"))
				.role(Role.ZAWODNIK)
				.waga(75)
				.wzrost(182)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(pomocnikSPO)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://piast-gliwice.eu/static/thumbnail/news/med/109445.jpg")
				.build();

		Zawodnik JorgeFelix = Zawodnik.builder()
				.login("JorgeFelix")
				.email("JorgeFelix@gmail.com")
				.imie("Jorge")
				.nazwisko("Félix")
				.data_Urodzenia(LocalDate.of(1991, 8, 22))
				.pesel(910822567)
				.haslo(passwordEncoder.encode("napastnik1"))
				.role(Role.ZAWODNIK)
				.waga(72)
				.wzrost(175)
				.kraj_pochodzenia(Set.of(ES))
				.pozycja(napastnikLN)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://piast-gliwice.eu/static/thumbnail/news/med/2037.jpg")
				.build();

		Zawodnik DamianKadzior = Zawodnik.builder()
				.login("DamianKadzior")
				.email("DamianKadzior@gmail.com")
				.imie("Damian")
				.nazwisko("Kądzior")
				.data_Urodzenia(LocalDate.of(1992, 6, 16))
				.pesel(920616456)
				.haslo(passwordEncoder.encode("napastnik2"))
				.role(Role.ZAWODNIK)
				.waga(78)
				.wzrost(174)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(napastnikPN)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://ocdn.eu/sport-images-transforms/1/tMQk9lBaHR0cHM6Ly9vY2RuLmV1L3B1bHNjbXMvTURBXy85M2MxOTBmMjhkMmY0ZWIxNmEwMGJmYWY1OWI2MGVlMS5qcGeTlQPNAnrMvc0Ff80DGJUCzQSwAMLDkwmmYTY5MzgxBt4AAqEwAaExAQ/damian-kadzior.jpg")
				.build();

		zawodnikRepository.saveAll(List.of(
				FrantisekPlach,DamianKadzior,JorgeFelix,
				FilipKarbowy,TomasHuk
		));

		PiastGliwice.dodajZawodnika(FrantisekPlach,LocalDate.now());
		PiastGliwice.dodajZawodnika(DamianKadzior,LocalDate.now());
		PiastGliwice.dodajZawodnika(JorgeFelix,LocalDate.now());
		PiastGliwice.dodajZawodnika(FilipKarbowy,LocalDate.now());
		PiastGliwice.dodajZawodnika(TomasHuk,LocalDate.now());
		klubRepository.save(PiastGliwice);

		zawodnikRepository.saveAll(List.of(
				FrantisekPlach,DamianKadzior,JorgeFelix,
				FilipKarbowy,TomasHuk
		));

		for (Obecny_klub ob : PiastGliwice.getSetObecnyKlub()) {
			obecny_klubRepository.save(ob);
		}

		//śląsk wrocław

		Zawodnik RafalLeszczynski = Zawodnik.builder()
				.login("RafalLeszczynski")
				.email("RafalLeszczynski@gmail.com")
				.imie("Rafał")
				.nazwisko("Leszczyński")
				.data_Urodzenia(LocalDate.of(1992, 4, 26))
				.pesel(920426789)
				.haslo(passwordEncoder.encode("bramkarz1"))
				.role(Role.ZAWODNIK)
				.waga(80)
				.wzrost(187)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(bramkarzBR)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://daf17zziboaju.cloudfront.net/wp-content/uploads/2024/04/07002348/rafal.leszczynski.jpg")
				.build();

		Zawodnik AleksanderPaluszek = Zawodnik.builder()
				.login("AleksanderPaluszek")
				.email("AleksanderPaluszek@gmail.com")
				.imie("Aleksander")
				.nazwisko("Paluszek")
				.data_Urodzenia(LocalDate.of(2001, 4, 9))
				.pesel(910409456)
				.haslo(passwordEncoder.encode("obronca1"))
				.role(Role.ZAWODNIK)
				.waga(85)
				.wzrost(194)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(obroncaSO)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://d-art.ppstatic.pl/kadry/k/r/1/46/73/64c8ea3155f20_o_medium.jpg")
				.build();

		Zawodnik TommasoGuercio = Zawodnik.builder()
				.login("TommasoGuercio")
				.email("TommasoGuercio@gmail.com")
				.imie("Tommaso")
				.nazwisko("Guercio")
				.data_Urodzenia(LocalDate.of(2005, 6, 1))
				.pesel(250601345)
				.haslo(passwordEncoder.encode("pomocnik1"))
				.role(Role.ZAWODNIK)
				.waga(78)
				.wzrost(186)
				.kraj_pochodzenia(Set.of(PL, IT))
				.pozycja(pomocnikLP)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://cdn.galleries.smcloud.net/t/galleries/gf-J2aQ-qN9c-nbqS_tommaso-guercio-nowym-pilkarzem-slaska-wroclaw-czego-spodziewac-sie-zawodniku-z-akademii-interu-994x828.jpg")
				.build();

		Zawodnik ArnauOrtiz = Zawodnik.builder()
				.login("ArnauOrtiz")
				.email("ArnauOrtiz@gmail.com")
				.imie("Arnau")
				.nazwisko("Ortiz")
				.data_Urodzenia(LocalDate.of(2001, 10, 29))
				.pesel(111029123)
				.haslo(passwordEncoder.encode("pomocnik2"))
				.role(Role.ZAWODNIK)
				.waga(72)
				.wzrost(175)
				.kraj_pochodzenia(Set.of(ES))
				.pozycja(pomocnikLP)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://slask-www-cdn.stellis.one/imgsize-m/documents/7763504/412023eb-7c79-4d07-6fdb-21f99e18df83")
				.build();

		Zawodnik JakubSwierczok = Zawodnik.builder()
				.login("JakubSwierczok")
				.email("JakubSwierczok@gmail.com")
				.imie("Jakub")
				.nazwisko("Świerczok")
				.data_Urodzenia(LocalDate.of(1992, 12, 28))
				.pesel(921228678)
				.haslo(passwordEncoder.encode("napastnik1"))
				.role(Role.ZAWODNIK)
				.waga(82)
				.wzrost(184)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(napastnikSN)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://www.zaglebie.com/aktualnosci/2024/11/image-thumb__125998__news-index-cover/Swierczok1~-~768w.jpeg")
				.build();

		zawodnikRepository.saveAll(List.of(
				JakubSwierczok,ArnauOrtiz,TommasoGuercio,
				AleksanderPaluszek,RafalLeszczynski
		));

		SlaskWroclaw.dodajZawodnika(JakubSwierczok,LocalDate.now());
		SlaskWroclaw.dodajZawodnika(ArnauOrtiz,LocalDate.now());
		SlaskWroclaw.dodajZawodnika(TommasoGuercio,LocalDate.now());
		SlaskWroclaw.dodajZawodnika(RafalLeszczynski,LocalDate.now());
		SlaskWroclaw.dodajZawodnika(AleksanderPaluszek,LocalDate.now());

		klubRepository.save(SlaskWroclaw);
		zawodnikRepository.saveAll(List.of(
				JakubSwierczok,ArnauOrtiz,TommasoGuercio,
				AleksanderPaluszek,RafalLeszczynski
		));

		for (Obecny_klub ob : SlaskWroclaw.getSetObecnyKlub()) {
			obecny_klubRepository.save(ob);
		}

		// KGHM zagłebie Lubin

		Zawodnik DominikHladun = Zawodnik.builder()
				.login("DominikHladun")
				.email("DominikHladun@gmail.com")
				.imie("Dominik")
				.nazwisko("Hładun")
				.data_Urodzenia(LocalDate.of(1995, 9, 17))
				.pesel(950917123)
				.haslo(passwordEncoder.encode("bramkarz1"))
				.role(Role.ZAWODNIK)
				.waga(82)
				.wzrost(190)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(bramkarzBR)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://www.zaglebie.com/zespol/pierwsza%20druzyna/2024-2025/Front/image-thumb__123899__team-index-cover/Dominik-Hladun--niebieskie-~-~768w.png")
				.build();

		Zawodnik PatrykKusztal = Zawodnik.builder()
				.login("PatrykKusztal")
				.email("PatrykKusztal@gmail.com")
				.imie("Patryk")
				.nazwisko("Kusztal")
				.data_Urodzenia(LocalDate.of(2003, 3, 28))
				.pesel(1030328789)
				.haslo(passwordEncoder.encode("pomocnik1"))
				.role(Role.ZAWODNIK)
				.waga(73)
				.wzrost(176)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(pomocnikSPO)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://www.zaglebie.com/zespol/pierwsza%20druzyna/2024-2025/Front/image-thumb__123860__team-index-cover/Patryk-Kusztal--1--pierwszy-komplet-~-~768w.png")
				.build();

		Zawodnik VaclavSejk = Zawodnik.builder()
				.login("VaclavSejk")
				.email("VaclavSejk@gmail.com")
				.imie("Vaclav")
				.nazwisko("Sejk")
				.data_Urodzenia(LocalDate.of(2002, 5, 18))
				.pesel(720518456)
				.haslo(passwordEncoder.encode("napastnik1"))
				.role(Role.ZAWODNIK)
				.waga(75)
				.wzrost(180)
				.kraj_pochodzenia(Set.of(CZ))
				.pozycja(napastnikSN)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://mkszaglebie.pl/uploads/gallery/026072024_MM_2.jpeg")
				.build();

		Zawodnik DawidKurminowski = Zawodnik.builder()
				.login("DawidKurminowski")
				.email("DawidKurminowski@gmail.com")
				.imie("Dawid")
				.nazwisko("Kurminowski")
				.data_Urodzenia(LocalDate.of(1999, 2, 24))
				.pesel(990224789)
				.haslo(passwordEncoder.encode("napastnik2"))
				.role(Role.ZAWODNIK)
				.waga(77)
				.wzrost(182)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(napastnikSN)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://www.zaglebie.com/zespol/pierwsza%20druzyna/2024-2025/Front/image-thumb__123843__team-index-cover/Dawid-Kurminowski--1--pierwszy-komplet-~-~768w.png")
				.build();

		Zawodnik LuisMata = Zawodnik.builder()
				.login("LuisMata")
				.email("LuisMata@gmail.com")
				.imie("Luís")
				.nazwisko("Mata")
				.data_Urodzenia(LocalDate.of(1997, 7, 6))
				.pesel(970706345)
				.haslo(passwordEncoder.encode("pomocnik2"))
				.role(Role.ZAWODNIK)
				.waga(74)
				.wzrost(181)
				.kraj_pochodzenia(Set.of(PT))
				.pozycja(pomocnikLP)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://www.zaglebie.com/zespol/pierwsza%20druzyna/2024-2025/Front/image-thumb__123842__team-index-cover/Luis-Mata--1--pierwszy-komplet-~-~768w.png")
				.build();

		zawodnikRepository.saveAll(List.of(
				LuisMata,DawidKurminowski,VaclavSejk,
				PatrykKusztal,DominikHladun
		));

		ZaglebieLubin.dodajZawodnika(LuisMata,LocalDate.now());
		ZaglebieLubin.dodajZawodnika(DawidKurminowski,LocalDate.now());
		ZaglebieLubin.dodajZawodnika(VaclavSejk,LocalDate.now());
		ZaglebieLubin.dodajZawodnika(DominikHladun,LocalDate.now());
		ZaglebieLubin.dodajZawodnika(PatrykKusztal,LocalDate.now());
		klubRepository.save(ZaglebieLubin);

		zawodnikRepository.saveAll(List.of(
				LuisMata,DawidKurminowski,VaclavSejk,
				PatrykKusztal,DominikHladun
		));

		for (Obecny_klub ob : ZaglebieLubin.getSetObecnyKlub()) {
			obecny_klubRepository.save(ob);
		}


		//radomiak

		Zawodnik MaciejKikolski = Zawodnik.builder()
				.login("MaciejKikolski")
				.email("MaciejKikolski@gmail.com")
				.imie("Maciej")
				.nazwisko("Kikolski")
				.data_Urodzenia(LocalDate.of(2004, 2, 23))
				.pesel(70402789)
				.haslo(passwordEncoder.encode("bramkarz1"))
				.role(Role.ZAWODNIK)
				.waga(81)
				.wzrost(192)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(bramkarzBR)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://twojradom.pl/wp-content/uploads/2024/06/phpThumb_generated_thumbnail-4.jpg")
				.build();

		Zawodnik DariuszPawlowski = Zawodnik.builder()
				.login("DariuszPawlowski")
				.email("DariuszPawlowski@gmail.com")
				.imie("Dariusz")
				.nazwisko("Pawłowski")
				.data_Urodzenia(LocalDate.of(1999, 2, 25))
				.pesel(990225123)
				.haslo(passwordEncoder.encode("obronca1"))
				.role(Role.ZAWODNIK)
				.waga(78)
				.wzrost(184)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(obroncaPO)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjbVN1yUV3M8YJVbFusYKdn0ndCwlXweIRzA&s")
				.build();

		Zawodnik BrunoJordao = Zawodnik.builder()
				.login("BrunoJordao")
				.email("BrunoJordao@gmail.com")
				.imie("Bruno")
				.nazwisko("Jordão")
				.data_Urodzenia(LocalDate.of(1998, 10, 12))
				.pesel(981012456)
				.haslo(passwordEncoder.encode("pomocnik1"))
				.role(Role.ZAWODNIK)
				.waga(75)
				.wzrost(180)
				.kraj_pochodzenia(Set.of(PT))
				.pozycja(pomocnikSPD)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://livesport-ott-images.ssl.cdn.cra.cz/r900xfq60/7648c86e-55f4-4ae7-9838-f25d357c7dd8.jpeg")
				.build();

		Zawodnik Luizao = Zawodnik.builder()
				.login("Luizao")
				.email("Luizao@gmail.com")
				.imie("Luizão")
				.nazwisko("Luizão")
				.data_Urodzenia(LocalDate.of(1998, 2, 20))
				.pesel(980220987)
				.haslo(passwordEncoder.encode("pomocnik2"))
				.role(Role.ZAWODNIK)
				.waga(77)
				.wzrost(184)
				.kraj_pochodzenia(Set.of(BR))
				.pozycja(pomocnikSPD)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://www.cozadzien.pl/img/2023/10/22/radomiak-radom-korona-kielce-zdjecia/_max/radomiak-korona-29.jpg")
				.build();

		Zawodnik GuilhermeZimovski = Zawodnik.builder()
				.login("GuilhermeZimovski")
				.email("GuilhermeZimovski@gmail.com")
				.imie("Guilherme")
				.nazwisko("Zimovski")
				.data_Urodzenia(LocalDate.of(2004, 12, 3))
				.pesel(2041203321)
				.haslo(passwordEncoder.encode("napastnik1"))
				.role(Role.ZAWODNIK)
				.waga(71)
				.wzrost(177)
				.kraj_pochodzenia(Set.of(BR, PL))
				.pozycja(napastnikPN)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://transfery.info/img/photos/95232/1500xauto/guilherme-zimovski.jpg")
				.build();

		zawodnikRepository.saveAll(List.of(
				GuilhermeZimovski,Luizao,BrunoJordao,
				DariuszPawlowski,MaciejKikolski
		));

		RadomiakRadom.dodajZawodnika(GuilhermeZimovski,LocalDate.now());
		RadomiakRadom.dodajZawodnika(Luizao,LocalDate.now());
		RadomiakRadom.dodajZawodnika(BrunoJordao,LocalDate.now());
		RadomiakRadom.dodajZawodnika(DariuszPawlowski,LocalDate.now());
		RadomiakRadom.dodajZawodnika(MaciejKikolski,LocalDate.now());
		klubRepository.save(RadomiakRadom);

		zawodnikRepository.saveAll(List.of(
				GuilhermeZimovski,Luizao,BrunoJordao,
				DariuszPawlowski,MaciejKikolski
		));

		for (Obecny_klub ob : RadomiakRadom.getSetObecnyKlub()) {
			obecny_klubRepository.save(ob);
		}

		//jagiellonia

		Zawodnik MaksymilianStryjek = Zawodnik.builder()
				.login("MaksymilianStryjek")
				.email("MaksymilianStryjek@gmail.com")
				.imie("Maksymilian")
				.nazwisko("Stryjek")
				.data_Urodzenia(LocalDate.of(1996, 7, 18))
				.pesel(960718123)
				.haslo(passwordEncoder.encode("bramkarz1"))
				.role(Role.ZAWODNIK)
				.waga(83)
				.wzrost(188)
				.kraj_pochodzenia(Set.of(PL, ANG))
				.pozycja(bramkarzBR)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://www.radio.bialystok.pl/src/4/351b74fdeae8b0f3bcd273df9709ef6f")
				.build();

		Zawodnik MateuszSkrzypczak = Zawodnik.builder()
				.login("MateuszSkrzypczak")
				.email("MateuszSkrzypczak@gmail.com")
				.imie("Mateusz")
				.nazwisko("Skrzypczak")
				.data_Urodzenia(LocalDate.of(2000, 8, 22))
				.pesel(20822456)
				.haslo(passwordEncoder.encode("obronca1"))
				.role(Role.ZAWODNIK)
				.waga(82)
				.wzrost(189)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(obroncaSO)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://s2.tvp.pl/images2/b/a/5/uid_ba514e61bb704ef687ca63db364466aa_width_1200_play_0_pos_0_gs_0_height_678_mateusz-skrzypczak-fot-getty-images.jpg")
				.build();

		Zawodnik AdrianDieguez = Zawodnik.builder()
				.login("AdrianDieguez")
				.email("AdrianDieguez@gmail.com")
				.imie("Adrián")
				.nazwisko("Diéguez")
				.data_Urodzenia(LocalDate.of(1996, 2, 4))
				.pesel(960204789)
				.haslo(passwordEncoder.encode("obronca2"))
				.role(Role.ZAWODNIK)
				.waga(84)
				.wzrost(188)
				.kraj_pochodzenia(Set.of(ES))
				.pozycja(obroncaSO)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://jagiellonia.net/gfx/upload/zawodnicy/2024-07-31_09-37-48_261185.png")
				.build();

		Zawodnik AurelienNguiamba = Zawodnik.builder()
				.login("AurelienNguiamba")
				.email("AurelienNguiamba@gmail.com")
				.imie("Aurélien")
				.nazwisko("Nguiamba")
				.data_Urodzenia(LocalDate.of(1999, 1, 18))
				.pesel(990118654)
				.haslo(passwordEncoder.encode("pomocnik1"))
				.role(Role.ZAWODNIK)
				.waga(76)
				.wzrost(179)
				.kraj_pochodzenia(Set.of(FR, CM))
				.pozycja(pomocnikSPD)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://akadera.bialystok.pl/app/uploads/2023/09/20230911_NGUIAMBA013.jpg")
				.build();

		Zawodnik LamineDiabyFadiga = Zawodnik.builder()
				.login("LamineDiabyFadiga")
				.email("LamineDiabyFadiga@gmail.com")
				.imie("Lamine")
				.nazwisko("Diaby-Fadiga")
				.data_Urodzenia(LocalDate.of(2001, 1, 19))
				.pesel(910119321)
				.haslo(passwordEncoder.encode("napastnik1"))
				.role(Role.ZAWODNIK)
				.waga(82)
				.wzrost(188)
				.kraj_pochodzenia(Set.of(FR, GN))
				.pozycja(napastnikSN)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://tmssl.akamaized.net/images/foto/galerie/diaby-fadiga-lamine-2024-2025-jagiellonia-bialystok-1724099926-145623.jpg")
				.build();

		Zawodnik JesusImaz = Zawodnik.builder()
				.login("JesusImaz")
				.email("JesusImaz@gmail.com")
				.imie("Jesús")
				.nazwisko("Imaz")
				.data_Urodzenia(LocalDate.of(1990, 9, 26))
				.pesel(900926987)
				.haslo(passwordEncoder.encode("napastnik2"))
				.role(Role.ZAWODNIK)
				.waga(72)
				.wzrost(174)
				.kraj_pochodzenia(Set.of(ES))
				.pozycja(napastnikSN)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://transfery.info/img/photos/95918/1500xauto/jesus-imaz.jpg")
				.build();


		zawodnikRepository.saveAll(List.of(
				JesusImaz,LamineDiabyFadiga,AurelienNguiamba,
				AdrianDieguez,MaksymilianStryjek,MateuszSkrzypczak
		));

		JagielloniaBialystok.dodajZawodnika(JesusImaz,LocalDate.now());
		JagielloniaBialystok.dodajZawodnika(LamineDiabyFadiga,LocalDate.now());
		JagielloniaBialystok.dodajZawodnika(AurelienNguiamba,LocalDate.now());
		JagielloniaBialystok.dodajZawodnika(AdrianDieguez,LocalDate.now());
		JagielloniaBialystok.dodajZawodnika(MaksymilianStryjek,LocalDate.now());
		JagielloniaBialystok.dodajZawodnika(MateuszSkrzypczak,LocalDate.now());
		klubRepository.save(JagielloniaBialystok);

		zawodnikRepository.saveAll(List.of(
				JesusImaz,LamineDiabyFadiga,AurelienNguiamba,
				AdrianDieguez,MaksymilianStryjek,MateuszSkrzypczak
		));

		for (Obecny_klub ob : JagielloniaBialystok.getSetObecnyKlub()) {
			obecny_klubRepository.save(ob);
		}

		//Widzew Łódź

		Zawodnik RafalGikiewicz = Zawodnik.builder()
				.login("RafalGikiewicz")
				.email("RafalGikiewicz@gmail.com")
				.imie("Rafał")
				.nazwisko("Gikiewicz")
				.data_Urodzenia(LocalDate.of(1987, 10, 26))
				.pesel(871026123)
				.haslo(passwordEncoder.encode("bramkarz1"))
				.role(Role.ZAWODNIK)
				.waga(84)
				.wzrost(190)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(bramkarzBR)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://www.goal.pl/wp-content/uploads/2024/10/Rafal-Gikiewicz-1.jpg")
				.build();

		Zawodnik MateuszZyro = Zawodnik.builder()
				.login("MateuszZyro")
				.email("MateuszZyro@gmail.com")
				.imie("Mateusz")
				.nazwisko("Żyro")
				.data_Urodzenia(LocalDate.of(1998, 10, 28))
				.pesel(981028456)
				.haslo(passwordEncoder.encode("obronca1"))
				.role(Role.ZAWODNIK)
				.waga(82)
				.wzrost(190)
				.kraj_pochodzenia(Set.of(PL))
				.pozycja(obroncaSO)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://widzew.com/images/sezon24-25/Glowki-www_2024-25/4_Zyro.png")
				.build();

		Zawodnik JuanIbiza = Zawodnik.builder()
				.login("JuanIbiza")
				.email("JuanIbiza@gmail.com")
				.imie("Juan")
				.nazwisko("Ibiza")
				.data_Urodzenia(LocalDate.of(1995, 8, 17))
				.pesel(950817789)
				.haslo(passwordEncoder.encode("obronca2"))
				.role(Role.ZAWODNIK)
				.waga(81)
				.wzrost(187)
				.kraj_pochodzenia(Set.of(ES))
				.pozycja(obroncaSO)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://widzew.com/images/sezon2023-24/Ludzie/Ibiza/IMG_3052.JPG")
				.build();

		Zawodnik LuisSilva = Zawodnik.builder()
				.login("LuisSilva")
				.email("LuisSilva@gmail.com")
				.imie("Luís")
				.nazwisko("Silva")
				.data_Urodzenia(LocalDate.of(1999, 2, 18))
				.pesel(990218654)
				.haslo(passwordEncoder.encode("obronca3"))
				.role(Role.ZAWODNIK)
				.waga(79)
				.wzrost(186)
				.kraj_pochodzenia(Set.of(PT))
				.pozycja(obroncaSO)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://widzew.com/images/sezon2023-24/Ludzie/Luis-Silva/IMG_8901-Enhanced-NR.JPG")
				.build();

		Zawodnik FabioNunes = Zawodnik.builder()
				.login("FabioNunes")
				.email("FabioNunes@gmail.com")
				.imie("Fábio")
				.nazwisko("Nunes")
				.data_Urodzenia(LocalDate.of(1992, 7, 24))
				.pesel(920724321)
				.haslo(passwordEncoder.encode("napastnik1"))
				.role(Role.ZAWODNIK)
				.waga(76)
				.wzrost(180)
				.kraj_pochodzenia(Set.of(PT))
				.pozycja(napastnikLN)
				.obecny_klub(new HashSet<>())
				.transfery(new ArrayList<>())
				.profiloweURL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQRxCnihC3muxHbjrr45LaxOTjkp13wDNz9oQ&s")
				.build();


		zawodnikRepository.saveAll(List.of(
				RafalGikiewicz,FabioNunes,LuisSilva,
				JuanIbiza,MateuszZyro
		));

		WidzewLodz.dodajZawodnika(RafalGikiewicz,LocalDate.now());
		WidzewLodz.dodajZawodnika(FabioNunes,LocalDate.now());
		WidzewLodz.dodajZawodnika(LuisSilva,LocalDate.now());
		WidzewLodz.dodajZawodnika(MateuszZyro,LocalDate.now());
		WidzewLodz.dodajZawodnika(JuanIbiza,LocalDate.now());
		klubRepository.save(WidzewLodz);

		zawodnikRepository.saveAll(List.of(
				RafalGikiewicz,FabioNunes,LuisSilva,
				JuanIbiza,MateuszZyro
		));

		for (Obecny_klub ob : WidzewLodz.getSetObecnyKlub()) {
			obecny_klubRepository.save(ob);
		}

		//Skauci klubów
		Skaut skaut2 = Skaut.builder()
				.login("skaut2")
				.email("skaut2@gmail.com")
				.imie("Juan")
				.nazwisko("Garcia")
				.data_Urodzenia(LocalDate.of(1988, 3, 15))
				.pesel(567891235)
				.haslo(passwordEncoder.encode("skaut456"))
				.role(Role.SKAUT)
				.kraj_pochodzenia(Set.of(ES))
				.skautKlubu(PiastGliwice)
				.build();

		Skaut skaut3 = Skaut.builder()
				.login("skaut3")
				.email("skaut3@gmail.com")
				.imie("Carlos")
				.nazwisko("Silva")
				.data_Urodzenia(LocalDate.of(1992, 10, 10))
				.pesel(567891236)
				.haslo(passwordEncoder.encode("skaut789"))
				.role(Role.SKAUT)
				.kraj_pochodzenia(Set.of(PT, BR))
				.skautKlubu(JagielloniaBialystok)
				.build();

		Skaut skaut4 = Skaut.builder()
				.login("skaut4")
				.email("skaut4@gmail.com")
				.imie("David")
				.nazwisko("Smith")
				.data_Urodzenia(LocalDate.of(1995, 12, 5))
				.pesel(567891237)
				.haslo(passwordEncoder.encode("skaut101"))
				.role(Role.SKAUT)
				.kraj_pochodzenia(Set.of(ANG))
				.skautKlubu(WidzewLodz)
				.build();

		Skaut skaut5 = Skaut.builder()
				.login("skaut5")
				.email("skaut5@gmail.com")
				.imie("Luca")
				.nazwisko("Rossi")
				.data_Urodzenia(LocalDate.of(1990, 4, 18))
				.pesel(567891238)
				.haslo(passwordEncoder.encode("skaut102"))
				.role(Role.SKAUT)
				.kraj_pochodzenia(Set.of(IT))
				.skautKlubu(RadomiakRadom)
				.build();

		Skaut skaut6 = Skaut.builder()
				.login("skaut6")
				.email("skaut6@gmail.com")
				.imie("Yuki")
				.nazwisko("Tanaka")
				.data_Urodzenia(LocalDate.of(1994, 7, 21))
				.pesel(567891239)
				.haslo(passwordEncoder.encode("skaut103"))
				.role(Role.SKAUT)
				.kraj_pochodzenia(Set.of(JP))
				.skautKlubu(SlaskWroclaw)
				.build();

		Skaut skaut7 = Skaut.builder()
				.login("skaut7")
				.email("skaut7@gmail.com")
				.imie("Samuel")
				.nazwisko("Johnson")
				.data_Urodzenia(LocalDate.of(1989, 8, 30))
				.pesel(567891240)
				.haslo(passwordEncoder.encode("skaut104"))
				.role(Role.SKAUT)
				.kraj_pochodzenia(Set.of(ANG))
				.skautKlubu(ZaglebieLubin)
				.build();

		Skaut skaut8 = Skaut.builder()
				.login("skaut8")
				.email("skaut8@gmail.com")
				.imie("Thiago")
				.nazwisko("Costa")
				.data_Urodzenia(LocalDate.of(1993, 11, 3))
				.pesel(567891241)
				.haslo(passwordEncoder.encode("skaut105"))
				.role(Role.SKAUT)
				.kraj_pochodzenia(Set.of(BR))
				.skautKlubu(RakowCzestochowa)
				.build();

		Skaut skaut9 = Skaut.builder()
				.login("skaut9")
				.email("skaut9@gmail.com")
				.imie("Artem")
				.nazwisko("Ivanov")
				.data_Urodzenia(LocalDate.of(1996, 1, 19))
				.pesel(567891242)
				.haslo(passwordEncoder.encode("skaut106"))
				.role(Role.SKAUT)
				.kraj_pochodzenia(Set.of(UA, PL))
				.skautKlubu(GornikZabrze)
				.build();

		Skaut skaut10 = Skaut.builder()
				.login("skaut10")
				.email("skaut10@gmail.com")
				.imie("Jean")
				.nazwisko("Dupont")
				.data_Urodzenia(LocalDate.of(1991, 5, 27))
				.pesel(567891243)
				.haslo(passwordEncoder.encode("skaut107"))
				.role(Role.SKAUT)
				.kraj_pochodzenia(Set.of(FR))
				.skautKlubu(LechPoznan)
				.build();

		Skaut skaut11 = Skaut.builder()
				.login("skaut11")
				.email("skaut11@gmail.com")
				.imie("Andreas")
				.nazwisko("Müller")
				.data_Urodzenia(LocalDate.of(1997, 6, 14))
				.pesel(567891244)
				.haslo(passwordEncoder.encode("skaut108"))
				.role(Role.SKAUT)
				.kraj_pochodzenia(Set.of(DE,PL))
				.skautKlubu(GKSKatowice)
				.build();

		skautRepository.saveAll(List.of(
				skaut2,skaut3,skaut4,skaut5,skaut6,skaut7,skaut8,skaut9,skaut10,skaut11
		));

		LechPoznan.setSkaut(skaut10);
		GKSKatowice.setSkaut(skaut11);
		GornikZabrze.setSkaut(skaut9);
		RakowCzestochowa.setSkaut(skaut8);
		ZaglebieLubin.setSkaut(skaut7);
		SlaskWroclaw.setSkaut(skaut6);
		RadomiakRadom.setSkaut(skaut5);
		WidzewLodz.setSkaut(skaut4);
		JagielloniaBialystok.setSkaut(skaut3);
		PiastGliwice.setSkaut(skaut2);

		//Trenerzy

		// Sztab klubu
		Trener NF = Trener.builder()
				.login("NF1")
				.email("NF1@gmail.com")
				.imie("Niels")
				.nazwisko("Frederiksen")
				.data_Urodzenia(LocalDate.of(1969, 11, 5))
				.pesel(123456789)
				.haslo(passwordEncoder.encode("trener123"))
				.role(Role.TRENER)
				.kraj_pochodzenia(Set.of(DK))
				.licencja_trenera("UEFA PRO")
				.trenerKlub(LechPoznan)
				.build();

		Trener MP = Trener.builder()
				.login("MP1")
				.email("MP1@gmail.com")
				.imie("Marek")
				.nazwisko("Papszun")
				.data_Urodzenia(LocalDate.of(1973, 8, 8))
				.pesel(123456788)
				.haslo(passwordEncoder.encode("trener123"))
				.role(Role.TRENER)
				.kraj_pochodzenia(Set.of(PL))
				.licencja_trenera("UEFA PRO")
				.trenerKlub(RakowCzestochowa)
				.build();

		Trener AS = Trener.builder()
				.login("AS1")
				.email("AS1@gmail.com")
				.imie("Adrian")
				.nazwisko("Siemieniec")
				.data_Urodzenia(LocalDate.of(1991, 1, 13))
				.pesel(123456787)
				.haslo(passwordEncoder.encode("trener123"))
				.role(Role.TRENER)
				.kraj_pochodzenia(Set.of(PL))
				.licencja_trenera("UEFA PRO")
				.trenerKlub(JagielloniaBialystok)
				.build();

		Trener JU = Trener.builder()
				.login("JU1")
				.email("JU1@gmail.com")
				.imie("Jan")
				.nazwisko("Urban")
				.data_Urodzenia(LocalDate.of(1961, 5, 14))
				.pesel(123456786)
				.haslo(passwordEncoder.encode("trener123"))
				.role(Role.TRENER)
				.kraj_pochodzenia(Set.of(PL))
				.licencja_trenera("UEFA PRO")
				.trenerKlub(GornikZabrze)
				.build();

		Trener MW = Trener.builder()
				.login("MW1")
				.email("MW1@gmail.com")
				.imie("Marcin")
				.nazwisko("Wlodarski")
				.data_Urodzenia(LocalDate.of(1982, 9, 6))
				.pesel(123456785)
				.haslo(passwordEncoder.encode("trener123"))
				.role(Role.TRENER)
				.kraj_pochodzenia(Set.of(PL))
				.licencja_trenera("UEFA PRO")
				.trenerKlub(ZaglebieLubin)
				.build();

		Trener DM = Trener.builder()
				.login("DM1")
				.email("DM1@gmail.com")
				.imie("Daniel")
				.nazwisko("Mysliwiec")
				.data_Urodzenia(LocalDate.of(1984, 11, 19))
				.pesel(123456784)
				.haslo(passwordEncoder.encode("trener123"))
				.role(Role.TRENER)
				.kraj_pochodzenia(Set.of(PL))
				.licencja_trenera("UEFA PRO")
				.trenerKlub(WidzewLodz)
				.build();

		Trener RG = Trener.builder()
				.login("RG1")
				.email("RG1@gmail.com")
				.imie("Rafal")
				.nazwisko("Gorak")
				.data_Urodzenia(LocalDate.of(1972, 3, 30))
				.pesel(123456783)
				.haslo(passwordEncoder.encode("trener123"))
				.role(Role.TRENER)
				.kraj_pochodzenia(Set.of(PL))
				.licencja_trenera("UEFA PRO")
				.trenerKlub(GKSKatowice)
				.build();

		Trener AV = Trener.builder()
				.login("AV1")
				.email("AV1@gmail.com")
				.imie("Aleksandar")
				.nazwisko("Vukovic")
				.data_Urodzenia(LocalDate.of(1978, 8, 25))
				.pesel(123456782)
				.haslo(passwordEncoder.encode("trener123"))
				.role(Role.TRENER)
				.kraj_pochodzenia(Set.of(SR, PL))
				.licencja_trenera("UEFA PRO")
				.trenerKlub(PiastGliwice)
				.build();

		Trener BB = Trener.builder()
				.login("BB1")
				.email("BB1@gmail.com")
				.imie("Bruno")
				.nazwisko("Baltazar")
				.data_Urodzenia(LocalDate.of(1976, 7, 6))
				.pesel(123456781)
				.haslo(passwordEncoder.encode("trener123"))
				.role(Role.TRENER)
				.kraj_pochodzenia(Set.of(PT))
				.licencja_trenera("UEFA PRO")
				.trenerKlub(RadomiakRadom)
				.build();

		Trener MH = Trener.builder()
				.login("MH1")
				.email("MH1@gmail.com")
				.imie("Michal")
				.nazwisko("Hetel")
				.data_Urodzenia(LocalDate.of(1993, 8, 24))
				.pesel(123456780)
				.haslo(passwordEncoder.encode("trener123"))
				.role(Role.TRENER)
				.kraj_pochodzenia(Set.of(PL))
				.licencja_trenera("UEFA PRO")
				.trenerKlub(SlaskWroclaw)
				.build();

		trenerRepository.saveAll(List.of(
				MH,AV,AS,DM,BB,MW,MP,JU,RG,NF
		));

		LechPoznan.setTrener(NF);
		GKSKatowice.setTrener(RG);
		GornikZabrze.setTrener(JU);
		RakowCzestochowa.setTrener(MP);
		ZaglebieLubin.setTrener(MW);
		SlaskWroclaw.setTrener(MH);
		RadomiakRadom.setTrener(BB);
		WidzewLodz.setTrener(DM);
		JagielloniaBialystok.setTrener(AS);
		PiastGliwice.setTrener(AV);

		//Menadzerowie

		Menadzer_klubu DariuszMioduski = Menadzer_klubu.builder()
				.login("DariuszMioduski")
				.email("DariuszMioduski@gmail.com")
				.imie("Dariusz")
				.nazwisko("Mioduski")
				.data_Urodzenia(LocalDate.of(1993, 8, 24))
				.pesel(3215436)
				.haslo(passwordEncoder.encode("Menadzer"))
				.role(Role.MENADZER_KLUBU)
				.kraj_pochodzenia(Set.of(PL))
				.menadzerKlubu(LegiaWarszawa)
				.profiloweURL("https://www.goal.pl/wp-content/uploads/2023/02/mioduski-dariusz-960x640.jpg")
				.build();

		Menadzer_klubu KarolKlimczak = Menadzer_klubu.builder()
				.login("KarolKlimczak")
				.email("KarolKlimczak@gmail.com")
				.imie("Karol")
				.nazwisko("Klimczak")
				.data_Urodzenia(LocalDate.of(1973, 3, 12))
				.pesel(56865234)
				.haslo(passwordEncoder.encode("Menadzer"))
				.role(Role.MENADZER_KLUBU)
				.kraj_pochodzenia(Set.of(PL))
				.menadzerKlubu(LechPoznan)
				.build();

		Menadzer_klubu KrzysztofNowak = Menadzer_klubu.builder()
				.login("KrzysztofNowak")
				.email("KrzysztofNowak@gmail.com")
				.imie("Krzysztof")
				.nazwisko("Nowak")
				.data_Urodzenia(LocalDate.of(1978, 5, 24))
				.pesel(987654321)
				.haslo(passwordEncoder.encode("Menadzer"))
				.role(Role.MENADZER_KLUBU)
				.kraj_pochodzenia(Set.of(PL))
				.menadzerKlubu(GKSKatowice)
				.build();

		Menadzer_klubu ArkadiuszSzymanka = Menadzer_klubu.builder()
				.login("ArkadiuszSzymanka")
				.email("ArkadiuszSzymanka@gmail.com")
				.imie("Arkadiusz")
				.nazwisko("Szymanka")
				.data_Urodzenia(LocalDate.of(1985, 2, 14))
				.pesel(345678901)
				.haslo(passwordEncoder.encode("Menadzer"))
				.role(Role.MENADZER_KLUBU)
				.kraj_pochodzenia(Set.of(PL))
				.menadzerKlubu(GornikZabrze)
				.build();

		Menadzer_klubu PiotrObidzinski = Menadzer_klubu.builder()
				.login("PiotrObidzinski")
				.email("PiotrObidzinski@gmail.com")
				.imie("Piotr")
				.nazwisko("Obidziński")
				.data_Urodzenia(LocalDate.of(1981, 6, 20))
				.pesel(456789012)
				.haslo(passwordEncoder.encode("Menadzer"))
				.role(Role.MENADZER_KLUBU)
				.kraj_pochodzenia(Set.of(PL))
				.menadzerKlubu(RakowCzestochowa)
				.build();

		Menadzer_klubu PawelJez = Menadzer_klubu.builder()
				.login("PawelJez")
				.email("PawelJez@gmail.com")
				.imie("Paweł")
				.nazwisko("Jeż")
				.data_Urodzenia(LocalDate.of(1990, 11, 30))
				.pesel(567890123)
				.haslo(passwordEncoder.encode("Menadzer"))
				.role(Role.MENADZER_KLUBU)
				.kraj_pochodzenia(Set.of(PL))
				.menadzerKlubu(ZaglebieLubin)
				.build();

		Menadzer_klubu PatrykZaleczny = Menadzer_klubu.builder()
				.login("PatrykZaleczny")
				.email("PatrykZaleczny@gmail.com")
				.imie("Patryk")
				.nazwisko("Załęczny")
				.data_Urodzenia(LocalDate.of(1989, 8, 19))
				.pesel(678901234)
				.haslo(passwordEncoder.encode("Menadzer"))
				.role(Role.MENADZER_KLUBU)
				.kraj_pochodzenia(Set.of(PL))
				.menadzerKlubu(SlaskWroclaw)
				.build();

		Menadzer_klubu SlawomirStempniewski = Menadzer_klubu.builder()
				.login("SlawomirStempniewski")
				.email("SlawomirStempniewski@gmail.com")
				.imie("Sławomir")
				.nazwisko("Stempniewski")
				.data_Urodzenia(LocalDate.of(1982, 1, 28))
				.pesel(789012345)
				.haslo(passwordEncoder.encode("Menadzer"))
				.role(Role.MENADZER_KLUBU)
				.kraj_pochodzenia(Set.of(PL))
				.menadzerKlubu(RadomiakRadom)
				.build();

		Menadzer_klubu TomaszStamirowski = Menadzer_klubu.builder()
				.login("TomaszStamirowski")
				.email("TomaszStamirowski@gmail.com")
				.imie("Tomasz")
				.nazwisko("Stamirowski")
				.data_Urodzenia(LocalDate.of(1983, 3, 15))
				.pesel(890123456)
				.haslo(passwordEncoder.encode("Menadzer"))
				.role(Role.MENADZER_KLUBU)
				.kraj_pochodzenia(Set.of(PL))
				.menadzerKlubu(WidzewLodz)
				.build();

		Menadzer_klubu WojciechPertkiewicz = Menadzer_klubu.builder()
				.login("WojciechPertkiewicz")
				.email("WojciechPertkiewicz@gmail.com")
				.imie("Wojciech")
				.nazwisko("Pertkiewicz")
				.data_Urodzenia(LocalDate.of(1988, 7, 4))
				.pesel(901234567)
				.haslo(passwordEncoder.encode("Menadzer"))
				.role(Role.MENADZER_KLUBU)
				.kraj_pochodzenia(Set.of(PL))
				.menadzerKlubu(JagielloniaBialystok)
				.build();

		Menadzer_klubu LukaszLewinski = Menadzer_klubu.builder()
				.login("LukaszLewinski")
				.email("LukaszLewinski@gmail.com")
				.imie("Łukasz")
				.nazwisko("Lewiński")
				.data_Urodzenia(LocalDate.of(1987, 9, 22))
				.pesel(77643188)
				.haslo(passwordEncoder.encode("Menadzer"))
				.role(Role.MENADZER_KLUBU)
				.kraj_pochodzenia(Set.of(PL))
				.menadzerKlubu(PiastGliwice)
				.build();

		menadzer_klubuRepository.saveAll(List.of(
				DariuszMioduski,KarolKlimczak,KrzysztofNowak,ArkadiuszSzymanka,PiotrObidzinski,
				PawelJez,PatrykZaleczny,SlawomirStempniewski,TomaszStamirowski,WojciechPertkiewicz,
				LukaszLewinski
		));

		LegiaWarszawa.setMenadzer_klubu(DariuszMioduski);
		LechPoznan.setMenadzer_klubu(KarolKlimczak);
		GKSKatowice.setMenadzer_klubu(KrzysztofNowak);
		GornikZabrze.setMenadzer_klubu(ArkadiuszSzymanka);
		RakowCzestochowa.setMenadzer_klubu(PiotrObidzinski);
		ZaglebieLubin.setMenadzer_klubu(PawelJez);
		SlaskWroclaw.setMenadzer_klubu(PatrykZaleczny);
		RadomiakRadom.setMenadzer_klubu(SlawomirStempniewski);
		WidzewLodz.setMenadzer_klubu(TomaszStamirowski);
		JagielloniaBialystok.setMenadzer_klubu(WojciechPertkiewicz);
		PiastGliwice.setMenadzer_klubu(LukaszLewinski);


		klubRepository.saveAll(List.of(
				 LegiaWarszawa,LechPoznan, GKSKatowice, GornikZabrze, RakowCzestochowa, ZaglebieLubin,
				SlaskWroclaw, RadomiakRadom, WidzewLodz, JagielloniaBialystok, PiastGliwice
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
