import { useState, useEffect, useCallback } from 'react';
import { loginUser } from '../services/authService';
import { registerUser } from '../services/registerUser';
import type { LoginUserPayload, LoginSuccess } from '../services/authService';
import type { RegisterUserData, RegisterUserResponse } from '../services/registerUser';

interface AuthState {
  isAuthenticated: boolean;
  user: LoginSuccess | null;
  isLoading: boolean;
  error: string | null;
}

export const useAuth = () => {
  const [authState, setAuthState] = useState<AuthState>({
    isAuthenticated: false,
    user: null,
    isLoading: false,
    error: null,
  });

  // Escuchar eventos de logout automático del interceptor de axios
  useEffect(() => {
    const handleAutoLogout = () => {
      logout();
    };

    window.addEventListener('auth:logout', handleAutoLogout);
    
    return () => {
      window.removeEventListener('auth:logout', handleAutoLogout);
    };
  }, []);

  const login = async (payload: LoginUserPayload) => {
    setAuthState(prev => ({ ...prev, isLoading: true, error: null }));
    
    try {
      const userData = await loginUser(payload);
      
      // Guardar tokens en localStorage
      localStorage.setItem('access_token', userData.access_token);
      localStorage.setItem('refresh_token', userData.refresh_token);
      localStorage.setItem('session_id', userData.session_id);
      
      setAuthState({
        isAuthenticated: true,
        user: userData,
        isLoading: false,
        error: null,
      });
      
      return userData;
    } catch (error) {
      const errorMessage = error instanceof Error ? error.message : 'Error de autenticación';
      setAuthState(prev => ({
        ...prev,
        isLoading: false,
        error: errorMessage,
      }));
      throw error;
    }
  };

  const register = async (payload: RegisterUserData): Promise<RegisterUserResponse> => {
    setAuthState(prev => ({ ...prev, isLoading: true, error: null }));
    
    try {
      const response = await registerUser(payload);
      
      setAuthState(prev => ({
        ...prev,
        isLoading: false,
        error: null,
      }));
      
      return response;
    } catch (error) {
      const errorMessage = error instanceof Error ? error.message : 'Error de registro';
      setAuthState(prev => ({
        ...prev,
        isLoading: false,
        error: errorMessage,
      }));
      throw error;
    }
  };

  const logout = () => {
    // Limpiar localStorage
    localStorage.removeItem('access_token');
    localStorage.removeItem('refresh_token');
    localStorage.removeItem('session_id');
    
    setAuthState({
      isAuthenticated: false,
      user: null,
      isLoading: false,
      error: null,
    });
  };

  const clearError = () => {
    setAuthState(prev => ({ ...prev, error: null }));
  };

  // Verificar si el usuario está autenticado al inicializar
  const checkAuthStatus = useCallback(() => {
    const token = localStorage.getItem('access_token');
    const sessionId = localStorage.getItem('session_id');
    
    if (token && sessionId) {
      setAuthState(prev => ({ ...prev, isAuthenticated: true }));
    }
  }, []);

  return {
    ...authState,
    login,
    register,
    logout,
    clearError,
    checkAuthStatus,
  };
};
