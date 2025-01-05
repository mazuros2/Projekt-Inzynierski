import React, { useEffect, useState } from "react";
import axios from "axios";
import jwtDecode from "jwt-decode"; 
import { useNavigate, useParams } from "react-router-dom";
import '../cssFolder/Navbar.css';
import Navbar from '../components/Navbar';

const Transfer = () => {
  const { id } = useParams();
  const [kwota, setKwota] = useState("");
  const [idKlubOd, setIdKlubOd] = useState(null);
  const [idKlubDo, setIdKlubDo] = useState("");
  const [error, setError] = useState("");
  const [role, setRole] = useState(null); // Przechowywanie roli użytkownika
  const [userId, setUserId] = useState(null); // Przechowywanie ID użytkownika z tokena
  const navigate = useNavigate();



  const getUserInfo = () => {
    const token = sessionStorage.getItem("token");
    if (!token) return null;
    try {
      const decodedToken = jwtDecode(token);
      console.log("Decoded token:", decodedToken); // Debugowanie tokena
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
      getUserInfo(); // Pobierz dane użytkownika z tokena

      const token = sessionStorage.getItem("token");
      if (!token) {
        setError("Brak tokena. Zaloguj się ponownie.");
        return;
      }

      try {
        // Pobierz ID klubu "od"
        const response = await axios.get(`http://localhost:8080/findKlubIdByZawodnik/${id}`, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });

        if (response.data && response.data.id) {
          setIdKlubOd(response.data.id);
        } else {
          setError("Nie znaleziono klubu dla tego zawodnika.");
        }
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
            headers: {
              Authorization: `Bearer ${token}`
            }
          });

          if (response.data) {
            setIdKlubDo(response.data);
          } else {
            setError("Nie znaleziono klubu dla menedżera.");
          }
        } catch (error) {
          console.error("Błąd przy pobieraniu ID klubu menedżera:", error);
          setError("Wystąpił problem podczas pobierania danych klubu.");
        }
      }
    };

    fetchIdKlubuMenadzera();
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
              readOnly={role === "ROLE_MENADZER_KLUBU"} // Automatyczne ustawianie dla menedżera
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
