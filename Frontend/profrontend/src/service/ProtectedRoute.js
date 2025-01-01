import React from "react";
import { Navigate } from "react-router-dom";
import { isUserInRole } from "./authService";

const ProtectedRoute = ({ children, allowedRoles }) => {
  const hasAccess = isUserInRole(allowedRoles);

  if (!hasAccess) {
    alert("Nie masz uprawnień do tej strony!");
    return <Navigate to="/" replace />; 
  }

  return children;
};

export default ProtectedRoute;
