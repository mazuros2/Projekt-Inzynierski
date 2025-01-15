import React, { useEffect, useState } from 'react';
import axios from 'axios';
import jwtDecode from 'jwt-decode';
import { useNavigate } from 'react-router-dom';
import '../cssFolder/Navbar.css';
import '../cssFolder/UserTransfers.css';
import Navbar from '../components/Navbar';

const UserTransfers = () => {
  const [userTransfers, setUserTransfers] = useState([]);
  const [filteredTransfers, setFilteredTransfers] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);
  const [role, setRole] = useState(null);
  const [currentPage, setCurrentPage] = useState(1);
  const [transfersPerPage] = useState(5); // Liczba transferów na stronę
  const [filterStatus, setFilterStatus] = useState(''); // Stan filtra
  const navigate = useNavigate();

  const getUserInfoFromToken = () => {
    const token = sessionStorage.getItem('token');
    if (!token) {
      console.error('Brak tokena w sessionStorage.');
      return {};
    }

    try {
      const decodedToken = jwtDecode(token);
      console.log('Token JWT:', decodedToken);
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
      setFilteredTransfers(response.data);
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
        {},
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
      setFilteredTransfers((prevTransfers) =>
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

    setRole(role);
    fetchUserTransfers(userId, role);
  }, [navigate]);

  // Obliczanie elementów do wyświetlenia na bieżącej stronie
  const indexOfLastTransfer = currentPage * transfersPerPage;
  const indexOfFirstTransfer = indexOfLastTransfer - transfersPerPage;
  const currentTransfers = filteredTransfers.slice(
    indexOfFirstTransfer,
    indexOfLastTransfer
  );

  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  const handleFilterChange = (event) => {
    const status = event.target.value;
    setFilterStatus(status);

    if (status === '') {
      setFilteredTransfers(userTransfers);
    } else {
      setFilteredTransfers(
        userTransfers.filter((transfer) => transfer.status === status)
      );
    }
    setCurrentPage(1); // Resetowanie strony do pierwszej przy zmianie filtra
  };

  return (
    <div>
      <Navbar />

      <div className="user-transfers">
        <h1>Twoje transfery</h1>

        <div className="filter-container">
          <label htmlFor="filter-status">Filtruj po statusie:</label>
          <select
            id="filter-status"
            value={filterStatus}
            onChange={handleFilterChange}
          >
            <option value="">Wszystkie</option>
            <option value="oczekujacy">Oczekujący</option>
            <option value="zaakceptowany">Zaakceptowany</option>
            <option value="odrzucony">Odrzucony</option>
          </select>
        </div>

        {loading ? (
          <p>Ładowanie transferów...</p>
        ) : error ? (
          <p className="error-message">{error}</p>
        ) : filteredTransfers.length > 0 ? (
          <>
            <ul className="transfer-list">
              {currentTransfers.map((transfer) => (
                <li key={transfer.id} className="transfer-item">
                  <p>
                    <strong>Data:</strong> {transfer.data_transferu}
                  </p>
                  <p>
                    <strong>Status:</strong>{' '}
                    {role === 'ROLE_MENADZER_KLUBU' &&
                    transfer.status === 'oczekujacy'
                      ? 'Propozycja transferu została wysłana'
                      : transfer.status}
                  </p>
                  <p>
                    <strong>Kwota:</strong> {transfer.kwota} PLN
                  </p>
                  <p>
                    <strong>Klub od:</strong> {transfer.nazwa_klub_od}
                  </p>
                  <p>
                    <strong>Klub do:</strong> {transfer.nazwa_klub_do}
                  </p>
                  {role === 'ROLE_MENADZER_KLUBU' ? (
                    <>
                      <p>
                        <strong>Imie zawodnika:</strong> {transfer.imie}
                      </p>
                      <p>
                        <strong>Nazwisko zawodnika:</strong> {transfer.nazwisko}
                      </p>
                    </>
                  ) : (
                    <>
                      <p>
                        <strong>Imie menadzera klubu:</strong> {transfer.imieMen}
                      </p>
                      <p>
                        <strong>Nazwisko menadzera klubu:</strong> {transfer.nazwiskoMen}
                      </p>
                    </>)}
                  {transfer.status === 'oczekujacy' &&
                    role !== 'ROLE_MENADZER_KLUBU' && (
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
                        <button
                          onClick={() => handleRejectTransfer(transfer.id)}
                        >
                          Odrzuć
                        </button>
                      </div>
                    )}
                </li>
              ))}
            </ul>
            <div className="pagination">
              {Array.from(
                { length: Math.ceil(filteredTransfers.length / transfersPerPage) },
                (_, index) => (
                  <button
                    key={index + 1}
                    onClick={() => paginate(index + 1)}
                    className={
                      currentPage === index + 1 ? 'active' : ''
                    }
                  >
                    {index + 1}
                  </button>
                )
              )}
            </div>
          </>
        ) : (
          <p>Brak transferów do wyświetlenia.</p>
        )}
      </div>
    </div>
  );
};

export default UserTransfers;
