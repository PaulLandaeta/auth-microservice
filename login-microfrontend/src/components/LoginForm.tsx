const LoginForm = () => (
  <form className="login-form">
    <input type="email" placeholder="Email" required />
    <input type="password" placeholder="Password" required />
    <div className="forgot-link">
      <a href="/forgot-password">Forgot Password?</a>
    </div>
    <button type="submit" className="login-btn">LOGIN</button>
  </form>
);

export default LoginForm;
