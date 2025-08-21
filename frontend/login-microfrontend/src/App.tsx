import { useState } from "react";
import "./App.css";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Login from "./components/Login";
import ForgotPasswordPage from "./pages/auth/ForgotPasswordPage";
import ResetPasswordPage from "./pages/auth/ResetPasswordPage";
import HomeTest from "./components/HomeTest";

function App() {
  const [mode, setMode] = useState("center");

  return (
    <div className="background-full">
      <div className="app-container">
        <BrowserRouter>
          <Routes>
            <Route
              path="/forgot-password"
              element={<ForgotPasswordPage />}
            />
            <Route
              path="/reset-password"
              element={<ResetPasswordPage />}
            />
            <Route
              path="/hometest"
              element={<HomeTest />}
            />
            <Route path="/" element={<Login />} />
            <Route path="/login" element={<Login />} />
            <Route path="/forgot-password" element={<ForgotPasswordPage />} />
            <Route path="/reset-password" element={<ResetPasswordPage />} />
            <Route path="*" element={<Navigate to="/" replace />} />
          </Routes>
        </BrowserRouter>
      </div>
    </div>
  );
}

export default App;
