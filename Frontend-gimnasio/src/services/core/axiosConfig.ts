import axios from 'axios';
import { API_BASE_URL } from '../../utils/constants';
import { STORAGE_KEYS } from '../../utils/constants';
import { toast } from 'react-toastify';
import type { AxiosError } from 'axios';

// Configure axios defaults globally
axios.defaults.baseURL = API_BASE_URL;
axios.defaults.headers.common['Content-Type'] = 'application/json';
axios.defaults.withCredentials = true; // Important for CORS with credentials

// Add request interceptor to include auth token
axios.interceptors.request.use(
  (config) => {
    // Leer el token directamente del localStorage (es un string, no JSON)
    try {
      const token = localStorage.getItem(STORAGE_KEYS.TOKEN);
      if (token && config.headers) {
        config.headers.Authorization = `Bearer ${token}`;
        // Log solo en desarrollo para debugging
        if (import.meta.env.DEV) {
          console.log('Request con token:', config.url, token.substring(0, 20) + '...');
        }
      } else if (config.url && !config.url.includes('/auth/')) {
        // Solo log si no es una petición de autenticación
        if (import.meta.env.DEV) {
          console.warn('Request sin token:', config.url);
        }
      }
    } catch (error) {
      console.error('Error reading token from localStorage:', error);
    }
    // Ensure withCredentials is set for all requests
    config.withCredentials = true;
    return config;
  },
  (error) => {
    console.error('Request Error:', error);
    return Promise.reject(error);
  }
);

// Add response interceptor for error handling
axios.interceptors.response.use(
  (response) => {
    return response;
  },
  (error: AxiosError) => {
    console.error('API Error:', {
      url: error.config?.url,
      method: error.config?.method,
      status: error.response?.status,
      data: error.response?.data,
    });
    
    if (error.response) {
      // Server responded with a status code outside 2xx
      const responseData = error.response.data as { message?: string; success?: boolean };
      const errorMessage = responseData?.message || 'Error en la solicitud';
      
      // Handle 401 Unauthorized - token invalid or missing
      if (error.response.status === 401) {
        // Clear invalid token
        localStorage.removeItem(STORAGE_KEYS.TOKEN);
        localStorage.removeItem(STORAGE_KEYS.USER);
        // Only show toast if it's not an auth endpoint
        if (!error.config?.url?.includes('/auth/')) {
          toast.error(errorMessage);
        }
      } else if (!error.config?.url?.includes('/auth/')) {
        // Don't show toast for auth errors (they're handled in authService)
        toast.error(errorMessage);
      }
    } else if (error.request) {
      // Request made but no response received
      toast.error('No se pudo conectar con el servidor. Verifica tu conexión.');
    } else {
      // Something happened in setting up the request
      toast.error('Error en la configuración de la solicitud');
    }
    
    return Promise.reject(error);
  }
);

export default axios;

