import { useNavigate } from "react-router-dom";
import SocialLogin from "../../components/SocialLogin";
import upbLogo from "/upblogo.jpeg";
import figuraIzq from "../../assets/fotito.png";
import figuraDer from "../../assets/fotito.png";

export default function ForgotPasswordPage({ position = "center" }) {
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
          <h2>Forgot Password</h2>

          <form className="login-form">
            <input type="email" placeholder="Username o Email" required />
            <button type="submit" className="login-btn">
              Next
            </button>
            <div className="forgot-link">
              <span
                onClick={() => {
                  navigate("/login");
                }}
              >
                Back to Login?
              </span>
            </div>
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
  );
}
