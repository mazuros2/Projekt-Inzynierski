import React, { useEffect, useState } from 'react';
import axios from 'axios';
import jwtDecode from 'jwt-decode';
import { Link, useNavigate } from 'react-router-dom';
import '../cssFolder/Navbar.css';
import '../cssFolder/UserTransfers.css';

const UserTransfers = () => {
  const [userTransfers, setUserTransfers] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);
  const [showSettings, setShowSettings] = useState(false);
  const navigate = useNavigate();

  const getUserIdFromToken = () => {
    const token = sessionStorage.getItem('token');
    if (!token) {
      console.error('Brak tokena w sessionStorage.');
      return null;
    }
  
    try {
      const decodedToken = jwtDecode(token);
      console.log('Token JWT:', decodedToken);  // Sprawdź, co zwraca jwtDecode
      return decodedToken.userId;  // Upewnij się, że userId istnieje
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

  const fetchUserTransfers = async (userId) => {
    try {
      const token = sessionStorage.getItem('token');
      const response = await axios.get(`http://localhost:8080/zawodnik/${userId}/transfery`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setUserTransfers(response.data);
    } catch (err) {
      console.error('Błąd pobierania transferów:', err);
      setError('Nie udało się pobrać transferów użytkownika.');
    } finally {
      setLoading(false);
    }
  };
  const userId1 = getUserIdFromToken();
  const handleAcceptTransfer = async (transferId, clubFromId, clubToId) => {
    try {
      const userId = getUserIdFromToken();
      if (!userId) {
        console.error('Brak userId. Token może być nieprawidłowy lub wygasł.');
        alert('Wystąpił błąd: Brak ID użytkownika.');
        return;
      }
  
      const token = sessionStorage.getItem('token');
  
      const params = new URLSearchParams();
      params.append('id_transfer', transferId);
      params.append('id_uzytkownik', userId1);
      params.append('id_klubOd', clubFromId);
      params.append('id_klubDo', clubToId);
  
      console.log('Wysyłane parametry:', params.toString());
  
      const response = await axios.post(
        'http://localhost:8080/zaakceptuj',
        params,
        {
          headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/x-www-form-urlencoded',
          },
        }
      );
      alert(response.data);
      fetchUserTransfers(userId);
    } catch (err) {
      console.error('Błąd akceptacji transferu:', err);
      alert('Nie udało się zaakceptować transferu.');
    }
  };
  
  
  

  const handleRejectTransfer = async (transferId) => {
    try {
      const token = sessionStorage.getItem('token');
      await axios.post(
        `http://localhost:8080/odrzuc/${transferId}`,
        {}, // Pusty obiekt dla POST
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setUserTransfers((prevTransfers) =>
        prevTransfers.map((transfer) =>
          transfer.id === transferId
            ? { ...transfer, status: "odrzucony" }
            : transfer
        )
      );
      alert("Transfer został odrzucony.");
    } catch (error) {
      console.error("Błąd odrzucania transferu:", error.response || error.message);
      alert(`Nie udało się odrzucić transferu: ${error.message}`);
    }
  };
  

  useEffect(() => {
    const userId = getUserIdFromToken();

    if (!userId) {
      console.error('Brak ID użytkownika w tokenie. Przekierowanie do logowania.');
      navigate('/logowanie');
      return;
    }

    fetchUserTransfers(userId);
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
                <li onClick={() => navigate('/ligii')}>Ligii</li>
                <li onClick={() => navigate('/kluby')}>Kluby</li>
                <li onClick={() => navigate('/zawodnicy')}>Zawodnicy</li>
                <li onClick={() => navigate('/trenerzy')}>Trenerzy</li>
                <li onClick={() => navigate('/listaObserwowanych')}>Lista obserwowanych</li>
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
                <p><strong>Klub:</strong> {transfer.nazwa_klubu}</p>
                {transfer.status === 'oczekujacy' && (
                  <div className="transfer-actions">
                    <button
                      onClick={() =>
                        handleAcceptTransfer(
                          transfer.id,
                          transfer.id_klub_od,
                          transfer.id_klub_do
                        )
                      }
                    >
                      Zaakceptuj
                    </button>
                    <button onClick={() => handleRejectTransfer(transfer.id)}>Odrzuć</button>
                  </div>
                )}
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
