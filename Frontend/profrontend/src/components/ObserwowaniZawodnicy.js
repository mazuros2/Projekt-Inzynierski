import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import jwtDecode from 'jwt-decode';
import Navbar from '../components/Navbar';
import '../cssFolder/ObserwowaniZawodnicy.css';

const ListaObserwowanych = () => {
  const [zawodnicy, setZawodnicy] = useState([]);
  const [error, setError] = useState("");
  const [userRole, setUserRole] = useState(null);
  const navigate = useNavigate();

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

    const endpoint =
      role === "ROLE_MENADZER_KLUBU"
        ? `${process.env.REACT_APP_API_URL}/api/skautingZawodnika/menadzer/listaZawodnikow`
        : `${process.env.REACT_APP_API_URL}/api/skautingZawodnika/skaut/listaZawodnikow`;

    axios
      .get(endpoint, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setZawodnicy(response.data);
      })
      .catch((error) => {
        console.error("Błąd podczas pobierania listy zawodników:", error);
        setError("Błąd podczas pobierania listy zawodników.");
      });
  }, []);

  const handleUsunClick = (idZawodnika) => {
    const token = sessionStorage.getItem("token");
    if (!token) {
      setError("Brak tokena. Zaloguj się ponownie.");
      return;
    }
  
    const endpoint =
      userRole === "ROLE_MENADZER_KLUBU"
        ? `${process.env.REACT_APP_API_URL}/api/skautingZawodnika/menadzer/usunZawodnika/${idZawodnika}`
        : `${process.env.REACT_APP_API_URL}/api/skautingZawodnika/skaut/usunZawodnika/${idZawodnika}`;
  
    axios
      .delete(endpoint, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then(() => {
        alert("Zawodnik został usunięty z obserwowanych.");
        setZawodnicy(zawodnicy.filter((zawodnik) => zawodnik.id !== idZawodnika));
      })
      .catch((error) => {
        console.error("Błąd podczas usuwania zawodnika z obserwowanych:", error);
        alert("Nie udało się usunąć zawodnika z obserwowanych.");
      });
  };
  


  if (error) {
    return <p className="error-message">{error}</p>;
  }

  return (
    <div>
      <Navbar/>
    
      <h1>Lista obserwowanych Zawodników</h1>
      {zawodnicy.length === 0 ? (
        <p>Brak obserwowanych zawodników.</p>
      ) : (
        <ul>
          {zawodnicy.map((zawodnik) => (
            <li key={zawodnik.id} className="zawodnicy-obs-container">
              <div className="zawodnicy-obs-info">
                <p><strong>Imię:</strong> {zawodnik.imie} </p>
                <p><strong>Nazwisko:</strong> {zawodnik.nazwisko} </p>
                <p><strong>Pozycja:</strong> {zawodnik.pozycja.nazwa_pozycji} </p>
                <p><strong>Obecny klub:</strong> {zawodnik.obecnyKlub} </p>
                <p><strong>Kraj pochodzenia: </strong>{zawodnik.krajPochodzenia.map((kraj) => kraj.nazwa).join(', ')}</p>
              </div>
              <div className="zawodnicy-obs-buttons">
                <button className="obs-button" onClick={() => navigate(`/zawodnicy/profil/${zawodnik.id}`)}>Profil Zawodnika</button>
                <button className="obs-button" onClick={() => handleUsunClick(zawodnik.id)}>Usuń z obserwowanych</button>
              </div>
            </li>
          ))}
        </ul>
      )}
    </div>
    
  );
};

export default ListaObserwowanych;
