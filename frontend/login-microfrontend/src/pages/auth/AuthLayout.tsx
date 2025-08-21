import React from "react";

interface AuthLayoutProps {
  position?: "left" | "right" | "center";
  children: React.ReactNode;
}

export default function AuthLayout({ position = "center", children }: AuthLayoutProps) {
  const upbLogo = "/upblogo.jpeg";
  const figura = "/fotito.png";
  const layoutClass = position === "center" ? "login-layout center" : "login-layout two-columns";

  return (
    <div className="background-full">
      <div className={layoutClass}>
        {position === "left" && (
          <div className="figure-col" aria-hidden>
            <img src={figura} alt="Figura decorativa izquierda" />
          </div>
        )}

        <div className="form-col">
          <img src={upbLogo} alt="UPB Logo" className="logo" />
          {children}
        </div>

        {position === "right" && (
          <div className="figure-col" aria-hidden>
            <img src={figura} alt="Figura decorativa derecha" />
          </div>
        )}
      </div>
    </div>
  );
}
