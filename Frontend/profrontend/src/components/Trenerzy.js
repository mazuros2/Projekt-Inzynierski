import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import '../cssFolder/Navbar.css'; 
import '../cssFolder/ZawodnicyiTrenerzyFilter.css';

const Trenerzy = () => {
  const [trenerzy, setTrenerzy] = useState([]);
  const [showSettings, setShowSettings] = useState(false);
  const [kraje, setKraje] = useState([]); 
  const [filterOptions, setFilterOptions] = useState({
      region: "",
      kraj: "",
  });

  const navigate = useNavigate();
  const [currentPage, setCurrentPage] = useState(1);
  const trenerzyPerPage = 12;

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
    const token = sessionStorage.getItem('token');
    if (!token) {
      console.error('Brak tokena. Przekierowanie do logowania.');
      navigate('/logowanie');
      return;
    }

    axios
      .get('http://localhost:8080/trenerzy', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setTrenerzy(response.data);
      })
      .catch((error) => {
        console.error('Error fetching trainers:', error);
      });

      axios
      .get("http://localhost:8080/api/krajpochodzenia/getkraje", {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        setKraje(response.data);
      })
      .catch((error) => {
        console.error("Błąd podczas pobierania krajów:", error);
      });

  }, [navigate]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFilterOptions((prevState) => ({ ...prevState, [name]: value }));
  };

  const filterTrenerzy = () => {
    return trenerzy.filter((trener) => {
      const {region, kraj, imie, nazwisko } = filterOptions;
      const matchesRegion = region ? trener.krajPochodzenia.some((kraj) => 
        kraj.region?.toLowerCase().includes(region.toLowerCase())) : true;

      const matchesKraj = kraj ? trener.krajPochodzenia.some((krajPochodzenia) => 
        krajPochodzenia.nazwa?.toLowerCase().includes(kraj.toLowerCase())) : true;
      
      const matchesImie = imie ? trener.imie?.toLowerCase().includes(imie.toLowerCase()) : true;
      const matchesNazwisko = nazwisko ? trener.nazwisko?.toLowerCase().includes(nazwisko.toLowerCase()) : true;
      return matchesRegion && matchesKraj && matchesImie && matchesNazwisko;
    });
  };

  const getCurrentPageTrenerzy = () => {
    const filteredTrenerzy = filterTrenerzy();
    const startIndex = (currentPage - 1) * trenerzyPerPage;
    return filteredTrenerzy.slice(startIndex, startIndex + trenerzyPerPage);
  };

  const totalPages = Math.ceil(filterTrenerzy().length / trenerzyPerPage);

  const handlePageChange = (newPage) => {
    if (newPage >= 1 && newPage <= totalPages) {
      setCurrentPage(newPage);
    }
  };


  return (
    <div className="trenerzy-container">
      <div className="navbar">
        <Link to="/">
          <img
            src="https://lh3.googleusercontent.com/proxy/4C4zlh5y6xvZC7MWNsG_99nE1x8yqQnSczaCD2cUy4xlvPOQFcm5vLMoEhrcczwjBcfADm4La8Li__oU9Gzy1Whmwpj1U0BvwG6FlMpj6y7cQuI4IfftojBNTeKQocivQu7lbKfiKvXW30jdeizyGN6AHdIUSpc7mWw1"
            alt="Logo"
            className="navbar-logo"
          />
        </Link>
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
                <li onClick={() => navigate('/ligii')}>Ligii</li>
                <li >Kluby</li>
                <li onClick={() => navigate('/zawodnicy')}>Zawodnicy</li>
                <li onClick={() => navigate('/trenerzy')}>Trenerzy</li>
                <li onClick={() => navigate("/listaObserwowanych")}>Lista obserwowanych</li>
                <li onClick={handleLogout}>Wyloguj</li>
              </ul>
            </div>
          )}
        </div>
      </div>
      <h1>Lista Trenerów</h1>
      <div className="filters-container">
        <input type="text" name="imie" placeholder="Imię" onChange={handleInputChange} />
        <input type="text" name="nazwisko" placeholder="Nazwisko" onChange={handleInputChange} />
        <select name="region" onChange={handleInputChange}>
          <option value="">Wybierz region</option>
          {[...new Set(kraje?.map((kraj) => kraj.region))].map((region, index) => (
            <option key={index} value={region}>{region}</option>
          ))}
        </select>
        <select name="kraj" onChange={handleInputChange}>
          <option value="">Wybierz kraj</option>
          {kraje?.map((kraj) => (
            <option key={kraj.id_Kraj} value={kraj.nazwa}>{kraj.nazwa}</option>
          ))}
        </select>
      </div>   

      {getCurrentPageTrenerzy().length > 0 ? (
        <ul>
          {getCurrentPageTrenerzy().map((trener) => (
            <li key={trener.id} className="user-filter-container">
            <div className="user-filter-info">  
              <p><strong>Imię:</strong> {trener.imie}</p>
              <p><strong>Nazwisko:</strong> {trener.nazwisko}</p>
              <p><strong>Region:</strong> {trener.krajPochodzenia.map((kraj) => kraj.region).join(', ')}</p>
              <p><strong>Kraj:</strong> {trener.krajPochodzenia.map((kraj) => kraj.nazwa).join(', ')}</p>
              <p><strong>Licencja:</strong> {trener.licencjaTrenera || 'Brak danych'}</p>
              <p><strong>Klub:</strong> {trener.klub || 'Brak klubu'}</p>
              </div>
              <button className="user-filter-button" onClick ={() => navigate(`/trener/profil/${trener.id}`)}>Zobacz szczegóły</button>
            </li>
          ))}
        </ul>
      ) : (
        <p>Brak trenerów spełniających kryteria wyszukiwania.</p>
      )}

      <div className="pagination">
        <button className="pagination-button" onClick={() => handlePageChange(currentPage - 1)} disabled={currentPage === 1}>
          Poprzednia
        </button>
        <span className="pagination-info">Strona {currentPage} z {totalPages}</span>
        <button className="pagination-button" onClick={() => handlePageChange(currentPage + 1)} disabled={currentPage === totalPages}>
          Następna
        </button>
      </div>

    </div>
  );
};

export default Trenerzy;
