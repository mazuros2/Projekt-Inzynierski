import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const Klub = () => {
  const { id } = useParams();
  const [klub, setKlub] = useState(null);

  useEffect(() => {
    const authHeader = 'Basic ' + btoa('admin:admin');
    axios.get(`http://localhost:8080/klub/${id}`, { 
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
  }, [id]);

  if (!klub) {
    return <p>Brak danych</p>;
  }

  return (
    <div>
      <h1>Szczegóły Klubu</h1>
      <ul>
        <li><strong>ID:</strong> {klub.id || "Brak"}</li>
        <li><strong>Nazwa klubu:</strong> {klub.nazwaKlubu || "Brak"}</li>
        <li><strong>Rok założenia:</strong> {klub.rokZalozenia || "Brak"}</li>
        <li><strong>Obecna liga klubu:</strong> {klub.ligaNazwaLigi || "Brak"}</li>
      </ul>
    </div>
  );
};

export default Klub;