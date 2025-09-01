import "./App.css";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import ForgotPasswordPage from "./pages/auth/ForgotPasswordPage";
import ResetPasswordPage from "./pages/auth/ResetPasswordPage";

function App() {
  return (
    <div className="background-full">
      <div className="app-container">
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Navigate to="/forgot-password" replace />} />
            <Route path="/forgot-password" element={<ForgotPasswordPage />} />
            <Route path="/reset-password" element={<ResetPasswordPage />} />
          </Routes>
        </BrowserRouter>
      </div>
    </div>
  );
}

export default App;

// no-op: ci test 2025-08-31T19:03:32
