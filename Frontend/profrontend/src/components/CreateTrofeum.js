import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Navbar from '../components/Navbar';
import '../cssFolder/CreateTrofeum.css'; 

const CreateTrofeum = () => {
  const navigate = useNavigate();
  const [token, setToken] = useState(sessionStorage.getItem("token"));
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


  return (
    <div>
    <Navbar/>
            
    <h1 className="trofeum-h2">Dodaj nowe trofeum</h1>
    
    <div className="create-trofeum-container">
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="nazwa">Nazwa trofeum:</label>
          <input className="input-register"
            type="text"
            id="nazwa"
            name="nazwa"
            value={formData.nazwaTrofeum}
            onChange={handleInputChange}
            required/>
        </div>

        <div className="form-group">
          <label htmlFor="dataZdobycia">Data zdobycia:</label>
          <input className="input-register"
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
