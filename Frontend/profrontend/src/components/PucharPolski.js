import React, { useEffect, useState } from 'react';
import axios from 'axios';

const PucharPolski = () => {
  const [pp, setpp] = useState([]);

  useEffect(() => {
    const authHeader = 'Basic ' + btoa('admin:admin');
    axios.get('http://localhost:8080/trofeum/pucharpolski', {
      headers: {
        'Authorization': authHeader, 
      }
    })
    .then(response => {
      setpp(response.data);
    })
    .catch(error => {
      console.error('Error fetching data:', error);
    });
  }, []);

  return (
    <div>
        <h1>Zdobywcy Pucharu Polskiego</h1>
        {pp.length === 0 ? (
            <p>Brak danych</p>
        ) : (
            <ul>
                {pp.map((trofeum, index) => (
                    <li key={index}>
                        <strong>Trofeum:</strong> {trofeum.nazwaTrofeum || "Brak"}<br />
                        <strong>Klub:</strong> {trofeum.nazwaKlubu || "Brak"}<br />
                        <strong>Data zdobycia:</strong> {trofeum.dataZdobycia || "Brak"}
                    </li>
                ))}
            </ul>
        )}
    </div>
);
}

export default PucharPolski;