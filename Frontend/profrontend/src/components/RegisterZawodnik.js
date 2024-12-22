import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { Link } from 'react-router-dom';

const RejestracjaZawodnika = () => {
  const navigate = useNavigate();
  const [showSettings, setShowSettings] = useState(false);
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
      <h1>Rejestracja Zawodnika</h1>
      <form onSubmit={handleSubmit}>
        <input type="text" name="imie" placeholder="Imię" onChange={handleInputChange} required />
        <input type="text" name="nazwisko" placeholder="Nazwisko" onChange={handleInputChange} required />
        <input type="email" name="email" placeholder="Email" onChange={handleInputChange} required />
        <input type="text" name="login" placeholder="Login" onChange={handleInputChange} required />
        <input type="password" name="haslo" placeholder="Hasło" onChange={handleInputChange} required />
        <input type="text" name="pesel" placeholder="PESEL" onChange={handleInputChange} required />
        <input type="date" name="dataUrodzenia" onChange={handleInputChange} required />
        <input type="number" name="waga" placeholder="Waga" onChange={handleInputChange} required />
        <input type="number" name="wzrost" placeholder="Wzrost" onChange={handleInputChange} required />

        <button type="button" onClick={() => setShowClubList(!showClubList)}>Wybierz Klub</button>
        {showClubList && kluby.map(klub => <div key={klub.id} onClick={() => handleClubSelect(klub.id, klub.nazwaKlubu)}>{klub.nazwaKlubu}</div>)}

        <button type="button" onClick={() => setShowPositionList(!showPositionList)}>Wybierz Pozycję</button>
        {showPositionList && pozycje.map(pozycja => <div key={pozycja.id} onClick={() => handlePositionSelect(pozycja.id_Pozycja, pozycja.nazwa_pozycji)}>{pozycja.nazwa_pozycji}</div>)}

        <button type="button" onClick={() => setShowCountryList(!showCountryList)}>Dodaj Kraj Pochodzenia</button>
        {showCountryList && kraje.map(kraj => <div key={kraj.id_Kraj} onClick={() => handleCountrySelect(kraj.id_Kraj, kraj.nazwa)}>{kraj.nazwa}</div>)}

        <button type="submit">Zarejestruj Zawodnika</button>
      </form>
    </div>
    </div>
  );
};

export default RejestracjaZawodnika;