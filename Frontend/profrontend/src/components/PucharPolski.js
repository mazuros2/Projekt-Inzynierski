// import React, { useEffect, useState } from 'react';
// import axios from 'axios';

// const PucharPolski = () => {
//   const [pp, setpp] = useState([]);

//   useEffect(() => {
//     const authHeader = 'Basic ' + btoa('admin:admin');
//     axios.get('http://localhost:8080/trofeum/pucharpolski', {
//       headers: {
//         'Authorization': authHeader, 
//       }
//     })
//     .then(response => {
//       setpp(response.data);
//     })
//     .catch(error => {
//       console.error('Error fetching data:', error);
//     });
//   }, []);

//   return (
//     <div>
//         <h1>Zdobywcy Pucharu Polskiego</h1>
//         {pp.length === 0 ? (
//             <p>Brak danych</p>
//         ) : (
//             <ul>
//                 {pp.map((trofeum, index) => (
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

// export default PucharPolski;

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../cssFolder/PucharPolski.css';
import '../cssFolder/Navbar.css'; // Import the CSS file for Navbar

const PucharPolski = () => {
  const [pp, setpp] = useState([]);

  useEffect(() => {
    const authHeader = 'Basic ' + btoa('admin:admin');
    axios.get(`${process.env.REACT_APP_API_URL}/trofeum/pucharpolski`, {
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
    <div className="pucharpolski-container">
      {/* Pasek nawigacyjny */}
      <div className="navbar">
        <img src="https://lh3.googleusercontent.com/proxy/4C4zlh5y6xvZC7MWNsG_99nE1x8yqQnSczaCD2cUy4xlvPOQFcm5vLMoEhrcczwjBcfADm4La8Li__oU9Gzy1Whmwpj1U0BvwG6FlMpj6y7cQuI4IfftojBNTeKQocivQu7lbKfiKvXW30jdeizyGN6AHdIUSpc7mWw1" alt="Logo" className="navbar-logo" />
        <h1 className="navbar-title"></h1>
        <div className="icons-container">
          <img
            src="https://www.pikpng.com/pngl/b/112-1121340_settings-logo-png-white-png-download-setting-icon.png"
            alt="Ustawienia"
            className="settings-icon"
          />
          <img
            src="https://www.pikpng.com/pngl/b/259-2599075_gear-user-account-person-configure-control-comments-security.png"
            alt="Użytkownik"
            className="user-icon"
          />
        </div>
      </div>

      {/* Zawartość strony */}
      <h1>Zdobywcy Pucharu Polski</h1>
      
      {pp.length === 0 ? (
        <p>Brak danych</p>
      ) : (
        <ul className="pp-list">
          {pp.map((trofeum, index) => (
            <li key={index} className="pp-item">
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

export default PucharPolski;

