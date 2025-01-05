import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams, useNavigate} from 'react-router-dom';
import Navbar from '../components/Navbar';
import '../cssFolder/TrenerDetails.css'

const TrenerDetails = () => {
  const { id } = useParams();
  const [trener, setTrener] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const token = sessionStorage.getItem('token');
    if (!token) {
      console.error('Brak tokena. Przekierowanie do logowania.');
      navigate('/logowanie');
      return;
    }

    axios
      .get(`http://localhost:8080/trener/profil/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setTrener(response.data);
      })
      .catch((error) => {
        console.error('Error fetching trainer profile:', error);
      });
  }, [id, navigate]);

  if (!trener) {
    return <p>Wczytywanie danych trenera...</p>;
  }

  return (
    <div className="trener-profil">
      <Navbar/>
      
      <h1>Profil Trenera</h1>
      
      <div className ="profilowe-info">
        <div className="user-profilowe">
          {trener.profiloweURL ? (
            <img src={trener.profiloweURL} />
          ) : (
           <p>Brak profilowego </p>
          )}
        </div>
      
        <div className ="info">
          <p><strong>Imię:</strong> {trener.imie}</p>
          <p><strong>Nazwisko:</strong> {trener.nazwisko}</p>
          <p><strong>Data urodzenia:</strong> {trener.dataUrodzenia}</p>
          <p><strong>Kraje pochodzenia:</strong> {trener.krajePochodzenia.join(', ')}</p>
          <p><strong>Licencja:</strong> {trener.licencjaTrenera}</p>
          <p><strong>Klub:</strong> {trener.klub}</p>
        </div>
      </div>
      

      <div className='back-button-container'>
        <button onClick={() => navigate(-1)} className="back-button">Wróć</button> 
      </div>
      
    </div>
  );
};

export default TrenerDetails;
