import axios from './axiosConfig';
import { toast } from 'react-toastify';

export interface EntrenadorAdminDTO {
  idEntrenador?: number;
  nombre: string;
  especialidad: string;
  certificaciones?: string;
  activo?: boolean;
  totalClases?: number;
}

export const adminEntrenadorService = {
  // Listar todos los entrenadores
  listarTodos: async (): Promise<EntrenadorAdminDTO[]> => {
    try {
      const response = await axios.get('/admin/entrenadores');
      return response.data.data || response.data;
    } catch (error) {
      console.error('Error al listar entrenadores:', error);
      throw error;
    }
  },

  // Listar entrenadores activos
  listarActivos: async (): Promise<EntrenadorAdminDTO[]> => {
    try {
      const response = await axios.get('/admin/entrenadores/activos');
      return response.data.data || response.data;
    } catch (error) {
      console.error('Error al listar entrenadores activos:', error);
      throw error;
    }
  },

  // Obtener entrenador por ID
  obtenerPorId: async (id: number): Promise<EntrenadorAdminDTO> => {
    try {
      const response = await axios.get(`/admin/entrenadores/${id}`);
      return response.data.data || response.data;
    } catch (error) {
      console.error('Error al obtener entrenador:', error);
      throw error;
    }
  },

  // Crear nuevo entrenador
  crear: async (entrenador: EntrenadorAdminDTO): Promise<EntrenadorAdminDTO> => {
    try {
      const response = await axios.post('/admin/entrenadores', entrenador);
      toast.success('Entrenador creado exitosamente');
      return response.data.data || response.data;
    } catch (error: any) {
      const errorMessage = error.response?.data?.message || 'Error al crear el entrenador';
      toast.error(errorMessage);
      throw error;
    }
  },

  // Actualizar entrenador
  actualizar: async (id: number, entrenador: EntrenadorAdminDTO): Promise<EntrenadorAdminDTO> => {
    try {
      const response = await axios.put(`/admin/entrenadores/${id}`, entrenador);
      toast.success('Entrenador actualizado exitosamente');
      return response.data.data || response.data;
    } catch (error: any) {
      const errorMessage = error.response?.data?.message || 'Error al actualizar el entrenador';
      toast.error(errorMessage);
      throw error;
    }
  },

  // Eliminar entrenador
  eliminar: async (id: number): Promise<void> => {
    try {
      await axios.delete(`/admin/entrenadores/${id}`);
      toast.success('Entrenador eliminado exitosamente');
    } catch (error: any) {
      const errorMessage = error.response?.data?.message || 'Error al eliminar el entrenador';
      toast.error(errorMessage);
      throw error;
    }
  },

  // Desactivar entrenador
  desactivar: async (id: number): Promise<void> => {
    try {
      await axios.patch(`/admin/entrenadores/${id}/desactivar`);
      toast.success('Entrenador desactivado exitosamente');
    } catch (error: any) {
      const errorMessage = error.response?.data?.message || 'Error al desactivar el entrenador';
      toast.error(errorMessage);
      throw error;
    }
  },

  // Activar entrenador
  activar: async (id: number): Promise<void> => {
    try {
      await axios.patch(`/admin/entrenadores/${id}/activar`);
      toast.success('Entrenador activado exitosamente');
    } catch (error: any) {
      const errorMessage = error.response?.data?.message || 'Error al activar el entrenador';
      toast.error(errorMessage);
      throw error;
    }
  },

  // Obtener especialidades disponibles
  obtenerEspecialidades: async (): Promise<string[]> => {
    try {
      const response = await axios.get('/admin/entrenadores/especialidades');
      return response.data.data || response.data;
    } catch (error) {
      console.error('Error al obtener especialidades:', error);
      return [];
    }
  },
};

