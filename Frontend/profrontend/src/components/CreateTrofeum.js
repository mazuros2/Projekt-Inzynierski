import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import jwtDecode from 'jwt-decode';
import '../cssFolder/CreateTrofeum.css'; 
import '../cssFolder/Navbar.css'; 

const CreateTrofeum = () => {
  const navigate = useNavigate();
  const [token, setToken] = useState(sessionStorage.getItem("token"));
  const [showSettings, setShowSettings] = useState(false);
  const [kluby, setKluby] = useState([]);
  const [showClubList, setShowClubList] = useState(false);
  const [formData, setFormData] = useState({
    nazwa: "",
    dataZdobycia: "",
    klubId: null,
  });

  
  useEffect(() => {
    if (!token) {
      console.error("Brak tokena. Przekierowanie do logowania.");
      navigate("/logowanie");
      return;
    }

    axios
      .get("http://localhost:8080/kluby", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setKluby(response.data);
      })
      .catch((error) => {
        console.error("Error fetching clubs:", error);
      });
  }, [token, navigate]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevState) => ({ ...prevState, [name]: value }));
  };

  const handleClubSelect = (id, nazwa) => {
    setFormData((prevState) => ({ ...prevState, klubId: id }));
    setShowClubList(false);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .post(
        "http://localhost:8080/api/trofeum/createTrofeum",
        formData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then(() => {
        alert("Trofeum zostało utworzone.");
        navigate("/"); 
      })
      .catch((error) => {
        console.error("Błąd podczas tworzenia trofeum:", error);
        alert("Nie udało się utworzyć trofeum.");
      });
  };

  const handleLogout = () => {
    localStorage.removeItem('token'); 
    sessionStorage.removeItem('token'); 
    navigate('/logowanie'); 
  };

  const toggleSettings = () => {
    setShowSettings(!showSettings);
  };
  
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

const goToUserProfile = () => {
    const userId = getUserIdFromToken(); 
    if (userId) {
      navigate(`/user-profile/${userId}`);
    } else {
      console.error("Nie udało się pobrać ID użytkownika z tokena.");
    }
  };


  return (
    <div>
    <div className="navbar">
        <Link to="/">
        <img
        src="https://lh3.googleusercontent.com/proxy/4C4zlh5y6xvZC7MWNsG_99nE1x8yqQnSczaCD2cUy4xlvPOQFcm5vLMoEhrcczwjBcfADm4La8Li__oU9Gzy1Whmwpj1U0BvwG6FlMpj6y7cQuI4IfftojBNTeKQocivQu7lbKfiKvXW30jdeizyGN6AHdIUSpc7mWw1"
        alt="Logo"
            className="navbar-logo"/>
        </Link>
        <h1 className="navbar-title"></h1>          
        <div className="icons-container">
            <img
             src="https://icons.veryicon.com/png/o/miscellaneous/iview30-ios-style/ios-menu-4.png"
            alt="Ustawienia"
            className="settings-icon"
            onClick={toggleSettings}/>
            <img
            src="https://www.pikpng.com/pngl/b/259-2599075_gear-user-account-person-configure-control-comments-security.png"
                alt="Użytkownik"
                className="user-icon"
                onClick={goToUserProfile}/>
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
            
    <h2>Dodaj nowe trofeum</h2>
    
    <div className="create-trofeum-container">
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="nazwa">Nazwa trofeum:</label>
          <input
            type="text"
            id="nazwa"
            name="nazwa"
            value={formData.nazwaTrofeum}
            onChange={handleInputChange}
            required/>
        </div>

        <div className="form-group">
          <label htmlFor="dataZdobycia">Data zdobycia:</label>
          <input
            type="date"
            id="dataZdobycia"
            name="dataZdobycia"
            value={formData.dataZdobycia}
            onChange={handleInputChange}
            required/>
        </div>

        <div className="form-group">
          <label htmlFor="klubId">Wybierz klub:</label>
          <button
            type="button"
            className="toggle-button"
            onClick={() => setShowClubList(!showClubList)}
          >
            {showClubList ? "Ukryj listę klubów" : "Pokaż listę klubów"}
          </button>
          {showClubList && (
            <ul className="dropdown-list">
              {kluby.map((klub) => (
                <li
                  key={klub.id}
                  onClick={() => handleClubSelect(klub.id, klub.nazwaKlubu)}>
                  {klub.nazwaKlubu}
                </li>
              ))}
            </ul>
          )}
        </div>

        <button type="submit" className="submit-button">Dodaj trofeum</button>
      </form>
    </div>
    </div>
  );
};

export default CreateTrofeum;
