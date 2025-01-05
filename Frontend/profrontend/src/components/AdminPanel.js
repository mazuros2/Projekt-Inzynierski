import React from 'react';
import { useNavigate } from 'react-router-dom';
import { isUserInRole } from "../service/authService.js";
import Navbar from '../components/Navbar';
import '../cssFolder/Navbar.css';
import '../cssFolder/AdminPanel.css'


const AdminPanel = () => {
  const navigate = useNavigate();

    const createZawodnik = () => {
      if (isUserInRole(["ROLE_ADMIN", "ROLE_MENADZER_KLUBU"])) {
        navigate('/createZawodnik');
      } else {
        alert("Nie masz uprawnień do tego panelu!");
      }
    };  

    const createTrener = () => {
      if (isUserInRole(["ROLE_ADMIN", "ROLE_MENADZER_KLUBU"])) {
        navigate('/createTrener');
      } else {
        alert("Nie masz uprawnień do tego panelu!");
      }  
    }; 

    const createSkaut = () => {
      if (isUserInRole(["ROLE_ADMIN", "ROLE_MENADZER_KLUBU"])) {
        navigate('/createSkaut');
      } else {
        alert("Nie masz uprawnień do tego panelu!");
      }  
    }; 

    const createMenadzer = () => {
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

    return (
    <div>
      <Navbar/>

      <div className="admin-buttons-container">
          <button onClick={createZawodnik} className="admin-button">Stwórz profil Zawodnika</button>
          <button onClick={createTrener} className = "admin-button">Stwórz profil Trenera</button>
          <button onClick={createSkaut} className = "admin-button">Stwórz profil Skauta</button>
          {isUserInRole(['ROLE_ADMIN']) && (
          <button onClick={createMenadzer} className = "admin-button">Stwórz profil Menadżera</button>)}
          {isUserInRole(['ROLE_ADMIN']) && (
          <button onClick={createTrofeum} className = "admin-button">Przydziel trofeum dla klubu</button>)}    
      </div>
    </div>
    );
}

export default AdminPanel;