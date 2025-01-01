import jwtDecode from "jwt-decode";

const getUserRole = () => {
  const token = sessionStorage.getItem("token");
  if (!token) return null;
  try {
    const decodedToken = jwtDecode(token);
    return decodedToken.role; 
  } catch (error) {
    console.error("Błąd dekodowania tokena:", error);
    return null;
  }
};

const isUserInRole = (requiredRoles) => {
  const role = getUserRole();
  return requiredRoles.includes(role);
};

export { getUserRole, isUserInRole };