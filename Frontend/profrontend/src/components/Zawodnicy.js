import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import '../cssFolder/Navbar.css'; 
import '../cssFolder/ZawodnicyiTrenerzyFilter.css';
import Navbar from '../components/Navbar';

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

  const [currentPage, setCurrentPage] = useState(1);
  const zawodnicyPerPage = 12;

  useEffect(() => {
    const token = sessionStorage.getItem("token");
    if (!token) {
      console.error("Brak tokena. Przekierowanie do logowania.");
      navigate("/logowanie");
      return;
    }

    axios
      .get(`${process.env.REACT_APP_API_URL}/zawodnicy`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        setZawodnicy(response.data);
      })
      .catch((error) => {
        console.error("Błąd podczas pobierania zawodników:", error);
      });

    axios
      .get(`${process.env.REACT_APP_API_URL}/api/pozycja/getpozycje`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        setPozycje(response.data);
      })
      .catch((error) => {
        console.error("Błąd podczas pobierania pozycji:", error);
      });

    axios
      .get(`${process.env.REACT_APP_API_URL}/api/krajpochodzenia/getkraje`, {
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
      const matchesPozycja = pozycja ? zawodnik.pozycja?.nazwa_pozycji?.toLowerCase().includes(pozycja.toLowerCase()) : true;
      const matchesObszar = obszar ? zawodnik.pozycja?.obszar_pozycji?.toLowerCase().includes(obszar.toLowerCase()) : true;
      
      const matchesRegion = region ? zawodnik.krajPochodzenia.some((kraj) => 
        kraj.region?.toLowerCase().includes(region.toLowerCase())) : true;

      const matchesKraj = kraj ? zawodnik.krajPochodzenia.some((krajPochodzenia) => 
        krajPochodzenia.nazwa?.toLowerCase().includes(kraj.toLowerCase())) : true;
      
      const matchesImie = imie ? zawodnik.imie?.toLowerCase().includes(imie.toLowerCase()) : true;
      const matchesNazwisko = nazwisko ? zawodnik.nazwisko?.toLowerCase().includes(nazwisko.toLowerCase()) : true;
      return matchesPozycja && matchesObszar && matchesRegion && matchesKraj && matchesImie && matchesNazwisko;
    });
  };

  const getCurrentPageZawodnicy = () => {
    const filteredZawodnicy = filterZawodnicy();
    const startIndex = (currentPage - 1) * zawodnicyPerPage;
    return filteredZawodnicy.slice(startIndex, startIndex + zawodnicyPerPage);
  };

  const totalPages = Math.ceil(filterZawodnicy().length / zawodnicyPerPage);

  const handlePageChange = (newPage) => {
    if (newPage >= 1 && newPage <= totalPages) {
      setCurrentPage(newPage);
    }
  };

  return (
    <div className="allMain">
      
      <Navbar/>

      <h1>Lista Zawodników</h1>
      
      <div className="filters-container">
        <input type="text" name="imie" placeholder="Imię" onChange={handleInputChange} />
        <input type="text" name="nazwisko" placeholder="Nazwisko" onChange={handleInputChange} />
        <select name="obszar" onChange={handleInputChange}>
          <option value="">Wybierz obszar</option>
          {[...new Set(pozycje?.map((pozycja) => pozycja.obszar_pozycji))].map((obszar, index) => (
            <option key={index} value={obszar}>{obszar}</option>
          ))}
        </select>
        <select name="pozycja" onChange={handleInputChange}>
          <option value="">Wybierz pozycję</option>
          {pozycje?.map((pozycja) => (
            <option key={pozycja.id_Pozycja} value={pozycja.nazwa_pozycji}>{pozycja.nazwa_pozycji}</option>
          ))}
        </select>
        <select name="region" onChange={handleInputChange}>
          <option value="">Wybierz region</option>
          {[...new Set(kraje?.map((kraj) => kraj.region))].map((region, index) => (
            <option key={index} value={region}>{region}</option>
          ))}
        </select>
        <select name="kraj" onChange={handleInputChange}>
          <option value="">Wybierz kraj</option>
          {kraje?.map((kraj) => (
            <option key={kraj.id_Kraj} value={kraj.nazwa}>{kraj.nazwa}</option>
          ))}
        </select>
      </div>

      {getCurrentPageZawodnicy().length > 0 ? (
        <ul>
          {getCurrentPageZawodnicy().map((zawodnik) => (
          <li key={zawodnik.id} className="user-filter-container">
          <div className="user-filter-info">
            <p><strong>Imię:</strong> {zawodnik.imie}</p>
            <p><strong>Nazwisko:</strong> {zawodnik.nazwisko}</p>
            <p><strong>Obszar:</strong> {zawodnik.pozycja?.obszar_pozycji}</p>
            <p><strong>Pozycja:</strong> {zawodnik.pozycja?.nazwa_pozycji}</p>
            <p><strong>Kraj:</strong> {zawodnik.krajPochodzenia.map((kraj) => kraj.nazwa).join(', ')}</p>
          </div>
          <button className="user-filter-button" onClick={() => navigate(`/zawodnicy/profil/${zawodnik.id}`)}>Zobacz szczegóły</button>
        </li>
      ))}
    </ul>
      ) : (
        <p>Brak zawodników spełniających kryteria wyszukiwania.</p>
      )}

      <div className="pagination">
        <button className="pagination-button" onClick={() => handlePageChange(currentPage - 1)} disabled={currentPage === 1}>
          Poprzednia
        </button>
        <span className="pagination-info">Strona {currentPage} z {totalPages}</span>
        <button className="pagination-button" onClick={() => handlePageChange(currentPage + 1)} disabled={currentPage === totalPages}>
          Następna
        </button>
      </div>

    </div>
  );
};

export default WyswietlanieZawodnikow;
