import axios from 'axios';
import { API_BASE_URL } from '../../utils/constants';
import { getFromStorage } from '../../utils/helpers';
import { STORAGE_KEYS } from '../../utils/constants';

const getAuthHeaders = () => {
  const token = getFromStorage<string>(STORAGE_KEYS.TOKEN);
  return token ? { Authorization: `Bearer ${token}` } : {};
};

export interface DisponibilidadDTO {
  idClase: number;
  nombreClase: string;
  horario: string;
  cupoTotal: number;
  cuposOcupados: number;
  cuposDisponibles: number;
  disponible: boolean;
  puedeReservar: boolean;
}

export const disponibilidadService = {
  verificarDisponibilidad: async (idClase: number): Promise<DisponibilidadDTO> => {
    const response = await axios.get(`${API_BASE_URL}/disponibilidad/clase/${idClase}`, {
      headers: getAuthHeaders(),
    });
    return response.data.data || response.data;
  },

  obtenerDisponibilidadClases: async (): Promise<DisponibilidadDTO[]> => {
    const response = await axios.get(`${API_BASE_URL}/disponibilidad/clases`, {
      headers: getAuthHeaders(),
    });
    return response.data.data || response.data;
  },

  obtenerClasesConCuposDisponibles: async (): Promise<DisponibilidadDTO[]> => {
    const response = await axios.get(`${API_BASE_URL}/disponibilidad/clases/disponibles`, {
      headers: getAuthHeaders(),
    });
    return response.data.data || response.data;
  },
};

