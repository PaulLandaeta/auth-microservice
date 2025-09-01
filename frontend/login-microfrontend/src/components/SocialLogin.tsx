import { FaGoogle, FaFacebook } from "react-icons/fa";

const SocialLogin = () => (
  <div className="social-login">
    <span className="social-text">Or login with</span>
    <div className="social-buttons">
      <button className="social-btn google">
        <FaGoogle /> Google
      </button>
      <button className="social-btn facebook">
        <FaFacebook /> Facebook
      </button>
    </div>
  </div>
);

export default SocialLogin;
