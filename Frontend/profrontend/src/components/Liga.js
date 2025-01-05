import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import { Link } from 'react-router-dom';
import Navbar from '../components/Navbar';
import '../cssFolder/Liga.css'

const Liga = () => {
  const [liga, setLiga] = useState([]);
  const navigate = useNavigate();

    useEffect(() => {
      const token = sessionStorage.getItem("token");
      if (!token) {
        console.error("Brak tokena. Przekierowanie do logowania.");
        navigate("/logowanie");
        return;
      }

      axios
      .get("http://localhost:8080/ligii", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setLiga(response.data);
      })
      .catch((error) => {
        console.error("Error fetching clubs:", error);
      });
    }, [navigate]);


  return (
    <div>
      <Navbar/>

      <h1>Wszystkie ligii</h1>
      {liga.length === 0 ? (
        <p>Brak danych</p>
        ) : (
          <ul className="liga-list">
            {liga.map((liga) => (
              <li key={liga.id} className="liga-item">
                <Link to={`${liga.id}/kluby`} className="liga-link">
                  <strong>Nazwa ligi:</strong> {liga.nazwaLigi || "Brak"}
                </Link>
              <br />
              <strong>Poziom Ligi:</strong> {liga.poziomLigi || "Brak"}
              <br />
            </li>
            ))}
          </ul>
        )}
    </div>
);
}

export default Liga;