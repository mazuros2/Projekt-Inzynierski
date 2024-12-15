import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const WyswietlanieZawodnikow = () => {
  const navigate = useNavigate();
  const [zawodnicy, setZawodnicy] = useState([]);
  const [pozycje, setPozycje] = useState([]);
  const [kraje, setKraje] = useState([]);
  const [filterOptions, setFilterOptions] = useState({
    pozycja: "",
    obszar: "",
    region: "",
    kraj: "",
    imie: "",
    nazwisko: ""
  });

  useEffect(() => {
    const token = sessionStorage.getItem("token");
    if (!token) {
      console.error("Brak tokena. Przekierowanie do logowania.");
      navigate("/logowanie");
      return;
    }

    axios
      .get("http://localhost:8080/zawodnicy", {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        setZawodnicy(response.data);
      })
      .catch((error) => {
        console.error("Błąd podczas pobierania zawodników:", error);
      });

    axios
      .get("http://localhost:8080/api/pozycja/getpozycje", {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        setPozycje(response.data);
      })
      .catch((error) => {
        console.error("Błąd podczas pobierania pozycji:", error);
      });

    axios
      .get("http://localhost:8080/api/krajpochodzenia/getkraje", {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        setKraje(response.data);
      })
      .catch((error) => {
        console.error("Błąd podczas pobierania krajów:", error);
      });
  }, [navigate]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFilterOptions((prevState) => ({ ...prevState, [name]: value }));
  };

  const filterZawodnicy = () => {
    return zawodnicy.filter((zawodnik) => {
      const { pozycja, obszar, region, kraj, imie, nazwisko } = filterOptions;
      const matchesPozycja = pozycja ? zawodnik.pozycja?.toLowerCase().includes(pozycja.toLowerCase()) : true;
      const matchesObszar = obszar ? zawodnik.obszar?.toLowerCase().includes(obszar.toLowerCase()) : true;
      const matchesRegion = region ? zawodnik.region?.toLowerCase().includes(region.toLowerCase()) : true;
      const matchesKraj = kraj ? zawodnik.kraj?.toLowerCase().includes(kraj.toLowerCase()) : true;
      const matchesImie = imie ? zawodnik.imie?.toLowerCase().includes(imie.toLowerCase()) : true;
      const matchesNazwisko = nazwisko ? zawodnik.nazwisko?.toLowerCase().includes(nazwisko.toLowerCase()) : true;
      return matchesPozycja && matchesObszar && matchesRegion && matchesKraj && matchesImie && matchesNazwisko;
    });
  };

  return (
    <div>
      <h1>Lista Zawodników</h1>
      
      <div>
        <h3>Filtry</h3>
        <input type="text" name="imie" placeholder="Imię" onChange={handleInputChange} />
        <input type="text" name="nazwisko" placeholder="Nazwisko" onChange={handleInputChange} />
        <select name="pozycja" onChange={handleInputChange}>
          <option value="">Wybierz pozycję</option>
          {pozycje.map((pozycja) => (
            <option key={pozycja.id_Pozycja} value={pozycja.nazwa_pozycji}>{pozycja.nazwa_pozycji}</option>
          ))}
        </select>
        <select name="obszar" onChange={handleInputChange}>
          <option value="">Wybierz obszar</option>
          {[...new Set(pozycje.map((pozycja) => pozycja.obszar_pozycji))].map((obszar, index) => (
            <option key={index} value={obszar}>{obszar}</option>
          ))}
        </select>
        <select name="region" onChange={handleInputChange}>
          <option value="">Wybierz region</option>
          {[...new Set(kraje.map((kraj) => kraj.region))].map((region, index) => (
            <option key={index} value={region}>{region}</option>
          ))}
        </select>
        <select name="kraj" onChange={handleInputChange}>
          <option value="">Wybierz kraj</option>
          {kraje.map((kraj) => (
            <option key={kraj.id_Kraj} value={kraj.nazwa}>{kraj.nazwa}</option>
          ))}
        </select>
      </div>

      <h2>Lista Zawodników</h2>
      {filterZawodnicy().length > 0 ? (
        <ul>
          {filterZawodnicy().map((zawodnik) => (
            <li key={zawodnik.id}>
              <h3>{zawodnik.imie} {zawodnik.nazwisko}</h3>
              <p>Pozycja: {zawodnik.pozycja}</p>
              <p>Kraj: {zawodnik.krajePochodzenia}</p>
              <button onClick={() => navigate(`/zawodnicy/profil/${zawodnik.id}`)}>Zobacz szczegóły</button>
            </li>
          ))}
        </ul>
      ) : (
        <p>Brak zawodników spełniających kryteria wyszukiwania.</p>
      )}
    </div>
  );
};

export default WyswietlanieZawodnikow;
