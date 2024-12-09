import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import '../cssFolder/Klub.css'; // Zaktualizuj ścieżkę do pliku CSS, jeśli potrzeba
import '../cssFolder/Navbar.css'; // Współdzielony plik CSS dla paska nawigacyjnego

const Klub = () => {
  const { id } = useParams();
  const [klub, setKlub] = useState(null);
  const [zawodnicy, setZawodnicy] = useState([]); 
  const [error, setError] = useState("");
  const [showSettings, setShowSettings] = useState(false);
  const navigate = useNavigate(); // Hook do przekierowania

  const toggleSettings = () => {
    setShowSettings(!showSettings);
  };

  const goToUserProfile = () => {
    navigate('/user-profile');
  };

  const goBackToKluby = () => {
    navigate('/'); // Funkcja do powrotu na stronę z wszystkimi klubami
  };

  useEffect(() => {
    const token = sessionStorage.getItem("token"); // Pobranie tokena z sessionStorage

    if (!token) {
      setError("Brak tokena. Zaloguj się ponownie.");
      return;
    }

    axios
      .get(`http://localhost:8080/klub/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`, // Przekazanie tokena w nagłówku
        },
      })
      .then((response) => {
        setKlub(response.data);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
        setError("Błąd podczas pobierania danych klubu. Sprawdź uprawnienia.");
      });
  

      axios
      .get(`http://localhost:8080/klub/${id}/zawodnicy`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        setZawodnicy(response.data);
      })
      .catch((error) => {
        console.error("Error fetching zawodnicy:", error);
        setError("Błąd podczas pobierania zawodników. Sprawdź uprawnienia.");
      });
      }, [id]);

  if (error) {
    return <p className="error-message">{error}</p>; // Wyświetlenie błędu, jeśli wystąpi
  }

  if (!klub) {
    return <p>Brak danych</p>;
  }

  return (
    <div className="klub-container">
      {/* Pasek nawigacyjny */}
      <div className="navbar">
        <img 
          src="https://lh3.googleusercontent.com/proxy/4C4zlh5y6xvZC7MWNsG_99nE1x8yqQnSczaCD2cUy4xlvPOQFcm5vLMoEhrcczwjBcfADm4La8Li__oU9Gzy1Whmwpj1U0BvwG6FlMpj6y7cQuI4IfftojBNTeKQocivQu7lbKfiKvXW30jdeizyGN6AHdIUSpc7mWw1" 
          alt="Logo" 
          className="navbar-logo" 
        />
        <h1 className="navbar-title"></h1>
        
        <div className="icons-container">
          <img
            src="https://www.pikpng.com/pngl/b/112-1121340_settings-logo-png-white-png-download-setting-icon.png"
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
                <li>Opcja 1</li>
                <li>Opcja 2</li>
                <li>Opcja 3</li>
              </ul>
            </div>
          )}
        </div>
      </div>

      {/* Szczegóły klubu */}
      <h1>Szczegóły Klubu</h1>
      {/* Logo klubu pośrodku strony */}
      <div className="klub-logo-container">
        {klub.logo_url ? (
          <img src={klub.logo_url} alt={klub.nazwaKlubu} className="klub-logo-central" />
        ) : (
          <p>Brak logo klubu</p>
        )}
      </div>
      <ul className="klub-details-list">
        <li><strong>ID:</strong> {klub.id || "Brak"}</li>
        <li><strong>Nazwa klubu:</strong> {klub.nazwaKlubu || "Brak"}</li>
        <li><strong>Rok założenia:</strong> {klub.rokZalozenia || "Brak"}</li>
        <li><strong>Obecna liga klubu:</strong> {klub.ligaNazwaLigi || "Brak"}</li>
      </ul>
        <div className='zawodnicy'>
        <h2>Zawodnicy</h2>
        {zawodnicy.length > 0 ? (
          <ul className="zawodnicy-list">
            {zawodnicy.map((zawodnik) => (
              <li key={zawodnik.id}>
                <strong>{zawodnik.imie} {zawodnik.nazwisko}</strong> - {zawodnik.pozycja} 
              </li>
            ))}
          </ul>
        ) : (
          <p>Brak zawodników w tym klubie</p>
        )}
        </div>

      {/* Przycisk powrotu */}
      <div className="back-button-container">
        <button onClick={goBackToKluby} className="back-button">Powrót do wszystkich klubów</button>
      </div>
    </div>
  );
};

export default Klub;
