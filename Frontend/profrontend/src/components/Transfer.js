import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";

const Transfer = () => {
  const { id } = useParams(); // Pobierz ID zawodnika z URL
  const [kwota, setKwota] = useState("");
  const [idKlubOd, setIdKlubOd] = useState(null); // Automatycznie pobrany klub
  const [idKlubDo, setIdKlubDo] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

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
  );
};

export default Transfer;
