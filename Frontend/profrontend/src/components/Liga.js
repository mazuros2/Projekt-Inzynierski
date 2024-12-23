import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import { Link } from 'react-router-dom';

const Liga = () => {
  const [liga, setLiga] = useState([]);
  const navigate = useNavigate();
  const [showSettings, setShowSettings] = useState(false);

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
      .get("http://localhost:8080/ligii", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setLiga(response.data);
      })
      .catch((error) => {
        console.error("Error fetching clubs:", error);
      });
    }, [navigate]);

    const toggleSettings = () => {
      setShowSettings(!showSettings);
    };
    const goToUserProfile = () => {
      navigate('/user-profile');
    };

  return (
    <div>
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

            <h1>Wszystkie ligii</h1>
      {liga.length === 0 ? (
        <p>Brak danych</p>
      ) : (
        <ul>
          {liga.map((liga) => (
            <li key={liga.id}>
              <Link to={`${liga.id}/kluby`}>
                <strong>Nazwa ligi:</strong> {liga.nazwaLigi || "Brak"}
              </Link>
              <br />
              <strong>Poziom Ligi:</strong> {liga.poziomLigi || "Brak"}
              <br />
            </li>
          ))}
        </ul>
      )}
    </div>
);
}

export default Liga;