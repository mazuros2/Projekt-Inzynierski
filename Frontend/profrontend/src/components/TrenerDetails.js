import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams, useNavigate, Link } from 'react-router-dom';
import jwtDecode from 'jwt-decode';
import '../cssFolder/TrenerDetails.css'

const TrenerDetails = () => {
  const { id } = useParams();
  const [trener, setTrener] = useState(null);
  const [showSettings, setShowSettings] = useState(false);
  const navigate = useNavigate();

  const toggleSettings = () => {
    setShowSettings(!showSettings);
  };

  const getUserIdFromToken = () => {
    const token = sessionStorage.getItem("token");
    if (!token) return null;
  
    try {
      const decodedToken = jwtDecode(token);
      return decodedToken.userId; 
    } catch (error) {
      console.error("Błąd dekodowania tokena:", error);
      return null;
    }
  };

  const goToUserProfile = () => {
    const userId = getUserIdFromToken(); 
    if (userId) {
      navigate(`/user-profile/${userId}`);
    } else {
      console.error("Nie udało się pobrać ID użytkownika z tokena.");
    }
  };

  const handleLogout = () => {
    localStorage.removeItem('token'); 
    sessionStorage.removeItem('token'); 
    navigate('/logowanie'); 
  };
  
  useEffect(() => {
    const token = sessionStorage.getItem('token');
    if (!token) {
      console.error('Brak tokena. Przekierowanie do logowania.');
      navigate('/logowanie');
      return;
    }

    axios
      .get(`http://localhost:8080/trener/profil/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setTrener(response.data);
      })
      .catch((error) => {
        console.error('Error fetching trainer profile:', error);
      });
  }, [id, navigate]);

  if (!trener) {
    return <p>Wczytywanie danych trenera...</p>;
  }

  return (
    <div className="trener-profil">
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
                <li onClick={() => navigate('/ligii')}>Ligii</li>
                <li onClick={() => navigate('/kluby')}>Kluby</li>
                <li onClick={() => navigate('/zawodnicy')}>Zawodnicy</li>
                <li onClick={() => navigate('/trenerzy')}>Trenerzy</li>
                <li onClick={() => navigate("/listaObserwowanych")}>Lista obserwowanych</li>
                <li onClick={handleLogout}>Wyloguj</li>
              </ul>
            </div>
          )}
        </div>
      </div>
      
      <h1>Profil Trenera</h1>
      
      <div className ="profilowe-info">
        <div className="user-profilowe">
          {trener.profiloweURL ? (
            <img src={trener.profiloweURL} />
          ) : (
           <p>Brak profilowego </p>
          )}
        </div>
      
        <div className ="info">
          <p><strong>Imię:</strong> {trener.imie}</p>
          <p><strong>Nazwisko:</strong> {trener.nazwisko}</p>
          <p><strong>Data urodzenia:</strong> {trener.dataUrodzenia}</p>
          <p><strong>Kraje pochodzenia:</strong> {trener.krajePochodzenia.join(', ')}</p>
          <p><strong>Licencja:</strong> {trener.licencjaTrenera}</p>
          <p><strong>Klub:</strong> {trener.klub}</p>
        </div>
      </div>
      

      <div className='back-button-container'>
        <button onClick={() => navigate(-1)} className="back-button">Wróć</button> 
      </div>
      
    </div>
  );
};

export default TrenerDetails;
