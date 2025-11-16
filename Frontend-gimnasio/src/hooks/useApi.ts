import { useState, useEffect, useCallback } from 'react';
import axios, { type AxiosError } from 'axios';
import { API_BASE_URL, STORAGE_KEYS } from '../utils/constants';
import { getFromStorage } from '../utils/helpers';

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
      const token = getFromStorage<string>(STORAGE_KEYS.TOKEN);
      
      const response = await axios({
        ...axiosOptions,
        url: `${API_BASE_URL}${url}`,
        method: axiosOptions.method || 'GET',
        headers: {
          'Content-Type': 'application/json',
          ...axiosOptions.headers,
          ...(token && { Authorization: `Bearer ${token}` }),
        },
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
      const token = getFromStorage<string>(STORAGE_KEYS.TOKEN);
      
      const response = await axios({
        ...options,
        url: `${API_BASE_URL}${url}`,
        method: options.method || 'POST',
        headers: {
          'Content-Type': 'application/json',
          ...options.headers,
          ...(token && { Authorization: `Bearer ${token}` }),
        },
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
