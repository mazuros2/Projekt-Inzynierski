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
 
  useEffect(() => {
    const token = sessionStorage.getItem("token");
    if (!token) {
      console.error("Brak tokena. Przekierowanie do logowania.");
      navigate("/logowanie");
      return;
    }

    // Pobranie listy krajów
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
      .post(
        "http://localhost:8080/api/menadzer/createSkaut",
        formData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((response) => {
        alert("Skaut został zarejestrowany pomyślnie!");
        navigate("/");
      })
      .catch((error) => {
        console.error("Błąd podczas rejestracji skauta:", error);
        alert("Nie udało się zarejestrować skauta. Sprawdź dane i spróbuj ponownie.");
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