import LoginForm from "./LoginForm";
import SocialLogin from "./SocialLogin";
import upbLogo from "/upblogo.jpeg";
import figuraIzq from "../assets/upb-campus.jpg";
import figuraDer from "../assets/upb-campus.jpg";

const Login = ({ mode = "center" }) => {
  const renderContent = () => {
    switch (mode) {
      case "left-figure":
        return (
          <div className="login-layout two-columns">
            <div className="figure-col">
              <img src={figuraIzq} alt="Figura izquierda" />
            </div>
            <div className="form-col">
              <img src={upbLogo} alt="UPB Logo" className="logo" />
              <h2>Login</h2>
              <LoginForm />
              <SocialLogin />
            </div>
          </div>
        );
      case "right-figure":
        return (
          <div className="login-layout two-columns">
            <div className="form-col">
              <img src={upbLogo} alt="UPB Logo" className="logo" />
              <h2>Login</h2>
              <LoginForm />
              <SocialLogin />
            </div>
            <div className="figure-col">
              <img src={figuraDer} alt="Figura derecha" />
            </div>
          </div>
        );
      default:
        return (
          <div className="login-layout center">
            <img src={upbLogo} alt="UPB Logo" className="logo" />
            <h2>Login</h2>
            <LoginForm />
            <SocialLogin />
          </div>
        );
    }
  };

  return <>{renderContent()}</>;
};

export default Login;
