import React, { useState } from "react";
import axios from "axios";
import "../cssFolder/LoginForm.css";

const LoginForm = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [token, setToken] = useState(null);

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:8080/api/authentication/authenticateUser",
        {
          username: username,
          password: password,
        }
      );

      setToken(response.data.token);
      setError("");
      alert("Logowanie zakończone sukcesem!");
    } catch (err) {
      console.error(err);
      setError("Nieprawidłowe dane logowania.");
    }
  };

  return React.createElement(
    "div",
    { className: "login-container" },
    React.createElement(
      "header",
      { className: "login-header" },
      React.createElement("img", {
        src: "https://lh3.googleusercontent.com/proxy/4C4zlh5y6xvZC7MWNsG_99nE1x8yqQnSczaCD2cUy4xlvPOQFcm5vLMoEhrcczwjBcfADm4La8Li__oU9Gzy1Whmwpj1U0BvwG6FlMpj6y7cQuI4IfftojBNTeKQocivQu7lbKfiKvXW30jdeizyGN6AHdIUSpc7mWw1",
        alt: "Logo",
        className: "navbar-logo",
      }),
      React.createElement("h1", { className: "navbar-title" }, ""),
      React.createElement(
        "div",
        { className: "navigation" },
        React.createElement(
          "button",
          { className: "nav-button" },
          "Strona główna"
        ),
        React.createElement(
          "button",
          { className: "nav-button active" },
          "Logowanie"
        )
      )
    ),
    React.createElement(
      "div",
      { className: "login-box" },
      React.createElement(
        "form",
        { className: "login-form", onSubmit: handleLogin },
        React.createElement(
          "div",
          { className: "input-group" },
          React.createElement("label", { htmlFor: "username" }, "Login"),
          React.createElement(
            "div",
            { className: "input-wrapper" },
            React.createElement("span", { className: "icon" }, "📧"),
            React.createElement("input", {
              type: "text",
              id: "username",
              value: username,
              onChange: (e) => setUsername(e.target.value),
              placeholder: "Login",
              required: true,
            })
          )
        ),
        React.createElement(
          "div",
          { className: "input-group" },
          React.createElement("label", { htmlFor: "password" }, "Hasło"),
          React.createElement(
            "div",
            { className: "input-wrapper" },
            React.createElement("span", { className: "icon" }, "🔑"),
            React.createElement("input", {
              type: "password",
              id: "password",
              value: password,
              onChange: (e) => setPassword(e.target.value),
              placeholder: "Hasło",
              required: true,
            })
          )
        ),
        error &&
          React.createElement(
            "p",
            { className: "error-message" },
            error
          ),
        // React.createElement(
        //   "div",
        //   { className: "forgot-password" },
        //   React.createElement(
        //     "a",
        //     { href: "#" },
        //     "Zapomniałem hasła"
        //   )
        // ),
        React.createElement(
          "button",
          { type: "submit", className: "login-button" },
          "Zaloguj"
        )
      ),
      token &&
        React.createElement(
          "p",
          { className: "success-message" },
          `Zalogowano pomyślnie! Token: ${token}`
        )
    )
  );
};

export default LoginForm;
