import React, { useEffect, useState } from 'react';
import axios from 'axios';

const WszystkieKluby = () => {
  const [kluby, setKluby] = useState([]);

  useEffect(() => {
    const authHeader = 'Basic ' + btoa('admin:admin');
    axios.get('http://localhost:8080/kluby', {
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
  }, []);

  return (
    <div>
      <h1>Wszystkie Kluby</h1>
      {kluby.length === 0 ? (
        <p>Brak danych</p>
      ) : (
        <ul>
          {kluby.map((klub, index) => (
            <li key={index}>
              <strong>Nazwa klubu:</strong> {klub.nazwaKlubu || "Brak"}<br />
              <strong>Rok założenia:</strong> {klub.rokZalozenia || "Brak"}<br />
              <strong>Obecna liga klubu:</strong> {klub.ligaNazwaLigi || "Brak"}<br />
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default WszystkieKluby;