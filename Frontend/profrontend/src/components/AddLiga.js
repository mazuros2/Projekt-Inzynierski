import React, { useState } from 'react';
import axios from 'axios';

const AddLiga = () => {
  const [formData, setFormData] = useState({
    nazwa_Ligi: '',
    poziom_Ligi: ''
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post(`${process.env.REACT_APP_API_URL}/addLiga`, {
      ...formData,
      poziom_Ligi: parseInt(formData.poziom_Ligi) 
    }, {
      headers: {
        'Authorization': 'Basic ' + btoa('admin:admin'),
        'Content-Type': 'application/json',
      }
    })
    .then(response => {
      console.log('Liga dodana:', response.data);
      setFormData({ nazwa_Ligi: '', poziom_Ligi: '' });
    })
    .catch(error => {
      console.error('Błąd przy dodawaniu ligi:', error);
    });
  };

  return (
    <div>
      <h1>Dodaj Ligę</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Nazwa Ligi:</label>
          <input 
            type="text" 
            name="nazwa_Ligi" 
            value={formData.nazwa_Ligi} 
            onChange={handleChange} 
            required 
          />
        </div>
        <div>
          <label>Poziom Ligi:</label>
          <input 
            type="number" 
            name="poziom_Ligi" 
            value={formData.poziom_Ligi} 
            onChange={handleChange} 
            required 
          />
        </div>
        <button type="submit">Dodaj Ligę</button>
      </form>
    </div>
  );
};

export default AddLiga;