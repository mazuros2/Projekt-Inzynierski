// import React, { useEffect, useState } from 'react';
// import axios from 'axios';
// import { useParams } from 'react-router-dom';

// const LigaKluby = () => {
//   const { ligaId } = useParams(); 
//   const [kluby, setKluby] = useState([]);

//   useEffect(() => {
//     const authHeader = 'Basic ' + btoa('admin:admin');
//     axios.get(`http://localhost:8080/${ligaId}/kluby`, { 
//       headers: {
//         'Authorization': authHeader,
//       }
//     })
//     .then(response => {
//       setKluby(response.data);
//     })
//     .catch(error => {
//       console.error('Error fetching data:', error);
//     });
//   }, [ligaId]);

//   return (
//     <div>
//       <h1>Kluby w Lidze {ligaId}</h1>
//       {kluby.length === 0 ? (
//         <p>Brak danych</p>
//       ) : (
//         <ul>
//           {kluby.map((klub, index) => (
//             <li key={index}>
//               <strong>ID:</strong> {klub.id || "Brak"}<br />
//               <strong>Nazwa klubu:</strong> {klub.nazwaKlubu || "Brak"}<br />
//             </li>
//           ))}
//         </ul>
//       )}
//     </div>
//   );
// };

// export default LigaKluby;
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom'; 
import '../cssFolder/Navbar.css';
import '../cssFolder/LigaKluby.css'; 


const LigaKluby = () => {
  const { ligaId } = useParams(); 
  const [kluby, setKluby] = useState([]);
  const [showSettings, setShowSettings] = useState(false);
  const navigate = useNavigate();

  const toggleSettings = () => {
    setShowSettings(!showSettings);
  };

  const goToUserProfile = () => {
    navigate('/user-profile');
  };

  useEffect(() => {
    const authHeader = 'Basic ' + btoa('admin:admin');
    axios.get(`http://localhost:8080/${ligaId}/kluby`, { 
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
  }, [ligaId]);

  return (
    <div className="kluby-container">
      {/* Pasek nawigacyjny */}
      <div className="navbar">
        <img 
          src="https://lh3.googleusercontent.com/proxy/4C4zlh5y6xvZC7MWNsG_99nE1x8yqQnSczaCD2cUy4xlvPOQFcm5vLMoEhrcczwjBcfADm4La8Li__oU9Gzy1Whmwpj1U0BvwG6FlMpj6y7cQuI4IfftojBNTeKQocivQu7lbKfiKvXW30jdeizyGN6AHdIUSpc7mWw1" 
          alt="Logo" 
          className="navbar-logo" 
        />
        <h1 className="navbar-title"></h1>
        <div className="icons-container">
          <img
            src="https://www.pikpng.com/pngl/b/112-1121340_settings-logo-png-white-png-download-setting-icon.png"
            alt="Ustawienia"
            className="settings-icon"
            onClick={toggleSettings}
          />
          <img
            src="https://www.pikpng.com/pngl/b/259-2599075_gear-user-account-person-configure-control-comments-security.png"
            alt="Użytkownik"
            className="user-icon"
            onClick={goToUserProfile}
          />
          {showSettings && (
            <div className="settings-menu">
              <ul>
                <li>Opcja 1</li>
                <li>Opcja 2</li>
                <li>Opcja 3</li>
              </ul>
            </div>
          )}
        </div>
      </div>
      
      {/* Zawartość strony */}
      <h1>Kluby w Lidze {ligaId}</h1>
      {kluby.length === 0 ? (
        <p>Brak danych</p>
      ) : (
        <ul className="kluby-list">
          {kluby.map((klub, index) => (
            <li key={index} className="klub-item">
              <div className="klub-info">
                    <span className="klub-logo">
                      <img src={klub.logo_url} alt={klub.nazwaKlubu} />
                    </span>
                <strong> - </strong> {klub.nazwaKlubu || "Brak"}<br />
              </div>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default LigaKluby;