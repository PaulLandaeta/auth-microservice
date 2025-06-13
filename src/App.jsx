import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import SocialLoginCentralizado from './components/SocialLoginCentralizado';
import SocialLoginIzquierda from './components/SocialLoginIzquierda';
import SocialLoginDerecha from './components/SocialLoginDerecha';
import fondito from './assets/fondito.png';

const appStyle = {
  height: '100vh',
  margin: 0,
  padding: 0,
  backgroundImage: `url(${fondito})`,
  backgroundRepeat: 'no-repeat',
  backgroundPosition: 'center center',
  backgroundSize: 'cover',

  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
};

function App() {
  return (
    <div style={appStyle}>
      <Router>
        <Routes>
          <Route path="/" element={<SocialLoginCentralizado />} />
          <Route path="/left" element={<SocialLoginIzquierda />} />
          <Route path="/right" element={<SocialLoginDerecha />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
