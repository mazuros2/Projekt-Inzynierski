import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from 'react-router-dom';
import { isUserInRole } from "../service/authService.js";
import Navbar from '../components/Navbar';
import '../cssFolder/Navbar.css';
import '../cssFolder/AdminPanel.css'


const AdminPanel = () => {
  const navigate = useNavigate();
  const [status, setStatus] = useState({ hasTrener: false, hasSkaut: false });
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const token = sessionStorage.getItem("token");

    if (!token) {
      console.error("Brak tokena. Przekierowanie do logowania.");
      navigate("/logowanie");
      return;
    }

    axios
      .get(`${process.env.REACT_APP_API_URL}/api/menadzer/klub/status`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        setStatus(response.data); 
        setLoading(false);
      })
      .catch((error) => {
        console.error("Błąd pobierania statusu klubu:", error);
        setLoading(false);
      });
  }, [navigate]);

    const createZawodnikAdmin = () => {
      if (isUserInRole(["ROLE_ADMIN"])) {
        navigate('/createZawodnik');
      } else {
        alert("Nie masz uprawnień do tego panelu!");
      }
    };  

    const createTrenerAdmin = () => {
      if (isUserInRole(["ROLE_ADMIN"])) {
        navigate('/createTrener');
      } else {
        alert("Nie masz uprawnień do tego panelu!");
      }  
    }; 

    const createSkautAdmin = () => {
      if (isUserInRole(["ROLE_ADMIN"])) {
        navigate('/createSkaut');
      } else {
        alert("Nie masz uprawnień do tego panelu!");
      }  
    }; 

    const createMenadzerAdmin = () => {
      if (isUserInRole(["ROLE_ADMIN"])) {
        navigate('/createMenadzer');
      } else {
        alert("Nie masz uprawnień do tego panelu!");
      }  
    }; 

    const createTrofeum = () => {
      if (isUserInRole(["ROLE_ADMIN"])) {
        navigate('/createTrofeum');
      } else {
        alert("Nie masz uprawnień do tego panelu!");
      }  
    }; 

    const createZawodnikMenadzer = () => {
      if (isUserInRole(["ROLE_MENADZER_KLUBU"])) {
        navigate('/createZawodnikByMenadzer');
      } else {
        alert("Nie masz uprawnień do tego panelu!");
      }  
    }; 
    const createTrenerMenadzer = () => {
      if (isUserInRole(["ROLE_MENADZER_KLUBU"])) {
        navigate('/createTrenerByMenadzer');
      } else {
        alert("Nie masz uprawnień do tego panelu!");
      }  
    }; 
    const createSkautMenadzer = () => {
      if (isUserInRole(["ROLE_MENADZER_KLUBU"])) {
        navigate('/createSkautByMenadzer');
      } else {
        alert("Nie masz uprawnień do tego panelu!");
      }  
    }; 

    const zwolnijSkautaMenadzer = () => {
      if (isUserInRole(["ROLE_MENADZER_KLUBU"])) {
        navigate('/zwolnijSkautaByMenadzer');
      } else {
        alert("Nie masz uprawnień do tego panelu!");
      }  
    }; 

    const zwolnijTreneraMenadzer = () => {
      if (isUserInRole(["ROLE_MENADZER_KLUBU"])) {
        navigate('/zwolnijTreneraByMenadzer');
      } else {
        alert("Nie masz uprawnień do tego panelu!");
      }  
    }; 

    const zwolnijSkautaAdmin = () => {
      if (isUserInRole(["ROLE_ADMIN"])) {
        navigate('/zwolnijSkauta');
      } else {
        alert("Nie masz uprawnień do tego panelu!");
      }  
    }; 

    const zwolnijTreneraAdmin = () => {
      if (isUserInRole(["ROLE_ADMIN"])) {
        navigate('/zwolnijTrenera');
      } else {
        alert("Nie masz uprawnień do tego panelu!");
      }  
    }; 

    const zwolnijMenadzeraAdmin = () => {
      if (isUserInRole(["ROLE_ADMIN"])) {
        navigate('/zwolnijMenadzera');
      } else {
        alert("Nie masz uprawnień do tego panelu!");
      }  
    }; 

    return (
    <div>
      <Navbar/>

      <div className="admin-buttons-container">
          {isUserInRole(['ROLE_MENADZER_KLUBU']) && (
          <button onClick={createZawodnikMenadzer} className="admin-button">Stwórz profil Zawodnika</button>)}
          {isUserInRole(['ROLE_MENADZER_KLUBU']) && !status.hasTrener &&(
          <button onClick={createTrenerMenadzer} className="admin-button" disabled={status.hasTrener}>Stwórz profil Trenera</button>)}
          {isUserInRole(['ROLE_MENADZER_KLUBU']) && !status.hasSkaut &&(
          <button onClick={createSkautMenadzer} className="admin-button" disabled={status.hasSkaut}>Stwórz profil Skauta</button>)}
          
          {isUserInRole(['ROLE_MENADZER_KLUBU']) && status.hasSkaut &&(
          <button onClick={zwolnijSkautaMenadzer} className="admin-button" >Zwolnij Skauta</button>)}
          {isUserInRole(['ROLE_MENADZER_KLUBU']) && status.hasTrener &&(
          <button onClick={zwolnijTreneraMenadzer} className="admin-button">Zwolnij Trenera</button>)}

          {isUserInRole(['ROLE_ADMIN']) && (
          <button onClick={createZawodnikAdmin} className="admin-button">Stwórz profil Zawodnika</button>)}
          {isUserInRole(['ROLE_ADMIN']) && (
          <button onClick={createTrenerAdmin} className = "admin-button">Stwórz profil Trenera</button>)}
          {isUserInRole(['ROLE_ADMIN']) && (
          <button onClick={createSkautAdmin} className = "admin-button">Stwórz profil Skauta</button>)}
          {isUserInRole(['ROLE_ADMIN']) && (
          <button onClick={createMenadzerAdmin} className = "admin-button">Stwórz profil Menadżera</button>)}
          
          {isUserInRole(['ROLE_ADMIN']) && (
          <button onClick={createTrofeum} className = "admin-button">Przydziel trofeum dla klubu</button>)}    

          {isUserInRole(['ROLE_ADMIN']) && (
          <button onClick={zwolnijSkautaAdmin} className="admin-button">Zwolnij Skauta</button>)}
          {isUserInRole(['ROLE_ADMIN']) && (
          <button onClick={zwolnijTreneraAdmin} className = "admin-button">Zwolnij Trenera</button>)}
          {isUserInRole(['ROLE_ADMIN']) && (
          <button onClick={zwolnijMenadzeraAdmin} className = "admin-button">Zwolnij Menadzera</button>)}
      </div>
    </div>
    );
}

export default AdminPanel;