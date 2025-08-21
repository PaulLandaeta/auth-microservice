import { useNavigate } from "react-router-dom";
import { useState } from "react";
import AuthLayout from "./AuthLayout";
import SocialLogin from "../../components/SocialLogin";
import toast from "react-hot-toast"; 

export default function ResetPassword() {

  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const navigate = useNavigate();

  const handleReset = async () => {

    if (newPassword !== confirmPassword) {
      toast.error("Passwords do not match");
      return;
    }

    toast.success("Password reset successful!");
    navigate("/login");
  }

  return (
  <AuthLayout position="left">
    <h2>Reset Password</h2>

    <form className="login-form" onSubmit={(e) => { e.preventDefault(); handleReset(); }}>
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