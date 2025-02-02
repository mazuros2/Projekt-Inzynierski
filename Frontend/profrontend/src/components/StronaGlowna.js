import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import Navbar from '../components/Navbar';
import '../cssFolder/Navbar.css'; 
import '../cssFolder/StronaGlowna.css'; 


const StronaGlowna = () => {
    const navigate = useNavigate();
    const [zdobywcyPP, setPP] = useState([]);
    const [zdobywcyMP, setMP] = useState([]);
    const [transfery, setTransfery] = useState([]);
    const [error, setError] = useState("");

    useEffect(() => {
        const token = sessionStorage.getItem('token');
        if (!token) {
          console.error('Brak tokena. Przekierowanie do logowania.');
          navigate('/logowanie');
          return;
        }

        axios.get(`${process.env.REACT_APP_API_URL}/api/trofeum/pucharpolski/ostatnizdobywcy`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
        })
        .then((response) => {
            setPP(response.data);
        })
        .catch((error) => {
        console.error("Error fetching trofea:", error);
            setError("Błąd podczas pobierania trofeów. Sprawdź uprawnienia.");
        });

        axios.get(`${process.env.REACT_APP_API_URL}/api/trofeum/mistrzpolski/ostatnizdobywcy`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
        })
        .then((response) => {
            setMP(response.data);
        })
        .catch((error) => {
            console.error("Error fetching trofea:", error);
                setError("Błąd podczas pobierania trofeów. Sprawdź uprawnienia.");
        });
        
        axios.get(`${process.env.REACT_APP_API_URL}/api/transfery/ostatnie`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
        .then((response) => {
          setTransfery(response.data);
        })
        .catch((error) => {
          console.error("Error fetching transfers:", error);
          setError("Błąd podczas pobierania transferów.");
        });

    }, []);


    return (
        <div>

        <Navbar/>
            
            <div className='mainpage-container'>
            
                <div className='mp-zdobywcy'>
                    <h2>Ostatni zdobywcy Mistrzostwa Polski</h2>    
                    <ul>
                      {zdobywcyMP.map((mp, index) => (
                        <li key={index}>
                          <strong>Nazwa:</strong> {mp.nazwaTrofeum} <br />
                          <strong>Klub:</strong> {mp.nazwaKlubu} <br />
                          <strong>Data Zdobycia:</strong> {mp.dataZdobycia}
                        </li>
                      ))}
                    </ul>
                </div>
            
                <div className='ostatnie-transfery'>
                    <h2>Ostatnie transfery</h2>
                    <ul>
                      {transfery.map((transfer, index) => (
                      <li key={index}>
                
                      <strong>Zawodnik: </strong>
                      <a href={`/zawodnicy/profil/${transfer.zawodnikId}`}>{transfer.imieZawodnika} {transfer.nazwiskoZawodnika}</a>
                      <br />
                
                      <strong>Od: </strong>
                      <a href={`/klub/${transfer.klubOdId}`}>{transfer.nazwaKlubuOd}</a>
                      <br />

                      <strong>Do: </strong>
                      <a href={`/klub/${transfer.klubDoId}`}>{transfer.nazwaKlubuDo}</a>
                      <br />
                
                      <strong>Kwota: </strong> {transfer.kwota} zł
                      </li>
                      ))}
                    </ul>
                </div>
            
                <div className='pp-zdobywcy'>
                    <h2>Ostatni zdobywcy Pucharu Polski</h2>
                    <ul>
                      {zdobywcyPP.map((pp, index) => (
                        <li key={index}>
                          <strong>Nazwa:</strong> {pp.nazwaTrofeum} <br />
                          <strong>Klub:</strong> {pp.nazwaKlubu} <br />
                          <strong>Data Zdobycia:</strong> {pp.dataZdobycia}
                        </li>
                      ))}
                    </ul>
                </div>
            
            </div>
        </div>
    );
};

export default StronaGlowna;