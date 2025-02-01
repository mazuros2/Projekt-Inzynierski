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
  const transfersPerPage = 5;
  const [filterStatus, setFilterStatus] = useState('');
  const navigate = useNavigate();

  const getUserInfoFromToken = () => {
    const token = sessionStorage.getItem('token');
    if (!token) return {};

    try {
      const decodedToken = jwtDecode(token);
      return { userId: decodedToken.userId || null, role: decodedToken.role || null };
    } catch (err) {
      console.error('Błąd dekodowania tokena:', err);
      return {};
    }
  };

  const fetchUserTransfers = async (userId, role) => {
    setLoading(true);
    try {
      const token = sessionStorage.getItem('token');
      const endpoint = role === 'ROLE_MENADZER_KLUBU' 
        ? `http://localhost:8080/${userId}/transfery` 
        : `http://localhost:8080/zawodnik/${userId}/transfery`;
      
      const response = await axios.get(endpoint, { headers: { Authorization: `Bearer ${token}` } });
      setUserTransfers(response.data || []);
      setFilteredTransfers(response.data || []);
    } catch (err) {
      setError('Nie udało się pobrać transferów użytkownika.');
    } finally {
      setLoading(false);
    }
  };

  const handleAcceptTransfer = async (transferId, clubFromId, clubToId) => {
    try {
      const { userId } = getUserInfoFromToken();
      if (!userId) return alert('Błąd: Brak ID użytkownika.');

      const token = sessionStorage.getItem('token');
      const params = new URLSearchParams({ id_transfer: transferId, id_uzytkownik: userId, id_klubOd: clubFromId, id_klubDo: clubToId });
      
      const response = await axios.post('http://localhost:8080/zaakceptuj', params, {
        headers: { Authorization: `Bearer ${token}`, 'Content-Type': 'application/x-www-form-urlencoded' },
      });

      alert(response.data);
      fetchUserTransfers(userId, role);
    } catch (err) {
      alert('Nie udało się zaakceptować transferu.');
    }
  };

  const handleRejectTransfer = async (transferId) => {
    try {
      const token = sessionStorage.getItem('token');
      await axios.post(`http://localhost:8080/odrzuc/${transferId}`, {}, { headers: { Authorization: `Bearer ${token}` } });
      setFilteredTransfers(filteredTransfers.map(t => (t.id === transferId ? { ...t, status: 'odrzucony' } : t)));
      alert('Transfer został odrzucony.');
    } catch (error) {
      alert(`Nie udało się odrzucić transferu: ${error.message}`);
    }
  };

  useEffect(() => {
    const { userId, role } = getUserInfoFromToken();
    if (!userId) return navigate('/logowanie');
    setRole(role);
    fetchUserTransfers(userId, role);
  }, [navigate]);

  const indexOfLastTransfer = currentPage * transfersPerPage;
  const indexOfFirstTransfer = indexOfLastTransfer - transfersPerPage;
  const currentTransfers = filteredTransfers.slice(indexOfFirstTransfer, indexOfLastTransfer);

  const handleFilterChange = (event) => {
    const status = event.target.value;
    setFilterStatus(status);
    setFilteredTransfers(status ? userTransfers.filter(t => t.status === status) : userTransfers);
    setCurrentPage(1);
  };

  return (
    <div>
      <Navbar />
      <div className="user-transfers">
        <h1>Twoje transfery</h1>
        <div className="filter-container">
          <label htmlFor="filter-status">Filtruj po statusie:</label>
          <select id="filter-status" value={filterStatus} onChange={handleFilterChange}>
            <option value="">Wszystkie</option>
            <option value="oczekujacy">Oczekujący</option>
            <option value="zaakceptowany">Zaakceptowany</option>
            <option value="odrzucony">Odrzucony</option>
          </select>
        </div>

        {loading ? <p>Ładowanie transferów...</p> : error ? <p className="error-message">{error}</p> : filteredTransfers.length > 0 ? (
          <ul className="transfer-list">
            {currentTransfers.map(transfer => (
              <li key={transfer.id} className="transfer-item">
                <p><strong>Data:</strong> {transfer.data_transferu}</p>
                <p><strong>Status:</strong> {transfer.status}</p>
                <p><strong>Kwota:</strong> {transfer.kwota} PLN</p>
                <p><strong>Klub od:</strong> {transfer.nazwa_klub_od}</p>
                <p><strong>Klub do:</strong> {transfer.nazwa_klub_do}</p>
                {transfer.status === 'oczekujacy' && role !== 'ROLE_MENADZER_KLUBU' && (
                  <div className="transfer-actions">
                    <button onClick={() => handleAcceptTransfer(transfer.id, transfer.id_klub_od, transfer.id_klub_do)}>Zaakceptuj</button>
                    <button onClick={() => handleRejectTransfer(transfer.id)}>Odrzuć</button>
                  </div>
                )}
              </li>
            ))}
          </ul>
        ) : <p>Brak transferów do wyświetlenia.</p>}
      </div>
    </div>
  );
};

export default UserTransfers;
