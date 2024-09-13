import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const LigaKluby = () => {
  const { ligaId } = useParams(); 
  const [kluby, setKluby] = useState([]);

  useEffect(() => {
    const authHeader = 'Basic ' + btoa('admin:admin');
    axios.get(`http://localhost:8080/${ligaId}/kluby`, { 
      headers: {
        'Authorization': authHeader,
      }
    })
    .then(response => {
      setKluby(response.data);
    })
    .catch(error => {
      console.error('Error fetching data:', error);
    });
  }, [ligaId]);

  return (
    <div>
      <h1>Kluby w Lidze {ligaId}</h1>
      {kluby.length === 0 ? (
        <p>Brak danych</p>
      ) : (
        <ul>
          {kluby.map((klub, index) => (
            <li key={index}>
              <strong>ID:</strong> {klub.id || "Brak"}<br />
              <strong>Nazwa klubu:</strong> {klub.nazwaKlubu || "Brak"}<br />
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default LigaKluby;
