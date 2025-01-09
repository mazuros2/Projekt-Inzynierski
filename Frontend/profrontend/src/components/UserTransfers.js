import React, { useEffect, useState } from 'react';
import axios from 'axios';
import jwtDecode from 'jwt-decode';
import { useNavigate } from 'react-router-dom';
import '../cssFolder/Navbar.css';
import '../cssFolder/UserTransfers.css';
import Navbar from '../components/Navbar';

const UserTransfers = () => {
  const [userTransfers, setUserTransfers] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);
  const [role, setRole] = useState(null);
  const navigate = useNavigate();

  const getUserInfoFromToken = () => {
    const token = sessionStorage.getItem('token');
    if (!token) {
      console.error('Brak tokena w sessionStorage.');
      return {};
    }

    try {
      const decodedToken = jwtDecode(token);
      console.log('Token JWT:', decodedToken); // Debugowanie tokena
      return {
        userId: decodedToken.userId || null,
        role: decodedToken.role || null,
      };
    } catch (err) {
      console.error('Błąd dekodowania tokena:', err);
      return {};
    }
  };

  const fetchUserTransfers = async (userId, role) => {
    try {
      const token = sessionStorage.getItem('token');
      const endpoint =
        role === 'ROLE_MENADZER_KLUBU'
          ? `http://localhost:8080/${userId}/transfery`
          : `http://localhost:8080/zawodnik/${userId}/transfery`;

      const response = await axios.get(endpoint, {
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

  const handleAcceptTransfer = async (transferId, clubFromId, clubToId) => {
    try {
      const { userId } = getUserInfoFromToken();
      if (!userId) {
        console.error('Brak userId. Token może być nieprawidłowy lub wygasł.');
        alert('Wystąpił błąd: Brak ID użytkownika.');
        return;
      }

      const token = sessionStorage.getItem('token');

      const params = new URLSearchParams();
      params.append('id_transfer', transferId);
      params.append('id_uzytkownik', userId);
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
      fetchUserTransfers(userId, role);
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
            ? { ...transfer, status: 'odrzucony' }
            : transfer
        )
      );
      alert('Transfer został odrzucony.');
    } catch (error) {
      console.error('Błąd odrzucania transferu:', error.response || error.message);
      alert(`Nie udało się odrzucić transferu: ${error.message}`);
    }
  };

  useEffect(() => {
    const { userId, role } = getUserInfoFromToken();

    if (!userId) {
      console.error('Brak ID użytkownika w tokenie. Przekierowanie do logowania.');
      navigate('/logowanie');
      return;
    }

    setRole(role); // Ustaw rolę użytkownika
    fetchUserTransfers(userId, role);
  }, [navigate]);

  return (
    <div>
      <Navbar />

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
    <p>
      <strong>Data:</strong> {transfer.data_transferu}
    </p>
    <p>
      <strong>Status:</strong>{" "}
      {role === "ROLE_MENADZER_KLUBU" && transfer.status === "oczekujacy"
        ? "Propozycja transferu została wysłana"
        : transfer.status}
    </p>
    <p>
      <strong>Kwota:</strong> {transfer.kwota} PLN
    </p>
    <p>
      <strong>Klub:</strong> {transfer.nazwa_klubu}
    </p>
    {transfer.status === "oczekujacy" && role !== "ROLE_MENADZER_KLUBU" && (
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
        <button onClick={() => handleRejectTransfer(transfer.id)}>
          Odrzuć
        </button>
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
