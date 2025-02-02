import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Navbar from '../components/Navbar';
import "../cssFolder/RegisterUser.css";

const RejestracjaSkauta = () => {
  const navigate = useNavigate();
  const [kraje, setKraje] = useState([]);
  const [formData, setFormData] = useState({
    imie: "",
    nazwisko: "",
    email: "",
    login: "",
    haslo: "",
    pesel: "",
    dataUrodzenia: "",
    idKlub: null,
    krajePochodzenia: [],
  });
  const [showCountryList, setShowCountryList] = useState(false);
  const [errors, setErrors] = useState({});
  
  useEffect(() => {
    const token = sessionStorage.getItem("token");
    if (!token) {
      console.error("Brak tokena. Przekierowanie do logowania.");
      navigate("/logowanie");
      return;
    }

    // Pobranie listy krajów
    axios
      .get(`${process.env.REACT_APP_API_URL}/api/krajpochodzenia/getkraje`, {
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


  // Zapis wybranego kraju
  const handleCountrySelect = (id, nazwa) => {
    setFormData((prevState) => ({
      ...prevState,
      krajePochodzenia: [...prevState.krajePochodzenia, id],
    }));
    alert(`Dodano kraj: ${nazwa}`);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const token = sessionStorage.getItem("token");
  
    axios
      .post(`${process.env.REACT_APP_API_URL}/api/menadzer/createSkaut`, formData, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        alert("Zawodnik został zarejestrowany pomyślnie!");
        navigate("/");
      })
      .catch((error) => {
        if (error.response && error.response.data.errors) {
          setErrors(error.response.data.errors);
        } else {
          console.error("Błąd podczas rejestracji zawodnika:", error);
          alert("Nie udało się zarejestrować zawodnika. Sprawdź dane i spróbuj ponownie.");
        }
      });
  };

  return (
    <div>
      <Navbar/>

      <h1>Rejestracja Skauta</h1>
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
    {errors.imie && <p className="error-message-form">{errors.imie}</p>}
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
    {errors.nazwisko && <p className="error-message-form">{errors.nazwisko}</p>}
  </div>
        <div className="form-group">
    <label>Email:</label>
    <input className="input-register"
      type="email"
      name="email"
      placeholder="Email"
      value={formData.email}
      onChange={handleInputChange}
      required
    />
    {errors.email && <p className="error-message-form">{errors.email}</p>}
  </div>
  <div className="form-group">
    <label>Login:</label>
    <input className="input-register"
      type="text"
      name="login"
      placeholder="Login"
      value={formData.login}
      onChange={handleInputChange}
      required
    />
    {errors.login && <p className="error-message-form">{errors.login}</p>}
  </div>
  <div className="form-group">
    <label>Hasło:</label>
    <input className="input-register"
      type="password"
      name="haslo"
      placeholder="Hasło"
      onChange={handleInputChange}
      required
    />
  </div>
  <div className="form-group">
    <label>PESEL:</label>
    <input className="input-register"
      type="text"
      name="pesel"
      placeholder="PESEL"
      onChange={handleInputChange}
      value={formData.pesel}
      required
    />
    {errors.pesel && <p className="error-message-form">{errors.pesel}</p>}
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
        <button type="submit" className="submit-button">Zarejestruj Skauta</button>
      </form>
    </div>
  );
};

export default RejestracjaSkauta;