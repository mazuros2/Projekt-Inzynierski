// import React, { useEffect, useState } from 'react';
// import axios from 'axios';
//
// const WszystkieKluby = () => {
//   const [kluby, setKluby] = useState([]);
//
//   useEffect(() => {
//     const authHeader = 'Basic ' + btoa('admin:admin');
//     axios.get('http://localhost:8080/kluby', {
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
//   }, []);
//
//   return (
//     <div>
//       <h1>Wszystkie Kluby</h1>
//       {kluby.length === 0 ? (
//         <p>Brak danych</p>
//       ) : (
//         <ul>
//           {kluby.map((klub, index) => (
//             <li key={index}>
//               <strong>Nazwa klubu:</strong> {klub.nazwaKlubu || "Brak"}<br />
//               <strong>Rok założenia:</strong> {klub.rokZalozenia || "Brak"}<br />
//               <strong>Obecna liga klubu:</strong> {klub.ligaNazwaLigi || "Brak"}<br />
//             </li>
//           ))}
//         </ul>
//       )}
//     </div>
//   );
// };
//
// export default WszystkieKluby;

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './WszystkieKluby.css'; // Import CSS

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
      <div className="kluby-container">
        <h1>Wszystkie Kluby</h1>
        {kluby.length === 0 ? (
            <p>Brak danych</p>
        ) : (
            <ul className="kluby-list">
              {kluby.map((klub, index) => (
                  <li key={index} className="klub-item">
                    <div className="klub-info">
                <span className="klub-logo">
                  {/* Tu możesz dodać logo klubu, np. ikona */}
                  <img src="URL_DO_LOGO" alt={klub.nazwaKlubu} />
                </span>
                      <div className="klub-details">
                        <strong>{klub.nazwaKlubu || "Brak"}</strong><br />
                        Rok założenia: {klub.rokZalozenia || "Brak"}<br />
                        Liga: {klub.ligaNazwaLigi || "Brak"}
                      </div>
                    </div>
                  </li>
              ))}
            </ul>
        )}
      </div>
  );
};

export default WszystkieKluby;
