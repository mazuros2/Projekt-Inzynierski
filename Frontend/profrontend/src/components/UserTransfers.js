import React, { useEffect, useState } from 'react';
import axios from 'axios';
import jwtDecode from 'jwt-decode';
import { Link, useNavigate } from 'react-router-dom';
import '../cssFolder/Navbar.css'; 
import '../cssFolder/UserTransfers.css'; // Opcjonalny styl dla transferów

const UserTransfers = () => {
  const [userTransfers, setUserTransfers] = useState([]); // Transfery użytkownika
  const [error, setError] = useState(null); // Błędy w ładowaniu
  const [loading, setLoading] = useState(true); // Stan ładowania
  const [showSettings, setShowSettings] = useState(false);
  const navigate = useNavigate();



  // Funkcja do uzyskania ID użytkownika z tokena
  const getUserIdFromToken = () => {
    const token = sessionStorage.getItem('token');
    if (!token) return null;

    try {
      const decodedToken = jwtDecode(token);
      return decodedToken.userId; // Zakładam, że ID użytkownika jest w polu `userId` tokena
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
    const userId = getUserIdFromToken(); 
    if (userId) {
      navigate(`/user-profile/${userId}`);
    } else {
      console.error("Nie udało się pobrać ID użytkownika z tokena.");
    }
  };



  // Pobieranie transferów użytkownika
  const fetchUserTransfers = async (userId) => {
    try {
      const token = sessionStorage.getItem('token');
      const response = await axios.get(`http://localhost:8080/zawodnik/${userId}/transfery`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setUserTransfers(response.data); // Ustaw transfery w stanie
    } catch (err) {
      console.error('Błąd pobierania transferów:', err);
      setError('Nie udało się pobrać transferów użytkownika.');
    } finally {
      setLoading(false); // Wyłącz stan ładowania
    }
  };

  useEffect(() => {
    const userId = getUserIdFromToken();

    if (!userId) {
      console.error('Brak ID użytkownika w tokenie. Przekierowanie do logowania.');
      navigate('/logowanie');
      return;
    }

    fetchUserTransfers(userId); // Pobierz transfery
  }, [navigate]);

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
    
    
    <div className="user-transfers">
      <h1>Twoje transfery</h1>
      {loading ? (
        <p>Ładowanie transferów...</p>
      ) : error ? (
        <p className="error-message">{error}</p>
      ) : userTransfers.length > 0 ? (
        <ul className="transfer-list">
          {userTransfers.map((transfer) => (
            <li key={transfer.id} className="transfer-item">
              <p><strong>Data:</strong> {transfer.data_transferu}</p>
              <p><strong>Status:</strong> {transfer.status}</p>
              <p><strong>Kwota:</strong> {transfer.kwota} PLN</p>
            </li>
          ))}
        </ul>
      ) : (
        <p>Brak transferów do wyświetlenia.</p>
      )}
    </div>
    </div>
  );
};

export default UserTransfers;
