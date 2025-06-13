import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import SocialLoginCentralizado from "./components/SocialLoginCentralizado";
import SocialLoginIzquierda from "./components/SocialLoginIzquierda";
import SocialLoginDerecha from "./components/SocialLoginDerecha";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<SocialLoginCentralizado />} />
        <Route path="/left" element={<SocialLoginIzquierda />} />
        <Route path="/right" element={<SocialLoginDerecha />} />
      </Routes>
    </Router>
  );
}

export default App;

