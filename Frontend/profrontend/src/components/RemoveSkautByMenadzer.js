import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../cssFolder/RegisterUser.css";
import Navbar from '../components/Navbar';

const ZwolnijSkauta = () => {
  const navigate = useNavigate();
  const [message, setMessage] = useState("");
  
  const handleZwolnij = async () => {
    const token = sessionStorage.getItem("token");

    if (!token) {
      console.error("Brak tokena. Przekierowanie do logowania.");
      navigate("/logowanie");
      return;
    }

    axios
      .delete("http://localhost:8080/api/menadzer/zwolnijSkauta", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setMessage(response.data); 
      })
      .catch((error) => {
        setMessage(error.response?.data || "Błąd podczas zwalniania skauta!");
        console.error("Błąd:", error);
      });
      navigate("/adminPanel");
    }

  return (
    <div>
        <Navbar/>
        <h1>Zwolnij skauta</h1>
        <div className = "form-container">
            <button type="submit" className="submit-button" onClick={handleZwolnij}>Zwolnij</button>
            {message && <p>{message}</p>}
        </div>
    </div>
    
  );
};

export default ZwolnijSkauta;