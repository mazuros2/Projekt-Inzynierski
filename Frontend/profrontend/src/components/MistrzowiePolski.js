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
    axios.get(`${process.env.REACT_APP_API_URL}/trofeum/mistrzpolski`, {
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
        <img src="https://lh3.googleusercontent.com/proxy/4C4zlh5y6xvZC7MWNsG_99nE1x8yqQnSczaCD2cUy4xlvPOQFcm5vLMoEhrcczwjBcfADm4La8Li__oU9Gzy1Whmwpj1U0BvwG6FlMpj6y7cQuI4IfftojBNTeKQocivQu7lbKfiKvXW30jdeizyGN6AHdIUSpc7mWw1" alt="Logo" className="navbar-logo" />
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

