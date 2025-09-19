import axiosInstance from '../api/axiosInstance';

export interface RegisterUserData {
  nombre: string;
  email: string;
  contraseña: string;
}

export interface RegisterUserResponse {
  success: boolean;
  message: string;
  user?: {
    id: string;
    nombre: string;
    email: string;
  };
}

export const registerUser = async (userData: RegisterUserData): Promise<RegisterUserResponse> => {
  try {
    const response = await axiosInstance.post('/auth/register-user', userData);
    console.log('Respuesta de registro:', response.data);

    if ('code' in response.data && 'msg' in response.data) {
      return {
        success: response.data.code === '201' || response.data.code === 201,
        message: response.data.msg,
      };
    }
    return response.data;
  } catch (error: unknown) {
    console.error('Error en registerUser:', error);

    const axiosError = error as { response?: { data?: { message?: string } } };
    if (axiosError.response?.data?.message) {
      throw new Error(axiosError.response.data.message);
    }

    throw new Error('Error al registrar usuario');
  }
};