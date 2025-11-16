import axios from 'axios';
import { API_BASE_URL } from '../../utils/constants';
import { getFromStorage } from '../../utils/helpers';
import { STORAGE_KEYS } from '../../utils/constants';

const getAuthHeaders = () => {
  const token = getFromStorage<string>(STORAGE_KEYS.TOKEN);
  return token ? { Authorization: `Bearer ${token}` } : {};
};

export interface ReservaDTO {
  idReserva: number;
  fechaReserva: string;
  estado: string;
  idUsuario: number;
  nombreUsuario?: string;
  correoUsuario?: string;
  idClase: number;
  nombreClase?: string;
  horarioClase?: string;
  duracionMinutos?: number;
}

export const reservaService = {
  crearReserva: async (idUsuario: number, idClase: number): Promise<ReservaDTO> => {
    const response = await axios.post(
      `${API_BASE_URL}/reservas`,
      { idUsuario, idClase },
      { headers: getAuthHeaders() }
    );
    return response.data.data || response.data;
  },

  cancelarReserva: async (idReserva: number, idUsuario: number): Promise<ReservaDTO> => {
    const response = await axios.post(
      `${API_BASE_URL}/reservas/${idReserva}/cancelar`,
      { idUsuario },
      { headers: getAuthHeaders() }
    );
    return response.data.data || response.data;
  },

  obtenerReservasPorUsuario: async (idUsuario: number): Promise<ReservaDTO[]> => {
    const response = await axios.get(`${API_BASE_URL}/reservas/usuario/${idUsuario}`, {
      headers: getAuthHeaders(),
    });
    return response.data.data || response.data;
  },

  obtenerReservasConfirmadas: async (idUsuario: number): Promise<ReservaDTO[]> => {
    const response = await axios.get(`${API_BASE_URL}/reservas/usuario/${idUsuario}/confirmadas`, {
      headers: getAuthHeaders(),
    });
    return response.data.data || response.data;
  },

  obtenerHistorial: async (idUsuario: number): Promise<ReservaDTO[]> => {
    const response = await axios.get(`${API_BASE_URL}/reservas/usuario/${idUsuario}/historial`, {
      headers: getAuthHeaders(),
    });
    return response.data.data || response.data;
  },

  obtenerPorId: async (idReserva: number): Promise<ReservaDTO> => {
    const response = await axios.get(`${API_BASE_URL}/reservas/${idReserva}`, {
      headers: getAuthHeaders(),
    });
    return response.data.data || response.data;
  },
};

