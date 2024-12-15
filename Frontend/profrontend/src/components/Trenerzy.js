import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';


const Trenerzy = () => {
  const [trenerzy, setTrenerzy] = useState([]);
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

  useEffect(() => {
    const token = sessionStorage.getItem('token');
    if (!token) {
      console.error('Brak tokena. Przekierowanie do logowania.');
      navigate('/logowanie');
      return;
    }

    axios
      .get('http://localhost:8080/trenerzy', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setTrenerzy(response.data);
      })
      .catch((error) => {
        console.error('Error fetching trainers:', error);
      });
  }, [navigate]);

  return (
    <div className="trenerzy-container">
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
                <li onClick={handleLogout}>Wyloguj</li>
              </ul>
            </div>
          )}
        </div>
      </div>

      <h1>Lista Trenerów</h1>
      {trenerzy.length === 0 ? (
        <p>Brak danych</p>
      ) : (
        <ul className="trenerzy-list">
          {trenerzy.map((trener) => (
            <li key={trener.id} className="trener-item">
              <Link to={`/trener/profil/${trener.id}`} className="trener-name">
                {trener.imie} {trener.nazwisko}
              </Link>
              <p><strong>Licencja:</strong> {trener.licencjaTrenera || 'Brak danych'}</p>
              <p><strong>Klub:</strong> {trener.klub || 'Brak klubu'}</p>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default Trenerzy;
