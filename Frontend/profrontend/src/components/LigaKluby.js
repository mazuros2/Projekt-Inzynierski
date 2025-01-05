import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams, useNavigate, Link } from 'react-router-dom'; 
import Navbar from '../components/Navbar';
import '../cssFolder/Navbar.css';
import '../cssFolder/LigaKluby.css'; 

const LigaKluby = () => {
  const { ligaId } = useParams(); 
  const [kluby, setKluby] = useState([]);
  const [liga, setLiga] = useState(null); 
  const navigate = useNavigate();

 
  useEffect(() => {
    const token = sessionStorage.getItem("token");
    if (!token) {
      console.error("Brak tokena. Przekierowanie do logowania.");
      navigate("/logowanie");
      return;
    }

    axios.get(`http://localhost:8080/${ligaId}/kluby`, {
      headers: { Authorization: `Bearer ${token}` }
    })
    .then((response) => {
      setKluby(response.data);
    })
    .catch((error) => {
      console.error("Error fetching clubs:", error);
    });

    axios.get(`http://localhost:8080/ligii/${ligaId}`, {
      headers: { Authorization: `Bearer ${token}` }
    })
    .then((response) => {
      setLiga(response.data);
    })
    .catch((error) => {
      console.error("Error fetching league:", error);
    });

  }, [ligaId, navigate]);

  return (
    <div className="kluby-container">
      <Navbar/>

      <h1>Kluby w {liga ? liga.nazwaLigi : "..."}</h1>

      {kluby.length === 0 ? (
        <p>Brak danych</p>
      ) : (
        <ul className="kluby-list">
          {kluby.map((klub) => (
            <li key={klub.id} className="klub-item">
              <div className="klub-info">
                <span className="klub-logo">
                  <img src={klub.logo_url} alt={klub.nazwaKlubu} />
                </span>
                <Link to={`/klub/${klub.id}`} className="klub-link">
                  {klub.nazwaKlubu || "Brak"}
                </Link>
              </div>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default LigaKluby;
