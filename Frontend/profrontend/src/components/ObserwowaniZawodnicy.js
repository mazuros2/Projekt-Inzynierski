import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import jwtDecode from 'jwt-decode';
import '../cssFolder/ObserwowaniZawodnicy.css';

const ListaObserwowanych = () => {
  const [zawodnicy, setZawodnicy] = useState([]);
  const [error, setError] = useState("");
  const [userRole, setUserRole] = useState(null);
  const [showSettings, setShowSettings] = useState(false);
  const navigate = useNavigate();

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

  const getUserRole = () => {
    const token = sessionStorage.getItem("token");
    if (!token) return null;

    const decoded = jwtDecode(token);
    return decoded.role;
  };


  useEffect(() => {
    const role = getUserRole();
    setUserRole(role);

    const token = sessionStorage.getItem("token");
    if (!token) {
      setError("Brak tokena. Zaloguj się ponownie.");
      return;
    }

    const endpoint =
      role === "ROLE_MENADZER_KLUBU"
        ? "http://localhost:8080/api/skautingZawodnika/menadzer/listaZawodnikow"
        : "http://localhost:8080/api/skautingZawodnika/skaut/listaZawodnikow";

    axios
      .get(endpoint, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setZawodnicy(response.data);
      })
      .catch((error) => {
        console.error("Błąd podczas pobierania listy zawodników:", error);
        setError("Błąd podczas pobierania listy zawodników.");
      });
  }, []);

  const handleUsunClick = (idZawodnika) => {
    const token = sessionStorage.getItem("token");
    if (!token) {
      setError("Brak tokena. Zaloguj się ponownie.");
      return;
    }
  
    const endpoint =
      userRole === "ROLE_MENADZER_KLUBU"
        ? `http://localhost:8080/api/skautingZawodnika/menadzer/usunZawodnika/${idZawodnika}`
        : `http://localhost:8080/api/skautingZawodnika/skaut/usunZawodnika/${idZawodnika}`;
  
    axios
      .delete(endpoint, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then(() => {
        alert("Zawodnik został usunięty z obserwowanych.");
        setZawodnicy(zawodnicy.filter((zawodnik) => zawodnik.id !== idZawodnika));
      })
      .catch((error) => {
        console.error("Błąd podczas usuwania zawodnika z obserwowanych:", error);
        alert("Nie udało się usunąć zawodnika z obserwowanych.");
      });
  };
  


  if (error) {
    return <p className="error-message">{error}</p>;
  }

  return (
    <div>
      {/* Pasek nawigacyjny */}
      <div className="navbar">
        <Link to="/">
          <img
            src="https://lh3.googleusercontent.com/proxy/4C4zlh5y6xvZC7MWNsG_99nE1x8yqQnSczaCD2cUy4xlvPOQFcm5vLMoEhrcczwjBcfADm4La8Li__oU9Gzy1Whmwpj1U0BvwG6FlMpj6y7cQuI4IfftojBNTeKQocivQu7lbKfiKvXW30jdeizyGN6AHdIUSpc7mWw1"
            alt="Logo"
            className="navbar-logo"
          />
        </Link>
        <h1 className="navbar-title"></h1>
        
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
                <li onClick={() => navigate("/kluby")}>Kluby</li>
                <li onClick={() => navigate('/zawodnicy')}>Zawodnicy</li>
                <li onClick={() => navigate("/trenerzy")}>Trenerzy</li>
                <li onClick={() => navigate("/listaObserwowanych")}>Lista obserwowanych</li>
                <li onClick={handleLogout}>Wyloguj</li>
              </ul>
            </div>
          )}
        </div>
      </div>


    
      <h1>Lista Obserwowanych Zawodników</h1>
      {zawodnicy.length === 0 ? (
        <p>Brak obserwowanych zawodników.</p>
      ) : (
        <ul>
          {zawodnicy.map((zawodnik) => (
            <li key={zawodnik.id} className="zawodnicy-obs-container">
              <div className="zawodnicy-obs-info">
              <p>
                <strong>Imię:</strong> {zawodnik.imie} <br />
                <strong>Nazwisko:</strong> {zawodnik.nazwisko} <br />
                <strong>Pozycja:</strong> {zawodnik.pozycja.nazwa_pozycji} <br />
                <strong>Obecny klub:</strong> {zawodnik.obecnyKlub} <br />
                <strong>Kraj pochodzenia: </strong>{zawodnik.krajPochodzenia.map((kraj) => kraj.nazwa).join(', ')}
              </p>
              </div>
              <div className="zawodnicy-obs-buttons">
                <button className="obs-button" onClick={() => navigate(`/zawodnicy/profil/${zawodnik.id}`)}>Profil Zawodnika</button>
                <button className="obs-button" onClick={() => handleUsunClick(zawodnik.id)}>Usuń z obserwowanych</button>
              </div>
            </li>
          ))}
        </ul>
      )}
    </div>
    
  );
};

export default ListaObserwowanych;
