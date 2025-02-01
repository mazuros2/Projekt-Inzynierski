import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import '../cssFolder/ZawodnikDetails.css'; 
import Navbar from '../components/Navbar';
import jwtDecode from 'jwt-decode';
import { isUserInRole } from "../service/authService.js";

const ZawodnikDetails = () => {
  const { id } = useParams();
  const [zawodnik, setZawodnik] = useState(null);
  const [error, setError] = useState("");
  const [isObserved, setIsObserved] = useState(false);
  const [userRole, setUserRole] = useState(null);
  const [userId, setUserId] = useState(null);
  const [transferExists, setTransferExists] = useState(false);
  const [idKlubZawodnika, setIdKlubZawodnika] = useState(null);
  const [idKlubMenadzera, setIdKlubMenadzera] = useState(null);
  const navigate = useNavigate();

  const handleTransferClick = () => {
    navigate(`/zawodnicy/${id}/transfer`);
  };

  const getUserInfo = () => {
    const token = sessionStorage.getItem("token");
    if (!token) return;

    try {
      const decoded = jwtDecode(token);
      setUserRole(decoded.role);
      setUserId(decoded.userId);
    } catch (error) {
      console.error("Błąd dekodowania tokena:", error);
    }
  };

  useEffect(() => {
    getUserInfo();
  }, []);

  useEffect(() => {
    const token = sessionStorage.getItem("token");
    if (!token) {
      setError("Brak tokena. Zaloguj się ponownie.");
      return;
    }

    axios
      .get(`http://localhost:8080/zawodnicy/profil/${id}`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        setZawodnik(response.data);
      })
      .catch((error) => {
        console.error("Error fetching zawodnik details:", error);
        setError("Błąd podczas pobierania danych zawodnika.");
      });

    axios
      .get(`http://localhost:8080/findKlubIdByZawodnik/${id}`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        if (response.data && response.data.id) {
          setIdKlubZawodnika(response.data.id);
        }
      })
      .catch((error) => {
        console.error("Błąd przy pobieraniu klubu zawodnika:", error);
      });

  }, [id]);

  useEffect(() => {
    if (userRole !== "ROLE_MENADZER_KLUBU" || !userId) return;

    const token = sessionStorage.getItem("token");

    axios
      .get(`http://localhost:8080/getIdKlubuMenadzera/${userId}`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        if (response.data && response.data.id) {
          setIdKlubMenadzera(response.data.id);
        }
      })
      .catch((error) => {
        console.error("Błąd przy pobieraniu klubu menedżera:", error);
      });

  }, [userId, userRole]);

  useEffect(() => {
    if (!userId || !id || userRole === 'ROLE_ADMIN') return;

    const token = sessionStorage.getItem("token");

    axios
      .get(`http://localhost:8080/walidacjaTransferu/${userId}/${id}`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        setTransferExists(!!response.data);
      })
      .catch((error) => {
        console.error("Błąd podczas sprawdzania transferu:", error);
        setTransferExists(false);
      });

  }, [userId, id, userRole]);

  if (error) {
    return <p className="error-message">{error}</p>;
  }

  if (!zawodnik) {
    return <p>Ładowanie danych zawodnika...</p>;
  }

  const isSameClub = idKlubZawodnika && idKlubMenadzera && idKlubZawodnika === idKlubMenadzera;

  return (
    <div>
      <Navbar/>
      
      <h1>Szczegóły zawodnika</h1>
      
      <div className="profilowe-info">
        <div className="user-profilowe">
          {zawodnik.profiloweURL ? (
            <img src={zawodnik.profiloweURL} alt="Profilowe" />
          ) : (
            <p>Brak profilowego</p>
          )}
        </div>
        <div className="info">
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

      {isSameClub && (
        <p className="info-message">Ten zawodnik jest już w Twojej drużynie.</p>
      )}

      <div className='zawodnik-details-buttons'>
        <button onClick={() => navigate(-1)}> Wróć </button>

        {!isSameClub && isUserInRole(['ROLE_ADMIN']) && (
          <button onClick={handleTransferClick}> Wyślij transfer </button>
        )}

        {!isSameClub && isUserInRole(['ROLE_MENADZER_KLUBU']) && !transferExists && (
          <button onClick={handleTransferClick}> Wyślij transfer </button>
        )}

        {isUserInRole(['ROLE_ADMIN','ROLE_MENADZER_KLUBU','ROLE_SKAUT']) && (
          <button onClick={() => {}} disabled={isObserved}>
            {isObserved ? "Obserwujesz" : `Obserwuj jako ${userRole === "ROLE_MENADZER_KLUBU" ? "menedżer" : "skaut"}`}
          </button>
        )}
      </div>
    </div>
  );
};

export default ZawodnikDetails;
