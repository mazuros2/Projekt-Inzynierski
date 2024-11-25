import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import '../cssFolder/WszystkieKluby.css';
import '../cssFolder/Navbar.css'; 

const WszystkieKluby = () => {
  const [kluby, setKluby] = useState([]);
  const [showSettings, setShowSettings] = useState(false);
  const navigate = useNavigate(); // Hook do przekierowania

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
      .get('http://localhost:8080/kluby', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setKluby(response.data);
      })
      .catch((error) => {
        console.error('Error fetching data:', error);
        if (error.response) {
          console.error('Status:', error.response.status);
          console.error('Data:', error.response.data);
        }
        if (error.response && error.response.status === 401) {
          sessionStorage.removeItem('token');
          navigate('/login');
        } else if (error.response && error.response.status === 403) {
          console.error('Brak uprawnień do zasobu.');
        }
      });
  }, [navigate]);

  return (
    <div className="kluby-container">
      {/* Pasek nawigacyjny */}
      <div className="navbar">
        <img src="https://lh3.googleusercontent.com/proxy/4C4zlh5y6xvZC7MWNsG_99nE1x8yqQnSczaCD2cUy4xlvPOQFcm5vLMoEhrcczwjBcfADm4La8Li__oU9Gzy1Whmwpj1U0BvwG6FlMpj6y7cQuI4IfftojBNTeKQocivQu7lbKfiKvXW30jdeizyGN6AHdIUSpc7mWw1" alt="Logo" className="navbar-logo" />
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

      {/* Zawartość strony */}
      <h1>Wszystkie Kluby</h1>
      {kluby.length === 0 ? (
          <p>Brak danych</p>
      ) : (
          <ul className="kluby-list">
            {kluby.map((klub, index) => (
                <li key={index} className="klub-item">
                  <div className="klub-info">
                    <span className="klub-logo">
                      <img src={klub.logo_url} alt={klub.nazwaKlubu} />
                    </span>
                    <div className="klub-details">
                      <strong>
                        <Link to={`/klub/${klub.id}`}>
                          {klub.nazwaKlubu || "Brak"}
                        </Link>
                      </strong><br />
                      Rok założenia: {klub.rokZalozenia || "Brak"}<br />
                      Liga: {klub.ligaNazwaLigi || "Brak"}
                    </div>
                  </div>
                </li>
            ))}
          </ul>
      )}
    </div>
  );
};

export default WszystkieKluby;
