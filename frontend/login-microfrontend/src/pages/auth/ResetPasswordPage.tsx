import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import AuthLayout from './AuthLayout';
import SocialLogin from '../../components/SocialLogin';

export default function ResetPasswordPage() {
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const navigate = useNavigate();

  const handleReset = (e: React.FormEvent) => {
    e.preventDefault();
    if (newPassword !== confirmPassword) {
      alert("Las contraseñas no coinciden");
      return;
    }
    // Aquí iría la lógica para resetear la contraseña (API call, etc.)
    alert("¡Contraseña restablecida exitosamente!");
    navigate("/login");
  };

  return (
    <AuthLayout position="left">
      <h2>Reset Password</h2>
      <form className="login-form" onSubmit={handleReset}>
        <input
          type="password"
          placeholder="New password"
          value={newPassword}
          onChange={e => setNewPassword(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="Confirm password"
          value={confirmPassword}
          onChange={e => setConfirmPassword(e.target.value)}
          required
        />
        <button type="submit" className="login-btn">
          Reset Password
        </button>
      </form>
      <SocialLogin />
    </AuthLayout>
  );
}