import { useState, useEffect, useCallback } from 'react';
import axios, { type AxiosError } from '../services/core/axiosConfig';
import { API_BASE_URL } from '../utils/constants';

interface ApiState<T> {
  data: T | null;
  loading: boolean;
  error: string | null;
}

interface RequestConfig {
  method?: 'GET' | 'POST' | 'PUT' | 'DELETE' | 'PATCH';
  headers?: Record<string, string>;
  params?: Record<string, any>;
  data?: any;
  skip?: boolean;
}

export const useApi = <T = any>(
  url: string,
  options: RequestConfig = {}
) => {
  const [state, setState] = useState<ApiState<T>>({
    data: null,
    loading: false,
    error: null,
  });

  const { skip = false, ...axiosOptions } = options;

  const fetchData = useCallback(async () => {
    if (skip) return;

    setState(prev => ({ ...prev, loading: true, error: null }));

    try {
      // El token se agregará automáticamente por el interceptor de axios
      const response = await axios({
        ...axiosOptions,
        url: url.startsWith('/') ? url : `/${url}`,
        method: axiosOptions.method || 'GET',
        headers: {
          ...axiosOptions.headers,
        },
        withCredentials: true, // Asegurar que withCredentials esté configurado
      });

      setState({
        data: response.data,
        loading: false,
        error: null,
      });
    } catch (error) {
      const axiosError = error as AxiosError<any>;
      const errorMessage = 
        (axiosError.response?.data as any)?.message || 
        axiosError.message || 
        'Error desconocido';
      
      setState({
        data: null,
        loading: false,
        error: errorMessage,
      });
    }
  }, [url, skip, JSON.stringify(axiosOptions)]);

  useEffect(() => {
    fetchData();
  }, [fetchData]);

  const refetch = useCallback(() => {
    fetchData();
  }, [fetchData]);

  return { ...state, refetch };
};

export const useApiMutation = <T = any, D = any>() => {
  const [state, setState] = useState<ApiState<T>>({
    data: null,
    loading: false,
    error: null,
  });

  const mutate = useCallback(async (
    url: string,
    options: Omit<RequestConfig, 'url' | 'skip'> & { data?: D } = {}
  ) => {
    setState(prev => ({ ...prev, loading: true, error: null }));

    try {
      // El token se agregará automáticamente por el interceptor de axios
      const response = await axios({
        ...options,
        url: url.startsWith('/') ? url : `/${url}`,
        method: options.method || 'POST',
        headers: {
          ...options.headers,
        },
        withCredentials: true, // Asegurar que withCredentials esté configurado
      });

      setState({
        data: response.data,
        loading: false,
        error: null,
      });
      return response.data;
    } catch (error) {
      const axiosError = error as AxiosError<any>;
      const errorMessage = 
        (axiosError.response?.data as any)?.message || 
        axiosError.message || 
        'Error desconocido';
      
      setState({
        data: null,
        loading: false,
        error: errorMessage,
      });
      throw error;
    }
  }, []);

  return { ...state, mutate };
};
