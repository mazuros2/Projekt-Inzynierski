import React, { useEffect, useState } from "react";
import axios from "axios";
import jwtDecode from "jwt-decode"; 
import { useNavigate, useParams } from "react-router-dom";
import '../cssFolder/Transfer.css';
import Navbar from '../components/Navbar';

const Transfer = () => {
  const { id } = useParams();
  const [kwota, setKwota] = useState("");
  const [idKlubOd, setIdKlubOd] = useState(null);
  const [nazwaKlubOd, setNazwaKlubOd] = useState(null);
  const [nazwaKlubDo, setNazwaKlubDo] = useState(null);
  const [idKlubDo, setIdKlubDo] = useState("");
  const [error, setError] = useState("");
  const [role, setRole] = useState(null);
  const [userId, setUserId] = useState(null);
  const navigate = useNavigate();

  const getUserInfo = () => {
    const token = sessionStorage.getItem("token");
    if (!token) return;
    try {
      const decodedToken = jwtDecode(token);
      setRole(decodedToken.role || null);
      setUserId(decodedToken.userId || null);
    } catch (error) {
      console.error("Błąd dekodowania tokena:", error);
      setRole(null);
      setUserId(null);
    }
  };

  useEffect(() => {
    const fetchRoleAndKlubData = async () => {
      getUserInfo();
      const token = sessionStorage.getItem("token");
      if (!token) {
        setError("Brak tokena. Zaloguj się ponownie.");
        return;
      }

      try {
        const response = await axios.get(`http://localhost:8080/findKlubIdByZawodnik/${id}`, {
          headers: { Authorization: `Bearer ${token}` }
        });

        if (response.data?.id) setIdKlubOd(response.data.id);
        else setError("Nie znaleziono klubu dla zawodnika.");

        if (response.data?.nazwaOd) setNazwaKlubOd(response.data.nazwaOd);
        else setError("Nie znaleziono nazwy klubu od.");
        
      } catch (error) {
        console.error("Błąd przy pobieraniu danych:", error);
        setError("Wystąpił problem podczas pobierania danych.");
      }
    };

    fetchRoleAndKlubData();
  }, [id]);

  useEffect(() => {
    const fetchIdKlubuMenadzera = async () => {
      if (role === "ROLE_MENADZER_KLUBU" && userId) {
        try {
          const token = sessionStorage.getItem("token");
          const response = await axios.get(`http://localhost:8080/getIdKlubuMenadzera/${userId}`, {
            headers: { Authorization: `Bearer ${token}` }
          });

          if (response.data?.id) setIdKlubDo(response.data.id);
          else setError("Nie znaleziono klubu dla menedżera.");

          if (response.data?.nazwaDo) setNazwaKlubDo(response.data.nazwaDo);
          else setError("Nie znaleziono nazwy klubu do.");
          
        } catch (error) {
          console.error("Błąd przy pobieraniu ID klubu menedżera:", error);
          setError("Wystąpił problem podczas pobierania danych klubu.");
        }
      }
    };

    if (role === "ROLE_MENADZER_KLUBU") {
      fetchIdKlubuMenadzera();
    }
  }, [role, userId]);

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
        navigate(-1);
      })
      .catch((error) => {
        console.error("Błąd przy wysyłaniu transferu:", error);
        setError("Wystąpił błąd przy wysyłaniu transferu.");
      });
  };

  return (
    <div>
      <Navbar/>
      <h1>Wysłanie transferu</h1>

      <div className="transfer-container">
        <form onSubmit={handleSubmit} className="transfer-form">
          <div className="form-row">
            <label>Kwota: </label>
            <input
              type="number"
              value={kwota}
              onChange={(e) => setKwota(e.target.value)}
              required
            />
          </div>

          <div className="form-row">
            <label>Klub od: </label>
            <span>{nazwaKlubOd || "Ładowanie..."}</span>
          </div>

          {role === "ROLE_ADMIN" ? (
            <div className="form-row">
              <label>Klub do (wpisz ID): </label>
              <input
                type="number"
                value={idKlubDo}
                onChange={(e) => setIdKlubDo(e.target.value)}
                required
              />
            </div>
          ) : (
            <div className="form-row">
              <label>Klub do: </label>
              <span>{nazwaKlubDo || "Ładowanie..."}</span>
            </div>
          )}

          <div className="form-buttons">
            <button type="button" onClick={() => navigate(-1)} className="cancel-button">
              Anuluj
            </button>
            <button type="submit" disabled={!idKlubOd} className="transfer-button">
              Wyślij transfer
            </button>
          </div>
        </form>
      </div>

      {error && <p className="error-message">{error}</p>}
    </div>
  );
};

export default Transfer;
