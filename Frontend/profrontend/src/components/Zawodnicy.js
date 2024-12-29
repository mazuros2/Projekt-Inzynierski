import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { Link } from 'react-router-dom';
import '../cssFolder/Navbar.css'; 
import '../cssFolder/ZawodnicyiTrenerzyFilter.css';

const WyswietlanieZawodnikow = () => {
  const navigate = useNavigate();
  const [showSettings, setShowSettings] = useState(false);  
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
  
  const toggleSettings = () => {
    setShowSettings(!showSettings);
  };
  const goToUserProfile = () => {
    navigate('/user-profile');
  };
  
  const handleLogout = () => {
    localStorage.removeItem('token'); 
    sessionStorage.removeItem('token'); 
    navigate('/logowanie'); 
  };

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
      
      {/* Pasek nawigacyjny */}
            <div className="navbar">
              <Link to="/">
                <img
                  src="https://lh3.googleusercontent.com/proxy/4C4zlh5y6xvZC7MWNsG_99nE1x8yqQnSczaCD2cUy4xlvPOQFcm5vLMoEhrcczwjBcfADm4La8Li__oU9Gzy1Whmwpj1U0BvwG6FlMpj6y7cQuI4IfftojBNTeKQocivQu7lbKfiKvXW30jdeizyGN6AHdIUSpc7mWw1"
                  alt="Logo"
                  className="navbar-logo"
                />
              </Link>
              <div className="icons-container">
                <img
                  src="https://icons.veryicon.com/png/o/miscellaneous/iview30-ios-style/ios-menu-4.png"
                  alt="Ustawienia"
                  className="settings-icon"
                  onClick={toggleSettings}
                />
                <img
                  src="https://www.pikpng.com/pngl/b/259-2599075_gear-user-account-person-configure-control-comments-security.png"
                  alt="Użytkownik"
                  className="user-icon"
                  onClick={goToUserProfile}
                />
                {showSettings && (
                  <div className="settings-menu">
                    <ul>
                      <li onClick={() => navigate("/ligii")}>Ligii</li>
                      <li>Kluby</li>
                      <li onClick={() => navigate('/zawodnicy')}>Zawodnicy</li>
                      <li onClick={() => navigate('/trenerzy')}>Trenerzy</li>
                      <li onClick={() => navigate("/listaObserwowanych")}>Lista obserwowanych</li>
                      <li onClick={handleLogout}>Wyloguj</li>
                    </ul>
                  </div>
                )}
              </div>
            </div>
      
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
