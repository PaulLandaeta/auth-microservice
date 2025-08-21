import AuthLayout from "./AuthLayout";
import SocialLogin from "../../components/SocialLogin";
import upbLogo from "/upblogo.jpeg";
import figuraIzq from "../../assets/fotito.png";
import figuraDer from "../../assets/fotito.png";
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

export default function ResetPasswordPage({ position = "center" }) {
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const navigate = useNavigate();
  return (
    <div className="login-background">
          <div className="login-layout two-columns">
            {position == "left" && (
              <div className="figure-col">
                <img src={figuraIzq} alt="Figura izquierda" />
              </div>
            )}
    
            <div className="form-col">
              <img src={upbLogo} alt="UPB Logo" className="logo" />
              <h2>Reset Password</h2>
    
              <form className="login-form" onSubmit={(e) => {
                e.preventDefault();
                if (password && confirmPassword && password === confirmPassword) {
                  navigate('/hometest');
                }
              }}>
                <input 
                  type="password" 
                  placeholder="Password" 
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required 
                />
                <input 
                  type="password" 
                  placeholder="Confirm Password" 
                  value={confirmPassword}
                  onChange={(e) => setConfirmPassword(e.target.value)}
                  required 
                />
                <button 
                  type="submit" 
                  className="login-btn"
                  disabled={!password || !confirmPassword || password !== confirmPassword}
                >
                  Login
                </button>
              </form>
              <SocialLogin />
            </div>
    
            {position == "right" && (
              <div className="figure-col">
                <img src={figuraDer} alt="Figura derecha" />
              </div>
            )}
          </div>
        </div>
  )

export default function ResetPasswordPage() {
  return (
    <AuthLayout position="right">
      <h2>Reset Password</h2>

      <form className="login-form">
        <input type="password" placeholder="Password" required />
        <input type="password" placeholder="Confirm Password" required />
        <button type="submit" className="login-btn">
          Login
        </button>
      </form>

      <SocialLogin />
    </AuthLayout>
  );
}
