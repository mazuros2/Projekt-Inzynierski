import React, { useEffect, useState } from 'react';
import axios from 'axios';
import jwtDecode from 'jwt-decode';
import { Link, useNavigate } from 'react-router-dom';
import '../cssFolder/Navbar.css'; // Stylowanie paska nawigacyjnego
import '../cssFolder/UserProfile.css';

const UserProfile = () => {
  const [userData, setUserData] = useState(null);
  const [showSettings, setShowSettings] = useState(false);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  // Funkcja do uzyskania ID użytkownika z tokena
  const getUserIdFromToken = () => {
    const token = sessionStorage.getItem('token');
    if (!token) return null;

    try {
      const decodedToken = jwtDecode(token);
      return decodedToken.userId;
    } catch (err) {
      console.error('Błąd dekodowania tokena:', err);
      return null;
    }
  };

  const handleLogout = () => {
    sessionStorage.removeItem('token');
    navigate('/logowanie');
  };

  const toggleSettings = () => {
    setShowSettings(!showSettings);
  };

  const goToUserProfile = () => {
    navigate('/user-profile');
  };

  useEffect(() => {
    const userId = getUserIdFromToken();

    if (!userId) {
      console.error('Brak ID użytkownika w tokenie. Przekierowanie do logowania.');
      navigate('/logowanie');
      return;
    }

    // Pobieranie danych użytkownika
    const fetchUserData = async () => {
      try {
        const token = sessionStorage.getItem('token');
        const response = await axios.get(`http://localhost:8080/userDetails/${userId}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setUserData(response.data);
      } catch (err) {
        console.error('Błąd pobierania danych użytkownika:', err);
        setError('Nie udało się pobrać danych użytkownika.');
      }
    };

    fetchUserData();
  }, [navigate]);

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

      {/* Dane profilu użytkownika */}
      <div style={{ padding: '20px' }}>
        
        {error ? (
          <div>{error}</div>
            ) : userData ? (
          <div className="user-profile">
              <div className="user-profilowe">
                {userData.profiloweURL ? (
                  <img src={userData.profiloweURL} />
                  ) : (
                  <p>Brak profilowego </p>
                  )}
              </div>
          <div className="user-info">       
            <p><strong>Imię:</strong> {userData.imie}</p>
            <p><strong>Nazwisko:</strong> {userData.nazwisko}</p>
            <p><strong>Email:</strong> {userData.email}</p>
            <p><strong>Login:</strong> {userData.login}</p>
            <p><strong>PESEL:</strong> {userData.pesel}</p>
            <p><strong>Data Urodzenia:</strong> {userData.data_Urodzenia}</p>
            <p><strong>Rola:</strong> {userData.role}</p>
            </div>
          </div>
        ) : (
          <div>Ładowanie danych...</div>
        )}
        
        <div className = "buttons">
        <button onClick={handleLogout} className="logout-button">Wyloguj</button>
        <button className = "data-button">Zmiana danych</button>
        <button className = "panel-button">Admin panel</button>
        </div>
      
      </div>
    </div>
  );
};

export default UserProfile;
