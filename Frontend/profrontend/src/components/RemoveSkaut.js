import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Navbar from '../components/Navbar';
import "../cssFolder/RegisterUser.css";
import "../cssFolder/RegisterUser.css";


const ZwolnijSkautaAdmin = () => {
  const [kluby, setKluby] = useState([]);
  const [selectedKlub, setSelectedKlub] = useState(null);
  const [message, setMessage] = useState("");
  const [showClubList, setShowClubList] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const token = sessionStorage.getItem("token");

    if (!token) {
      console.error("Brak tokena. Przekierowanie do logowania.");
      return;
    }

    axios
      .get(`${process.env.REACT_APP_API_URL}/api/kluby/zwolnienieSkauta/getKlubyWithSkaut`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => setKluby(response.data))
      .catch((error) => console.error("Błąd pobierania klubów:", error));
  }, []);

  const handleClubSelect = (klub) => {
    setSelectedKlub(klub);
    setShowClubList(false);
    alert(`Wybrano klub: ${klub.nazwaKlubu}`);
  };

  const handleZwolnijSkauta = () => {
    if (!selectedKlub) {
      alert("Wybierz klub!");
      return;
    }

    const token = sessionStorage.getItem("token");

    axios
      .delete(`${process.env.REACT_APP_API_URL}/api/admin/zwolnijSkauta`, {
        headers: { Authorization: `Bearer ${token}` },
        data: { id: selectedKlub.id, nazwaKlubu: selectedKlub.nazwaKlubu }, 
      })
      .then((response) => setMessage(response.data))
      .catch((error) =>
        setMessage(error.response?.data || "Błąd przy zwalnianiu skauta!")
      );
      navigate("/adminPanel");
  };

  return (
    <div>
        <Navbar/>

        <h1>Zwolnij skauta z klubu</h1>
        <div className = "form-container">
        <div className="form-group">
          <button type="button" className="toggle-button" onClick={() => setShowClubList(!showClubList)}>
            {showClubList ? "Ukryj listę klubów" : "Pokaż listę klubów"}
          </button>
          {showClubList && (
            <ul className="dropdown-list">
              {kluby.map((klub) => (
                <li key={klub.id} onClick={() => handleClubSelect(klub)}>
                  {klub.nazwaKlubu}
                </li>
              ))}
            </ul>
          )}
        </div>

        <div className="form-group">
        {selectedKlub && (
        <div>
          <p>Wybrany klub: {selectedKlub.nazwaKlubu}</p>
          <button type="submit" className="submit-button" onClick={handleZwolnijSkauta}>Zwolnij skauta</button>
        </div>
        )}
        </div>
        {message && <p>{message}</p>}
        </div>
    </div>
  );
};

export default ZwolnijSkautaAdmin;
