// import React, { useEffect, useState } from 'react';
// import axios from 'axios';

// const Mistrzowiepolski = () => {
//   const [mp, setMP] = useState([]);

//   useEffect(() => {
//     const authHeader = 'Basic ' + btoa('admin:admin');
//     axios.get('http://localhost:8080/trofeum/mistrzpolski', {
//       headers: {
//         'Authorization': authHeader, 
//       }
//     })
//     .then(response => {
//       setMP(response.data);
//     })
//     .catch(error => {
//       console.error('Error fetching data:', error);
//     });
//   }, []);

//   return (
//     <div>
//         <h1>Mistrzowie Polski</h1>
//         {mp.length === 0 ? (
//             <p>Brak danych</p>
//         ) : (
//             <ul>
//                 {mp.map((trofeum, index) => (
//                     <li key={index}>
//                         <strong>Trofeum:</strong> {trofeum.nazwaTrofeum || "Brak"}<br />
//                         <strong>Klub:</strong> {trofeum.nazwaKlubu || "Brak"}<br />
//                         <strong>Data zdobycia:</strong> {trofeum.dataZdobycia || "Brak"}
//                     </li>
//                 ))}
//             </ul>
//         )}
//     </div>
// );
// }

// export default Mistrzowiepolski;

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../cssFolder/MistrzowiePolski.css';
import '../cssFolder/Navbar.css'; // Import only the CSS styles

const MistrzowiePolski = () => {
  const [mp, setMP] = useState([]);

  useEffect(() => {
    const authHeader = 'Basic ' + btoa('admin:admin');
    axios.get('http://localhost:8080/trofeum/mistrzpolski', {
      headers: {
        'Authorization': authHeader,
      }
    })
    .then(response => {
      setMP(response.data);
    })
    .catch(error => {
      console.error('Error fetching data:', error);
    });
  }, []);

  return (
    <div className="mistrzowiepolski-container">
      {/* Pasek nawigacyjny */}
      <div className="navbar">
        <img src="https://upload.wikimedia.org/wikipedia/commons/9/97/Logo_PZPN_%28transparent%29.jpg" alt="Logo" className="navbar-logo" />
        <h1 className="navbar-title">Mistrzowie Polski</h1>
      </div>
      
      <h1>Mistrzowie Polski</h1>
      
      {mp.length === 0 ? (
        <p>Brak danych</p>
      ) : (
        <ul className="mp-list">
          {mp.map((trofeum, index) => (
            <li key={index} className="mp-item">
              <strong>Trofeum:</strong> {trofeum.nazwaTrofeum || "Brak"}<br />
              <strong>Klub:</strong> {trofeum.nazwaKlubu || "Brak"}<br />
              <strong>Data zdobycia:</strong> {trofeum.dataZdobycia || "Brak"}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default MistrzowiePolski;

