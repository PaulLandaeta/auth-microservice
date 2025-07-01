import { useState } from "react";
import Login from "./components/Login";
import ModeSelector from "./components/ModeSelector";
import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import ForgotPasswordPage from "./pages/auth/ForgotPasswordPage";
import ResetPasswordPage from "./pages/auth/ResetPasswordPage";

function App() {
  const [mode, setMode] = useState("center");

  return (
    <div className="background-full">
      <div className="app-container">
        {/* <ModeSelector mode={mode} setMode={setMode} /> */}
        {/* <Login mode={mode} /> */}
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
          </Routes>
        </BrowserRouter>
      </div>
    </div>
  );
}

export default App;

