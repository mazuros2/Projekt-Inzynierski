import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import '../cssFolder/ZawodnikDetails.css'; 
import Navbar from '../components/Navbar';
import jwtDecode from 'jwt-decode';
import '../cssFolder/Navbar.css'; 

const ZawodnikDetails = () => {
  const { id } = useParams();
  const [zawodnik, setZawodnik] = useState(null);
  const [error, setError] = useState("");
  const [isObserved, setIsObserved] = useState(false);
  const [userRole, setUserRole] = useState(null);
  const navigate = useNavigate();

  const handleTransferClick = () => {
    navigate(`/zawodnicy/${id}/transfer`);
  };

  const getUserRole = () => {
    const token = sessionStorage.getItem("token");
    if (!token) return null;

    const decoded = jwtDecode(token);
    return decoded.role; 
  };

  useEffect(() => {
    const role = getUserRole();
    setUserRole(role);

    const token = sessionStorage.getItem("token");

    if (!token) {
      setError("Brak tokena. Zaloguj się ponownie.");
      return;
    }

    axios
      .get(`http://localhost:8080/zawodnicy/profil/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setZawodnik(response.data);
      })
      .catch((error) => {
        console.error("Error fetching zawodnik details:", error);
        setError("Błąd podczas pobierania danych zawodnika.");
      });
  }, [id]);

  const handleObserwujClick = () => {
    const token = sessionStorage.getItem("token");
  
    if (!token) {
      setError("Brak tokena. Zaloguj się ponownie.");
      return;
    }
  
    const endpoint = 
      userRole === "ROLE_MENADZER_KLUBU"
        ? `http://localhost:8080/api/skautingZawodnika/menadzer/dodajZawodnika/${id}`
        : `http://localhost:8080/api/skautingZawodnika/skaut/dodajZawodnika/${id}`;

    axios
      .post(endpoint, null, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then(() => {
        setIsObserved(true);
        alert(`Zawodnik został dodany do obserwowanych przez ${userRole === "ROLE_MENADZER_KLUBU" ? "menedżera" : "skauta"}.`);
      })
      .catch((error) => {
        console.error("Błąd podczas dodawania zawodnika do obserwowanych:", error);
        alert(`Nie udało się dodać zawodnika do obserwowanych przez ${userRole === "ROLE_MENADZER_KLUBU" ? "menedżera" : "skauta"}.`);
      });
  };

  if (error) {
    return <p className="error-message">{error}</p>;
  }

  if (!zawodnik) {
    return <p>Ładowanie danych zawodnika...</p>;
  }

  return (
    <div>

      <Navbar/>
      
      <h1>Szczegóły zawodnika</h1>
      
      <div className ="profilowe-info">
      <div className="user-profilowe">
        {zawodnik.profiloweURL ? (
          <img src={zawodnik.profiloweURL} />
        ) : (
          <p>Brak profilowego </p>
        )}
      </div>
      <div className ="info">
        <p><strong>Imię:</strong> {zawodnik.imie}</p>
        <p><strong>Nazwisko:</strong> {zawodnik.nazwisko}</p>
        <p><strong>Pozycja:</strong> {zawodnik.pozycja}</p>
        <p><strong>Obecny klub:</strong> {zawodnik.obecnyKlub}</p>
        <p><strong>Kraj:</strong> {zawodnik.krajePochodzenia}</p>
        <p><strong>Data urodzenia:</strong> {zawodnik.dataUrodzenia}</p>
        <p><strong>Wzrost:</strong> {zawodnik.wzrost}</p>
        <p><strong>Waga:</strong> {zawodnik.waga}</p>
      </div>
      </div>
  
      <div className='zawodnik-details-buttons'>
        <button onClick={() => navigate(-1)}> Wróć </button>
        <button onClick={handleTransferClick}> Wyślij transfer </button>
        <button onClick={handleObserwujClick} disabled={isObserved}>
        {isObserved ? "Obserwujesz" : `Obserwuj jako ${userRole === "ROLE_MENADZER_KLUBU" ? "menedżer" : "skaut"}`}</button>
      </div>

  </div>
   
  );
};

export default ZawodnikDetails;
