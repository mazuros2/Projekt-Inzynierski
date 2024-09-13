import React, { useEffect, useState } from 'react';
import axios from 'axios';

const LigaKluby = () => {
  const [klub, setKlub] = useState([]);

  useEffect(() => {
    const authHeader = 'Basic ' + btoa('admin:admin');
    axios.get('http://localhost:8080/{id}/kluby', {
      headers: {
        'Authorization': authHeader, 
      }
    })
    .then(response => {
        setKlub(response.data);
    })
    .catch(error => {
      console.error('Error fetching data:', error);
    });
  }, []);

  return (
    <div>
      <h1>Klub</h1>
      {klub.length === 0 ? (
        <p>Brak danych</p>
      ) : (
        <ul>
          {klub.map((klub, index) => (
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