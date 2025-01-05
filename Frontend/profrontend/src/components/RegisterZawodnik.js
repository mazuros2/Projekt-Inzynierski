import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../cssFolder/RegisterUser.css";
import Navbar from '../components/Navbar';

const RejestracjaZawodnika = () => {
  const navigate = useNavigate();
  const [kraje, setKraje] = useState([]);
  const [kluby, setKluby] = useState([]);
  const [pozycje, setPozycje] = useState([]);
  const [formData, setFormData] = useState({
    imie: "",
    nazwisko: "",
    email: "",
    login: "",
    haslo: "",
    pesel: "",
    dataUrodzenia: "",
    waga: "",
    wzrost: "",
    pozycjaId: null,
    klubId: null,
    krajePochodzenia: [],
  });
  const [showCountryList, setShowCountryList] = useState(false);
  const [showClubList, setShowClubList] = useState(false);
  const [showPositionList, setShowPositionList] = useState(false);
  
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

    
    axios
      .get("http://localhost:8080/api/pozycja/getpozycje", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setPozycje(response.data);
      })
      .catch((error) => {
        console.error("Error fetching positions:", error);
      });
  }, [navigate]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevState) => ({ ...prevState, [name]: value }));
  };

  const handleClubSelect = (id, nazwa) => {
    setFormData((prevState) => ({ ...prevState, klubId: id }));
    setShowClubList(false);
    alert(`Wybrano klub: ${nazwa}`);
  };

  const handleCountrySelect = (id, nazwa) => {
    setFormData((prevState) => ({
      ...prevState,
      krajePochodzenia: [...prevState.krajePochodzenia, id],
    }));
    alert(`Dodano kraj: ${nazwa}`);
  };

  const handlePositionSelect = (id, nazwa) => {
    setFormData((prevState) => ({ ...prevState, pozycjaId: id }));
    setShowPositionList(false);
    alert(`Wybrano pozycję: ${nazwa}`);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const token = sessionStorage.getItem("token");

    axios
      .post("http://localhost:8080/api/admin/createZawodnik", formData, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        alert("Zawodnik został zarejestrowany pomyślnie!");
        navigate("/");
      })
      .catch((error) => {
        console.error("Błąd podczas rejestracji zawodnika:", error);
        alert("Nie udało się zarejestrować zawodnika. Sprawdź dane i spróbuj ponownie.");
      });
  };

  return (
  <div>
    <Navbar/>
   
   <h1>Rejestracja Zawodnika</h1>
<form onSubmit={handleSubmit} className="form-container">
  <div className="form-group">
    <label>Imię:</label>
    <input className="input-register"
      type="text"
      name="imie"
      placeholder="Imię"
      onChange={handleInputChange}
      required
    />
  </div>
  <div className="form-group">
    <label>Nazwisko:</label>
    <input className="input-register"
      type="text"
      name="nazwisko"
      placeholder="Nazwisko"
      onChange={handleInputChange}
      required
    />
  </div>
  <div className="form-group">
    <label>Email:</label>
    <input className="input-register"
      type="email"
      name="email"
      placeholder="Email"
      onChange={handleInputChange}
      required
    />
  </div>
  <div className="form-group">
    <label>Login:</label>
    <input className="input-register"
      type="text"
      name="login"
      placeholder="Login"
      onChange={handleInputChange}
      required
    />
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
      required
    />
  </div>
  <div className="form-group">
    <label>Data Urodzenia:</label>
    <input className="input-register"
      type="date"
      name="dataUrodzenia"
      onChange={handleInputChange}
      required
    />
  </div>
  <div className="form-group">
    <label>Waga:</label>
    <input className="input-register"
      type="number"
      name="waga"
      placeholder="Waga (kg)"
      onChange={handleInputChange}
      required
    />
  </div>
  <div className="form-group">
    <label>Wzrost:</label>
    <input className="input-register"
      type="number"
      name="wzrost"
      placeholder="Wzrost (cm)"
      onChange={handleInputChange}
      required
    />
  </div>
  <div className="form-group">
    <button
      type="button"
      className="toggle-button"
      onClick={() => setShowClubList(!showClubList)}
    >
      {showClubList ? "Ukryj listę klubów" : "Wybierz Klub"}
    </button>
    {showClubList && (
      <ul className="dropdown-list">
        {kluby.map((klub) => (
          <li
            key={klub.id}
            onClick={() => handleClubSelect(klub.id, klub.nazwaKlubu)}
          >
            {klub.nazwaKlubu}
          </li>
        ))}
      </ul>
    )}
  </div>
  <div className="form-group">
    <button
      type="button"
      className="toggle-button"
      onClick={() => setShowPositionList(!showPositionList)}
    >
      {showPositionList ? "Ukryj listę pozycji" : "Wybierz Pozycję"}
    </button>
    {showPositionList && (
      <ul className="dropdown-list">
        {pozycje.map((pozycja) => (
          <li
            key={pozycja.id}
            onClick={() => handlePositionSelect(pozycja.id_Pozycja, pozycja.nazwa_pozycji)}
          >
            {pozycja.nazwa_pozycji}
          </li>
        ))}
      </ul>
    )}
  </div>
  <div className="form-group">
    <button
      type="button"
      className="toggle-button"
      onClick={() => setShowCountryList(!showCountryList)}
    >
      {showCountryList ? "Ukryj listę krajów" : "Dodaj Kraj Pochodzenia"}
    </button>
    {showCountryList && (
      <ul className="dropdown-list">
        {kraje.map((kraj) => (
          <li
            key={kraj.id_Kraj}
            onClick={() => handleCountrySelect(kraj.id_Kraj, kraj.nazwa)}
          >
            {kraj.nazwa}
          </li>
        ))}
      </ul>
    )}
  </div>
  <button type="submit" className="submit-button">Zarejestruj Zawodnika</button>
</form>

    </div>
    
  );
};

export default RejestracjaZawodnika;