import axios from 'axios';
import { API_BASE_URL } from '../../utils/constants';
import { getFromStorage } from '../../utils/helpers';
import { STORAGE_KEYS } from '../../utils/constants';

const getAuthHeaders = () => {
  const token = getFromStorage<string>(STORAGE_KEYS.TOKEN);
  return token ? { Authorization: `Bearer ${token}` } : {};
};

export interface ClaseDTO {
  idClase: number;
  nombre: string;
  descripcion?: string;
  horario: string;
  cupo: number;
  cuposDisponibles: number;
  duracionMinutos: number;
  activo: boolean;
  idEntrenador: number;
  nombreEntrenador: string;
  especialidadEntrenador: string;
}

export const claseService = {
  obtenerClasesDisponibles: async (): Promise<ClaseDTO[]> => {
    const response = await axios.get(`${API_BASE_URL}/clases`, {
      headers: getAuthHeaders(),
    });
    return response.data.data || response.data;
  },

  obtenerClasesProximas: async (): Promise<ClaseDTO[]> => {
    const response = await axios.get(`${API_BASE_URL}/clases/proximas`, {
      headers: getAuthHeaders(),
    });
    return response.data.data || response.data;
  },

  obtenerClasesActivas: async (): Promise<ClaseDTO[]> => {
    const response = await axios.get(`${API_BASE_URL}/clases/activas`, {
      headers: getAuthHeaders(),
    });
    return response.data.data || response.data;
  },

  obtenerPorId: async (id: number): Promise<ClaseDTO> => {
    const response = await axios.get(`${API_BASE_URL}/clases/${id}`, {
      headers: getAuthHeaders(),
    });
    return response.data.data || response.data;
  },

  buscarPorNombre: async (nombre: string): Promise<ClaseDTO[]> => {
    const response = await axios.get(`${API_BASE_URL}/clases/buscar`, {
      params: { nombre },
      headers: getAuthHeaders(),
    });
    return response.data.data || response.data;
  },

  obtenerPorEntrenador: async (idEntrenador: number): Promise<ClaseDTO[]> => {
    const response = await axios.get(`${API_BASE_URL}/clases/entrenador/${idEntrenador}`, {
      headers: getAuthHeaders(),
    });
    return response.data.data || response.data;
  },

  obtenerPorRangoFechas: async (inicio: string, fin: string): Promise<ClaseDTO[]> => {
    const response = await axios.get(`${API_BASE_URL}/clases/rango-fechas`, {
      params: { inicio, fin },
      headers: getAuthHeaders(),
    });
    return response.data.data || response.data;
  },
};

