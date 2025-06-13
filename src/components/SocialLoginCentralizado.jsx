import React from 'react';
import styles from '../styles/login.module.css';
import upbLogo from '../assets/UPB.png';

const SocialLoginCentralizado = () => {
  return (
    <div className={styles.centralPage}>
      <div className={styles.centralContainer}>
        <img src={upbLogo} alt="UPB Logo" className={styles.logo} />
        <div className={styles.loginBox}>
          <h2>Login Central</h2>
          <div className={styles.socialIconsSide}>
            <button className={styles.googleBtn}>Login with Google</button>
            <button className={styles.facebookBtn}>Login with Facebook</button>
          </div>
          <p className={styles.signupLink}>Sign up</p>
        </div>
      </div>
    </div>
  );
};

export default SocialLoginCentralizado;
