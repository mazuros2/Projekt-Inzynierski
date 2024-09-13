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

    </div>
);
}

export default Liga;