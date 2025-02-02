import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';
import { useNavigate, useParams, Link } from 'react-router-dom';
import '../cssFolder/Klub.css'; 
import '../cssFolder/Navbar.css'; 

const Klub = () => {
  const { id } = useParams();
  const [klub, setKlub] = useState(null);
  const [zawodnicy, setZawodnicy] = useState([]);
  const [trofea, setTrofea] = useState([]);
  const [trener, setTrener] = useState(null); 
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const goBackToKluby = () => {
    navigate('/kluby');
  };

  useEffect(() => {
    const token = sessionStorage.getItem("token");

    if (!token) {
      setError("Brak tokena. Zaloguj się ponownie.");
      return;
    }

    axios
      .get(`${process.env.REACT_APP_API_URL}/klub/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setKlub(response.data);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
        setError("Błąd podczas pobierania danych klubu. Sprawdź uprawnienia.");
      });

    axios
      .get(`${process.env.REACT_APP_API_URL}/klub/${id}/zawodnicy`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setZawodnicy(response.data);
      })
      .catch((error) => {
        console.error("Error fetching zawodnicy:", error);
        setError("Błąd podczas pobierania zawodników. Sprawdź uprawnienia.");
      });

    axios
      .get(`${process.env.REACT_APP_API_URL}/klub/${id}/trofea`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setTrofea(response.data);
      })
      .catch((error) => {
        console.error("Error fetching trofea:", error);
        setError("Błąd podczas pobierania trofeów. Sprawdź uprawnienia.");
      });

    axios
      .get(`${process.env.REACT_APP_API_URL}/klub/${id}/trener`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setTrener(response.data);
      })
      .catch((error) => {
        console.error("Error fetching trener:", error);
        setError("Błąd podczas pobierania trenera. Sprawdź uprawnienia.");
      });
  }, [id]);

  if (error) {
    return <p className="error-message">{error}</p>;
  }

  if (!klub) {
    return <p>Brak danych</p>;
  }

  return (
    <div className="klub-container">
      <Navbar />
      
      {/*<h1>Szczegóły Klubu</h1>*/}
      <div className='calyklub'> 
      {/* Szczegóły klubu */}
      <div className='logo-info-container'>
      <div className='logo-info'>
      <div className="klub-logo-container">
        {klub.logo_url ? (
          <img src={klub.logo_url} alt={klub.nazwaKlubu} className="klub-logo-central" />
        ) : (
          <p>Brak logo klubu</p>
        )}
      </div>
      <ul className="klub-details-list">
        {/*<li><strong>ID:</strong> {klub.id || "Brak"}</li> */}
        <li><strong>Nazwa klubu:</strong> {klub.nazwaKlubu || "Brak"}</li>
        <li><strong>Rok założenia:</strong> {klub.rokZalozenia || "Brak"}</li>
        <li><strong>Obecna liga klubu:</strong> {klub.ligaNazwaLigi || "Brak"}</li>
      </ul>
      </div>
      </div>

      {/* Wyświetlanie trofeów */}
      <div className="trofea">
        <h2>Trofea</h2>
        {trofea.length > 0 ? (
          <ul className="trofea-list">
            {trofea.map((trofeum) => (
              <li key={trofeum.id}>
                <strong>{trofeum.nazwa}</strong> - {new Date(trofeum.data_zdobycia).toLocaleDateString()}
              </li>
            ))}
          </ul>
        ) : (
          <p>Brak trofeów</p>
        )}
      </div>

      {/* Wyświetlanie trenera */}
      <div className="trener">
        <h2>Trener</h2>
        {trener ? (
          <ul className="trener-details-list">
            <li><strong>Imię i nazwisko: </strong> 
              <Link to={`/trener/profil/${trener.id}`} className="trener-link">
              {trener.imie} {trener.nazwisko}
            </Link>
            </li>
            <li><strong>Data urodzenia:</strong> {new Date(trener.dataUrodzenia).toLocaleDateString()}</li>
            <li><strong>Licencja trenera:</strong> {trener.licencjaTrenera}</li>
          </ul>
        ) : (
          <p>Brak danych o trenerze</p>
        )}
      </div>

      {/* Wyświetlanie zawodników */}
      <div className="zawodnicy">
        <h2>Zawodnicy</h2>
        {zawodnicy.length > 0 ? (
          <ul className="zawodnicy-list">
            {zawodnicy.map((zawodnik) => (
              <li key={zawodnik.id}>
                <Link to={`/zawodnicy/profil/${zawodnik.id}`}>
                  <strong>{zawodnik.imie} {zawodnik.nazwisko}</strong>
                </Link> - {zawodnik.pozycja}
              </li>
            ))}
          </ul>
        ) : (
          <p>Brak zawodników w tym klubie</p>
        )}
      </div>

      {/* Przycisk powrotu */}
      <div className="back-button-container">
        <button onClick={goBackToKluby} className="back-button">Powrót do wszystkich klubów</button>
      </div>
    </div>
    </div>
  );
};

export default Klub;
