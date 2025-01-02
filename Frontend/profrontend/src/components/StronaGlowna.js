import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import jwtDecode from 'jwt-decode';
import '../cssFolder/Navbar.css'; 
import '../cssFolder/StronaGlowna.css'; 


const StronaGlowna = () => {
    const navigate = useNavigate();
    const [showSettings, setShowSettings] = useState(false);
    const [zdobywcyPP, setPP] = useState([]);
    const [zdobywcyMP, setMP] = useState([]);

    const getUserIdFromToken = () => {
        const token = sessionStorage.getItem("token");
        if (!token) return null;
      
        try {
          const decodedToken = jwtDecode(token);
          return decodedToken.userId; 
        } catch (error) {
          console.error("Błąd dekodowania tokena:", error);
          return null;
        }
    };
    
    const toggleSettings = () => {
        setShowSettings(!showSettings);
    };
    
    const goToUserProfile = () => {
        const userId = getUserIdFromToken(); 
        if (userId) {
          navigate(`/user-profile/${userId}`);
        } else {
          console.error("Nie udało się pobrać ID użytkownika z tokena.");
        }
    };
    
    const handleLogout = () => {
        localStorage.removeItem('token'); 
        sessionStorage.removeItem('token');
        navigate('/logowanie');
    };

    useEffect(() => {
        const token = sessionStorage.getItem('token');
        if (!token) {
          console.error('Brak tokena. Przekierowanie do logowania.');
          navigate('/logowanie');
          return;
        }
    });



    return (
        <div>

        <div className="navbar">
                <Link to="/">
                  <img
                    src="https://lh3.googleusercontent.com/proxy/4C4zlh5y6xvZC7MWNsG_99nE1x8yqQnSczaCD2cUy4xlvPOQFcm5vLMoEhrcczwjBcfADm4La8Li__oU9Gzy1Whmwpj1U0BvwG6FlMpj6y7cQuI4IfftojBNTeKQocivQu7lbKfiKvXW30jdeizyGN6AHdIUSpc7mWw1"
                    alt="Logo"
                    className="navbar-logo"
                  />
                </Link>
                  <h1 className="navbar-title"></h1>
                  
                  <div className="icons-container">
                    <img
                      src="https://icons.veryicon.com/png/o/miscellaneous/iview30-ios-style/ios-menu-4.png"
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
                          <li onClick={() => navigate("/ligii")}>Ligii</li>
                          <li onClick={() => navigate('/kluby')}>Kluby</li>
                          <li onClick={() => navigate('/zawodnicy')}>Zawodnicy</li>
                          <li onClick={() => navigate('/trenerzy')}>Trenerzy</li>
                          <li onClick={() => navigate("/listaObserwowanych")}>Lista obserwowanych</li>
                          <li onClick={handleLogout}>Wyloguj</li>
                        </ul>
                      </div>
                      )}
                  </div>
              </div>




        </div>


    );
};

export default StronaGlowna;