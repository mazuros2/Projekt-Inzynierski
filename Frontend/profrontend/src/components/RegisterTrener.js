import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { Link } from 'react-router-dom';
import "../cssFolder/RegisterUser.css";

const RejestracjaTrenera = () => {
  const navigate = useNavigate();
  const [showSettings, setShowSettings] = useState(false);
  const [kraje, setKraje] = useState([]);
  const [kluby, setKluby] = useState([]);
  const [formData, setFormData] = useState({
    imie: "",
    nazwisko: "",
    email: "",
    login: "",
    haslo: "",
    pesel: "",
    dataUrodzenia: "",
    licencjaTrenera: "",
    idKlub: null,
    krajePochodzenia: [],
  });
  const [showCountryList, setShowCountryList] = useState(false);
  const [showClubList, setShowClubList] = useState(false);

  const toggleSettings = () => {
    setShowSettings(!showSettings);
  };
  const goToUserProfile = () => {
    navigate('/user-profile');
  };

  const handleLogout = () => {
    localStorage.removeItem('token'); 
    sessionStorage.removeItem('token'); 
    navigate('/logowanie'); 
  };


  useEffect(() => {
    const token = sessionStorage.getItem("token");
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

   
    axios
      .get("http://localhost:8080/api/krajpochodzenia/getkraje", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setKraje(response.data);
      })
      .catch((error) => {
        console.error("Error fetching countries:", error);
      });
  }, [navigate]);

  // Obsługa zmiany danych w formularzu
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevState) => ({ ...prevState, [name]: value }));
  };

  // Zapis wybranego klubu
  const handleClubSelect = (id, nazwa) => {
    setFormData((prevState) => ({ ...prevState, idKlub: id }));
    setShowClubList(false);
    alert(`Wybrano klub: ${nazwa}`);
  };

  // Zapis wybranego kraju
  const handleCountrySelect = (id, nazwa) => {
    setFormData((prevState) => ({
      ...prevState,
      krajePochodzenia: [...prevState.krajePochodzenia, id],
    }));
    alert(`Dodano kraj: ${nazwa}`);
  };

  // Obsługa wysłania formularza
  const handleSubmit = (e) => {
    e.preventDefault();
    const token = sessionStorage.getItem("token");

    axios
      .post(
        "http://localhost:8080/api/admin/createTrener",
        formData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((response) => {
        alert("Trener został zarejestrowany pomyślnie!");
        navigate("/");
      })
      .catch((error) => {
        console.error("Błąd podczas rejestracji trenera:", error);
        alert("Nie udało się zarejestrować trenera. Sprawdź dane i spróbuj ponownie.");
      });
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

      <h1>Rejestracja Trenera</h1>
      <form onSubmit={handleSubmit} className="form-container">
      <div className="form-group">
          <label>Imię:</label>
          <input className="input-register"
            type="text"
            name="imie"
            value={formData.imie}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Nazwisko:</label>
          <input className="input-register"
            type="text"
            name="nazwisko"
            value={formData.nazwisko}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Email:</label>
          <input className="input-register"
            type="email"
            name="email"
            value={formData.email}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Login:</label>
          <input className="input-register"
            type="text"
            name="login"
            value={formData.login}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Hasło:</label>
          <input className="input-register"
            type="password"
            name="haslo"
            value={formData.haslo}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label>PESEL:</label>
          <input className="input-register"
            type="text"
            name="pesel"
            value={formData.pesel}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Data Urodzenia:</label>
          <input className="input-register"
            type="date"
            name="dataUrodzenia"
            value={formData.dataUrodzenia}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Licencja Trenera:</label>
          <input className="input-register"
            type="text"
            name="licencjaTrenera"
            value={formData.licencjaTrenera}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <button type="button" className="toggle-button" onClick={() => setShowClubList(!showClubList)}>
            {showClubList ? "Ukryj listę klubów" : "Pokaż listę klubów"}
          </button>
          {showClubList && (
            <ul className="dropdown-list">
              {kluby.map((klub) => (
                <li key={klub.id} onClick={() => handleClubSelect(klub.id, klub.nazwaKlubu)}>
                  {klub.nazwaKlubu}
                </li>
              ))}
            </ul>
          )}
        </div>
        <div className="form-group">
          <button type="button" className="toggle-button" onClick={() => setShowCountryList(!showCountryList)}>
            {showCountryList ? "Ukryj listę krajów" : "Pokaż listę krajów"}
          </button>
          {showCountryList && (
            <ul className="dropdown-list">
              {kraje.map((kraj) => (
                <li key={kraj.id_Kraj} onClick={() => handleCountrySelect(kraj.id_Kraj, kraj.nazwa)}>
                  {kraj.nazwa}
                </li>
              ))}
            </ul>
          )}
        </div>
        <button type="submit" className="submit-button">Zarejestruj Trenera</button>
      </form>
    </div>
  );
};

export default RejestracjaTrenera;