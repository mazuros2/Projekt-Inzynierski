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

function App() {
  return (
<Router>
      <Routes>
        <Route path="/" element={<WszystkieKluby />} /> 
        <Route path="/user-profile" component={UserProfile} />
        <Route path="/klub/:id" element={<Klub />} />
        <Route path=":ligaId/kluby" element={<LigaKluby />} />
        <Route path="/ligii" element={<Liga />} /> 
        <Route path="/addliga" element={<AddLiga />} /> 
        <Route path="/trofeum/mistrzpolski" element={<MistrzowiePolski />} /> 
        <Route path="/trofeum/pucharpolski" element={<PucharPolski />} /> 
        <Route path="/logowanie" element={<StronaLogowania />} /> 
      </Routes>
    </Router>
  );
}

export default App;