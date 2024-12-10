import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import '../cssFolder/ZawodnikDetails.css'; // Plik CSS dla szczegółów zawodnika



const ZawodnikDetails = () => {
  const { id } = useParams();
  const [zawodnik, setZawodnik] = useState(null);
  const [error, setError] = useState("");
  const [showSettings, setShowSettings] = useState(false);
  const navigate = useNavigate(); // Hook do przekierowania


  const goBackToKluby = () => {
    navigate('/'); // Funkcja do powrotu na stronę z wszystkimi klubami
  };

  const toggleSettings = () => {
    setShowSettings(!showSettings);
  };
  const goToUserProfile = () => {
    navigate('/user-profile');
  };
  useEffect(() => {
    const token = sessionStorage.getItem("token");

    if (!token) {
      setError("Brak tokena. Zaloguj się ponownie.");
      return;
    }

    axios
      .get(`http://localhost:8080/zawodnicy/profil/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setZawodnik(response.data);
      })
      .catch((error) => {
        console.error("Error fetching zawodnik details:", error);
        setError("Błąd podczas pobierania danych zawodnika.");
      });
  }, [id]);

  if (error) {
    return <p className="error-message">{error}</p>;
  }

  if (!zawodnik) {
    return <p>Ładowanie danych zawodnika...</p>;
  }

  return (
    <div className="zawodnik-container">
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
    <div className="zawodnik-details-container">
      <h1>Szczegóły zawodnika</h1>
      <ul>
        <li><strong>Imię:</strong> {zawodnik.imie}</li>
        <li><strong>Nazwisko:</strong> {zawodnik.nazwisko}</li>
        <li><strong>Pozycja:</strong> {zawodnik.pozycja}</li>
        <li><strong>Obecny klub:</strong> {zawodnik.obecnyKlub}</li>
        <li><strong>Kraj:</strong> {zawodnik.krajePochodzenia}</li>
        <li><strong>Data urodzenia:</strong> {zawodnik.dataUrodzenia}</li>
        <li><strong>Wzrost:</strong> {zawodnik.wzrost}</li>
        <li><strong>Waga:</strong> {zawodnik.waga}</li>
      </ul>
      {/* Przycisk powrotu */}
      <div className="back-button-container">
        <button onClick={goBackToKluby} className="back-button">Powrót do wszystkich klubów</button>
      </div>
    </div>
    </div>
  );
};

export default ZawodnikDetails;