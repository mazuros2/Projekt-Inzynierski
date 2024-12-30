import React from 'react';
import './App.css';
import Liga from './components/Liga';
import MistrzowiePolski from './components/MistrzowiePolski';
import PucharPolski from './components/PucharPolski';
import WszystkieKluby from './components/WszystkieKluby';
import Klub from './components/Klub';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LigaKluby from './components/LigaKluby';
import UserProfile from './components/UserProfile';
import AddLiga from './components/AddLiga';
import StronaLogowania from './components/LoginForm'
import ZawodnikDetails from './components/ZawodnikDetails.js'; 
import RegisterTrener from './components/RegisterTrener.js';
import RegisterZawodnik from './components/RegisterZawodnik.js';
import RegisterSkaut from './components/RegisterSkaut.js';
import RegisterMenadzer from './components/RegisterMenadzer.js';
import Trenerzy from './components/Trenerzy.js';
import TrenerDetails from './components/TrenerDetails.js';
import Zawodnicy from './components/Zawodnicy.js';
import ObserwowaniZawodnicy from './components/ObserwowaniZawodnicy.js';
import AdminPanel from './components/AdminPanel.js';
import ZmianaDanych from './components/ZmianaDanych.js';


function App() {
  return (
<Router>
      <Routes>
        <Route path="/zawodnicy/profil/:id" element={<ZawodnikDetails />} />
        <Route path="/" element={<WszystkieKluby />} /> 
        <Route path="/user-profile/:id" element={<UserProfile />} />
        <Route path="/klub/:id" element={<Klub />} />
        <Route path="/ligii/:ligaId/kluby" element={<LigaKluby />} />
        <Route path="/ligii" element={<Liga />} /> 
        <Route path="/addliga" element={<AddLiga />} /> 
        <Route path="/trofeum/mistrzpolski" element={<MistrzowiePolski />} /> 
        <Route path="/trofeum/pucharpolski" element={<PucharPolski />} /> 
        <Route path="/logowanie" element={<StronaLogowania />} />
        <Route path="/createZawodnik" element={<RegisterZawodnik />} />
        <Route path="/createTrener" element={<RegisterTrener />} />
        <Route path="/createSkaut" element={<RegisterSkaut />} />
        <Route path="/createMenadzer" element={<RegisterMenadzer />} />
        <Route path="/trenerzy" element={<Trenerzy />} />
        <Route path="/trener/profil/:id" element={<TrenerDetails />} />
        <Route path="/zawodnicy" element={<Zawodnicy />} />
        <Route path="/listaObserwowanych" element={<ObserwowaniZawodnicy />} />
        <Route path="/adminPanel" element={<AdminPanel />} />
        <Route path="/user-profile/zmianaDanych" element={<ZmianaDanych />} />
      </Routes>
    </Router>
  );
}

export default App;