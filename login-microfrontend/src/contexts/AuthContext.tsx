import { createContext, useContext, useEffect } from "react";
import type { ReactNode } from "react";
import { useAuth } from "../hooks/useAuth";
import type { LoginUserPayload, LoginSuccess } from "../services/authService";
import type {
  RegisterUserData,
  RegisterUserResponse,
} from "../services/registerUser";

interface AuthContextType {
  isAuthenticated: boolean;
  user: LoginSuccess | null;
  isLoading: boolean;
  error: string | null;
  login: (payload: LoginUserPayload) => Promise<LoginSuccess>;
  register: (payload: RegisterUserData) => Promise<RegisterUserResponse>;
  logout: () => void;
  clearError: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

interface AuthProviderProps {
  children: ReactNode;
}

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
  const auth = useAuth();

  useEffect(() => {
    // Verificar el estado de autenticación al montar el componente
    auth.checkAuthStatus();
  }, [auth.checkAuthStatus]);

  return <AuthContext.Provider value={auth}>{children}</AuthContext.Provider>;
};

export const useAuthContext = (): AuthContextType => {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error("useAuthContext must be used within an AuthProvider");
  }
  return context;
};
