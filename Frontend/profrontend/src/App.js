import React from 'react';
import './App.css';
import Liga from './components/Liga';
import Mistrzowiepolski from './components/MistrzowiePolski';
import PucharPolski from './components/PucharPolski';

function App() {
  return (
    <div className="App">
      <h1>Mistrzostwo Polski i kluby, które je wygrały</h1>
      <PucharPolski />
    </div>
  );
}

export default App;