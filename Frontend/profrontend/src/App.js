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
import Transfer from './components/Transfer';
import UserTransfers from './components/UserTransfers';
import ProtectedRoute from './service/ProtectedRoute.js';
import StronaGlowna from './components/StronaGlowna.js';
import CreateTrofeum from './components/CreateTrofeum.js';

function App() {
  return (
<Router>
      <Routes>
        <Route path="/" element={< StronaGlowna/>} />
        <Route path="/logowanie" element={<StronaLogowania />} />

        <Route path="/kluby" element={<WszystkieKluby />} /> 
        <Route path="/klub/:id" element={<Klub />} />

        <Route path="/user-profile/:id" element={<UserProfile />} />
        <Route path="/ligii/:ligaId/kluby" element={<LigaKluby />} />
        <Route path="/ligii" element={<Liga />} /> 
        <Route path="/addliga" element={<AddLiga />} /> 
        <Route path="/trofeum/mistrzpolski" element={<MistrzowiePolski />} /> 
        <Route path="/trofeum/pucharpolski" element={<PucharPolski />} /> 
       
        
        <Route path="/createZawodnik" 
        element={ <ProtectedRoute allowedRoles={["ROLE_ADMIN", "ROLE_MENADZER_KLUBU"]}>
                  <RegisterZawodnik />
                  </ProtectedRoute>} />
        
        <Route path="/createTrener" 
        element={ <ProtectedRoute allowedRoles={["ROLE_ADMIN", "ROLE_MENADZER_KLUBU"]}> 
                  <RegisterTrener /> 
                  </ProtectedRoute>} />
        
        <Route path="/createSkaut" 
        element={ <ProtectedRoute allowedRoles={["ROLE_ADMIN", "ROLE_MENADZER_KLUBU"]}> 
                  <RegisterSkaut /> 
                  </ProtectedRoute>} />
        
        <Route path="/createMenadzer" 
        element={ <ProtectedRoute allowedRoles={["ROLE_ADMIN"]}> 
                  <RegisterMenadzer /> 
                  </ProtectedRoute>} />

        <Route path="/createTrofeum" 
        element={ <ProtectedRoute allowedRoles={["ROLE_ADMIN"]}> 
                  <CreateTrofeum /> 
                  </ProtectedRoute>} />
        
        <Route path="/trenerzy" element={<Trenerzy />} />
        <Route path="/trener/profil/:id" element={<TrenerDetails />} />
        
        <Route path="/zawodnicy" element={<Zawodnicy />} />
        <Route path="/zawodnicy/profil/:id" element={<ZawodnikDetails />} />
        
        <Route path="/listaObserwowanych" 
        element={<ProtectedRoute allowedRoles={["ROLE_ADMIN", "ROLE_MENADZER_KLUBU","ROLE_SKAUT"]}>
          <ObserwowaniZawodnicy />
          </ProtectedRoute>  } />
        
        <Route path="/adminPanel" 
        element={ <ProtectedRoute allowedRoles={["ROLE_ADMIN", "ROLE_MENADZER_KLUBU"]}>
                  <AdminPanel /> 
                  </ProtectedRoute>} />
        
        <Route path="/user-profile/zmianaDanych" element={<ZmianaDanych />} />
        <Route path="/zawodnicy/:id/transfer" element={<Transfer />} />
        <Route path="/transfery" element={<UserTransfers />} />
      </Routes>
    </Router>
  );
}

export default App;