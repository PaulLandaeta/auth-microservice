import React from 'react';
import styles from '../styles/login.module.css';
import upbLogo from '../assets/UPB.png';
import fotito from '../assets/fotito.png';

const SocialLoginDerecha = () => {
  return (
    <div className={styles.page}>
      <div className={styles.contentContainer}>
        <img src={upbLogo} alt="UPB Logo" className={styles.logo} />
        <div className={styles.loginBox}>
          <h2>Login Right</h2>
          <div className={styles.socialIconsSide}>
            <button className={styles.googleBtn}>Login with Google</button>
            <button className={styles.facebookBtn}>Login with Facebook</button>
          </div>
          <p className={styles.signupLink}>Sign up</p>
        </div>
      </div>

      
      <div className={styles.imageSide}>
        <img src={fotito} alt="Decorative Side" className={styles.sideImage} />
      </div>
    </div>
  );
};

export default SocialLoginDerecha;
