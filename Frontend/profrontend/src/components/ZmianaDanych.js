import React, { useState, useEffect } from "react";
import axios from "axios";
import jwtDecode from "jwt-decode";
import { Link, useNavigate } from "react-router-dom";
import '../cssFolder/Navbar.css'; 
import '../cssFolder/ZmianaDanych.css';


const EdycjaDanychUzytkownika = () => {
  const [formData, setFormData] = useState({
    login: "",
    haslo: "",
    email: "",
    profiloweURL: "",
  });
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");
  const [showSettings, setShowSettings] = useState(false);
  const navigate = useNavigate();
  
  
  const handleLogout = () => {
    sessionStorage.removeItem('token');
    navigate('/logowanie');
  };

  const toggleSettings = () => {
    setShowSettings(!showSettings);
  };

  const goToUserProfile = () => {
    navigate('/user-profile');
  };

  const getUserIdFromToken = () => {
      const token = sessionStorage.getItem("token");
      if (!token) return null;

      try {
        const decodedToken = jwtDecode(token);
        return decodedToken.userId;
      } catch (err) {
        console.error("Błąd dekodowania tokena:", err);
        return null;
      }
  };

  useEffect(() => {
    const userId = getUserIdFromToken();
    if (!userId) {
      console.error("Brak ID użytkownika w tokenie. Przekierowanie do logowania.");
      setError("Brak ID użytkownika w tokenie. Zaloguj się ponownie.");
      return;
    }

    const fetchUserData = async () => {
      try {
        const token = sessionStorage.getItem("token");
        const response = await axios.get(`http://localhost:8080/userDetails/${userId}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setFormData(response.data);
      } catch (err) {
        console.error("Błąd pobierania danych użytkownika:", err);
        setError("Nie udało się pobrać danych użytkownika.");
      }
    };

    fetchUserData();
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    const userId = getUserIdFromToken();

    e.preventDefault();
    const token = sessionStorage.getItem("token");
    if (!token) {
      setError("Brak tokena. Zaloguj się ponownie.");
      return;
    }

    try {
      await axios.put("http://localhost:8080/api/uzytkownik/zmienDane", formData, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setMessage("Dane użytkownika zostały zaktualizowane.");
      setError("");
      navigate(`/user-profile/${userId}`);
    } catch (err) {
      console.error("Błąd podczas aktualizacji danych użytkownika:", err);
      setError("Nie udało się zaktualizować danych użytkownika.");
    }
  };

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
                <li>Kluby</li>
                <li onClick={() => navigate('/zawodnicy')}>Zawodnicy</li>
                <li onClick={() => navigate('/trenerzy')}>Trenerzy</li>
                <li onClick={() => navigate("/listaObserwowanych")}>Lista obserwowanych</li>
                <li onClick={handleLogout}>Wyloguj</li>
              </ul>
            </div>
          )}
        </div>
    </div>
      
    <h1>Zmiana danych</h1>    

    <div className="edycja-danych-container">
      {message && <p className="success-message">{message}</p>}
      {error && <p className="error-message">{error}</p>}
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="login">Login:</label>
          <input className="input-data"
            type="text"
            id="login"
            name="login"
            value={formData.login}
            onChange={handleInputChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="haslo">Hasło:</label>
          <input className="input-data"
            type="password"
            id="haslo"
            name="haslo"
            value={formData.haslo}
            onChange={handleInputChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="email">Email:</label>
          <input className="input-data"
            type="email"
            id="email"
            name="email"
            value={formData.email}
            onChange={handleInputChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="profiloweURL">Profilowe URL:</label>
          <input className="input-data"
            type="text"
            id="profiloweURL"
            name="profiloweURL"
            value={formData.profiloweURL}
            onChange={handleInputChange}
          />
        </div>
        <button className="button-form-submit" type="submit">Zapisz zmiany</button>
      </form>
    </div>
    </div>
  );
};

export default EdycjaDanychUzytkownika;