import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useNavigate, useParams } from "react-router-dom";
import '../cssFolder/Navbar.css';

const Transfer = () => {
  const { id } = useParams(); // Pobierz ID zawodnika z URL
  const [kwota, setKwota] = useState("");
  const [idKlubOd, setIdKlubOd] = useState(null); // Automatycznie pobrany klub
  const [idKlubDo, setIdKlubDo] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();
  const [showSettings, setShowSettings] = useState(false);

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
    const fetchKlubId = async () => {
      try {
        const token = sessionStorage.getItem("token");
        if (!token) {
          setError("Brak tokena. Zaloguj się ponownie.");
          return;
        }

        const response = await axios.get(`http://localhost:8080/findKlubIdByZawodnik/${id}`, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });

        console.log(response.data); // Sprawdź, co zwraca backend

        if (response.data && response.data.id) {
          setIdKlubOd(response.data.id);
        } else {
          setError("Nie znaleziono klubu dla tego zawodnika.");
        }
      } catch (error) {
        console.error("Błąd przy pobieraniu ID klubu zawodnika:", error);
        setError("Wystąpił problem podczas pobierania danych klubu.");
      }
    };

    fetchKlubId();
  }, [id]);

  const handleSubmit = (e) => {
    e.preventDefault();
    const token = sessionStorage.getItem("token");

    if (!token) {
      setError("Brak tokena. Zaloguj się ponownie.");
      return;
    }

    const transferRequest = {
      kwota: parseFloat(kwota),
      idZawodnik: Number(id),
      idKlubOd: Number(idKlubOd),
      idKlubDo: Number(idKlubDo),
    };

    axios
      .post("http://localhost:8080/sendTransfer", transferRequest, {
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        },
      })
      .then(() => {
        alert("Transfer został wysłany z powodzeniem");
        navigate(-1); // Wróć do poprzedniej strony
      })
      .catch((error) => {
        console.error("Błąd przy wysyłaniu transferu:", error);
        setError("Wystąpił błąd przy wysyłaniu transferu.");
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

    
    <div>
      <h1>Wysłanie transferu</h1>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>Kwota: </label>
          <input
            type="number"
            value={kwota}
            onChange={(e) => setKwota(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Klub od (ID): </label>
          <span>{idKlubOd || "Ładowanie..."}</span>
        </div>
        <div>
          <label>Klub do (ID): </label>
          <input
            type="number"
            value={idKlubDo}
            onChange={(e) => setIdKlubDo(e.target.value)}
            required
          />
        </div>
        <button type="submit" disabled={!idKlubOd}>
          Wyślij transfer
        </button>
      </form>
      <button onClick={() => navigate(-1)}>Anuluj</button>
    </div>
    </div>
  );
};

export default Transfer;
