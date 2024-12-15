import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams, useNavigate, Link } from 'react-router-dom';


const TrenerDetails = () => {
  const { id } = useParams();
  const [trener, setTrener] = useState(null);
  const [showSettings, setShowSettings] = useState(false);
  const navigate = useNavigate();

  const toggleSettings = () => {
    setShowSettings(!showSettings);
  };

  const goToUserProfile = () => {
    navigate('/user-profile');
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
                <li >Kluby</li>
                <li onClick={() => navigate('/zawodnicy')}>Zawodnicy</li>
                <li onClick={() => navigate('/trenerzy')}>Trenerzy</li>
                <li>Lista obserwowanych</li>
              </ul>
            </div>
          )}
        </div>
      </div>
      
      <h1>Profil Trenera</h1>
      <p><strong>Imię:</strong> {trener.imie}</p>
      <p><strong>Nazwisko:</strong> {trener.nazwisko}</p>
      <p><strong>Data urodzenia:</strong> {trener.dataUrodzenia}</p>
      <p><strong>Kraje pochodzenia:</strong> {trener.krajePochodzenia.join(', ')}</p>
      <p><strong>Licencja:</strong> {trener.licencjaTrenera}</p>
      <p><strong>Klub:</strong> {trener.klub}</p>
      <button onClick={() => navigate(-1)}>Wróć</button>
    </div>
  );
};

export default TrenerDetails;
