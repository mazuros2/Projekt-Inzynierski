import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../cssFolder/LoginForm.css";


const LoginForm = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const navigate = useNavigate(); // Hook do nawigacji

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:8080/api/authentication/authenticateUser",
        {
          login: username,
          haslo: password,
        }
      );

      console.log("Response data:", response.data); // Logujemy odpowiedź serwera

      // Upewniamy się, że token istnieje
      const token = response.data.jwtToken; // Poprawione odniesienie do tokena

      if (token) {
        sessionStorage.setItem("token", token); // Zapisujemy token
        setError("");
        alert("Logowanie zakończone sukcesem!");
        navigate("/"); // Przekierowanie
      } else {
        console.error("Brak tokena:", response.data);
        throw new Error("Brak tokena w odpowiedzi.");
      }
    } catch (err) {
      console.error(err);
      setError("Nieprawidłowe dane logowania.");
    }
  };

  return (
    <div className="login-container">
      <header className="login-header">
        <img
          src="https://lh3.googleusercontent.com/proxy/4C4zlh5y6xvZC7MWNsG_99nE1x8yqQnSczaCD2cUy4xlvPOQFcm5vLMoEhrcczwjBcfADm4La8Li__oU9Gzy1Whmwpj1U0BvwG6FlMpj6y7cQuI4IfftojBNTeKQocivQu7lbKfiKvXW30jdeizyGN6AHdIUSpc7mWw1"
          alt="Logo"
          className="navbar-logo"
        />
        <div className="navigation">
          <button className="nav-button">Strona główna</button>
          <button className="nav-button active">Logowanie</button>
        </div>
      </header>

      <div className="login-box">
        <form className="login-form" onSubmit={handleLogin}>
          <div className="input-group">
            <label htmlFor="username">Login</label>
            <div className="input-wrapper">
              <span className="icon">📧</span>
              <input
                type="text"
                id="username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                placeholder="Login"
                required
              />
            </div>
          </div>

          <div className="input-group">
            <label htmlFor="password">Hasło</label>
            <div className="input-wrapper">
              <span className="icon">🔑</span>
              <input
                type="password"
                id="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="Hasło"
                required
              />
            </div>
          </div>

          {error && <p className="error-message">{error}</p>}

          <button type="submit" className="login-button">
            Zaloguj
          </button>
        </form>
      </div>
    </div>
  );
};

export default LoginForm;
