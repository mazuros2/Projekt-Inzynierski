import React, { useEffect, useState } from 'react';
import axios from 'axios';
import jwtDecode from 'jwt-decode';
import { useNavigate } from 'react-router-dom';
import { isUserInRole } from "../service/authService.js";
import Navbar from '../components/Navbar';
import '../cssFolder/Navbar.css'; 
import '../cssFolder/UserProfile.css';

const UserProfile = () => {
  const [userData, setUserData] = useState(null);
  const [error, setError] = useState(null);
  const navigate = useNavigate();


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

  const goToAdminPanel = () => {
    if (isUserInRole(["ROLE_ADMIN", "ROLE_MENADZER_KLUBU"])) {
      navigate("/adminPanel");
    } else {
      alert("Nie masz uprawnień do tego panelu!");
    }
  };

  const goToZmianaDanych = () => {
    navigate('/user-profile/zmianaDanych');
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
        const response = await axios.get(`${process.env.REACT_APP_API_URL}/userDetails/${userId}`, {
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
      
      <Navbar/>

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
        <button onClick={goToZmianaDanych} className = "data-button">Zmiana danych</button>
        {isUserInRole(['ROLE_MENADZER_KLUBU','ROLE_ADMIN']) && (
        <button onClick={goToAdminPanel} className = "panel-button">Admin panel</button>)}
        </div>
      
      </div>
    </div>
  );
};

export default UserProfile;
