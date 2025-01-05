import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import Navbar from '../components/Navbar';
import '../cssFolder/WszystkieKluby.css';
import '../cssFolder/Navbar.css';

const WszystkieKluby = () => {
  const [kluby, setKluby] = useState([]);
  const navigate = useNavigate();
  
  useEffect(() => {
    const token = sessionStorage.getItem('token');
    if (!token) {
      console.error('Brak tokena. Przekierowanie do logowania.');
      navigate('/logowanie');
      return;
    }

    // Pobieranie listy klubów
    axios.get('http://localhost:8080/kluby', {
      headers: { Authorization: `Bearer ${token}` },
    })
    .then((response) => setKluby(response.data))
    .catch((error) => {
      console.error('Błąd pobierania danych:', error);
      if (error.response?.status === 401) {
        sessionStorage.removeItem('token');
        navigate('/logowanie');
      }
    });
  }, [navigate]);

  return (
    <div className="kluby-container">
      
      <Navbar/>
      
      <h1>Wszystkie Kluby</h1>
      {kluby.length === 0 ? (
        <p>Brak danych</p>
      ) : (
        <ul className="kluby-list">
          {kluby.map((klub, index) => (
            <li key={index} className="klub-item">
              <div className="klub-info">
                <span className="klub-logo">
                  <img src={klub.logo_url} alt={klub.nazwaKlubu} />
                </span>
                <div className="klub-details">
                  <strong>
                      <Link to={`/klub/${klub.id}`}>
                        {klub.nazwaKlubu || "Brak"}
                      </Link>
                  </strong><br />
                  Rok założenia: {klub.rokZalozenia || "Brak"}<br />
                  Liga: {klub.ligaNazwaLigi || "Brak"}
                </div>
              </div>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default WszystkieKluby;
