import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Liga = () => {
  const [liga, setLiga] = useState([]);

  useEffect(() => {
    const authHeader = 'Basic ' + btoa('admin:admin');
    axios.get('http://localhost:8080/ligii', {
      headers: {
        'Authorization': authHeader, 
      }
    })
    .then(response => {
        setLiga(response.data);
    })
    .catch(error => {
      console.error('Error fetching data:', error);
    });
  }, []);

  return (
    <div>
      <h1>Wszystkie ligii</h1>
      {liga.length === 0 ? (
        <p>Brak danych</p>
      ) : (
        <ul>
          {liga.map((liga, index) => (
            <li key={index}>
              <strong>Nazwa ligi:</strong> {liga.nazwaLigi || "Brak"}<br />
              <strong>Poziom Ligi:</strong> {liga.poziomLigi || "Brak"}<br />
            </li>
          ))}
        </ul>
      )}
    </div>
);
}

export default Liga;