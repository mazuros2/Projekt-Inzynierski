import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Trofea = () => {
  const [trofea, setTrofea] = useState([]);

  useEffect(() => {
    const authHeader = 'Basic ' + btoa('admin:admin');
    axios.get('http://localhost:8080/trofeum/mistrzpolski', {
      headers: {
        'Authorization': authHeader, 
      }
    })
    .then(response => {
      setTrofea(response.data);
    })
    .catch(error => {
      console.error('Error fetching data:', error);
    });
  }, []);

  return (
    <div>
        <h1>Trofea</h1>
        {trofea.length === 0 ? (
            <p>Brak danych</p>
        ) : (
            <ul>
                {trofea.map((trofeum, index) => (
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

export default Trofea;