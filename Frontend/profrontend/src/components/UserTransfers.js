import React, { useEffect, useState } from 'react';
import axios from 'axios';
import jwtDecode from 'jwt-decode';
import { useNavigate } from 'react-router-dom';
import '../cssFolder/UserTransfers.css'; // Opcjonalny styl dla transferów

const UserTransfers = () => {
  const [userTransfers, setUserTransfers] = useState([]); // Transfery użytkownika
  const [error, setError] = useState(null); // Błędy w ładowaniu
  const [loading, setLoading] = useState(true); // Stan ładowania
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
  );
};

export default UserTransfers;
