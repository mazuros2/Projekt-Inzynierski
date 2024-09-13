import React from 'react';
import './App.css';
import Liga from './components/Liga';
import Mistrzowiepolski from './components/MistrzowiePolski';
import PucharPolski from './components/PucharPolski';
import WszystkieKluby from './components/WszystkieKluby';
import Klub from './components/Klub';

function App() {
  return (
    <div className="App">
      <h1>Test</h1>
      <WszystkieKluby />
    </div>
  );
}

export default App;