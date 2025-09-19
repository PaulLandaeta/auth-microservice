import axios from "axios";

const axiosInstance = axios.create({
    baseURL: import.meta.env.VITE_API_URL,
    timeout: 10000,
});

// Interceptor para agregar el token de autenticación a las peticiones
axiosInstance.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('access_token');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

// Interceptor para manejar errores de respuesta, especialmente 401 (unauthorized)
axiosInstance.interceptors.response.use(
    (response) => {
        return response;
    },
    async (error) => {
        const originalRequest = error.config;

        if (error.response?.status === 401 && !originalRequest._retry) {
            originalRequest._retry = true;

            // Intentar renovar el token
            const refreshToken = localStorage.getItem('refresh_token');
            if (refreshToken) {
                try {
                    const response = await axios.post(`${import.meta.env.VITE_API_URL}/auth/refresh`, {
                        refresh_token: refreshToken
                    });

                    const newAccessToken = response.data.access_token;
                    localStorage.setItem('access_token', newAccessToken);
                    
                    // Reintentar la petición original con el nuevo token
                    originalRequest.headers.Authorization = `Bearer ${newAccessToken}`;
                    return axiosInstance(originalRequest);
                } catch {
                    // Si falla la renovación, limpiar tokens y redirigir al login
                    localStorage.removeItem('access_token');
                    localStorage.removeItem('refresh_token');
                    localStorage.removeItem('session_id');
                    
                    // Enviar evento personalizado para que la app maneje el logout
                    window.dispatchEvent(new CustomEvent('auth:logout'));
                }
            }
        }

        return Promise.reject(error);
    }
);

export default axiosInstance;
