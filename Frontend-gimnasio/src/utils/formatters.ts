// Formateadores para datos

/**
 * Formatea una fecha a formato legible
 */
export const formatDate = (date: string | Date): string => {
  if (!date) return '';
  const d = typeof date === 'string' ? new Date(date) : date;
  if (isNaN(d.getTime())) return '';
  
  return d.toLocaleDateString('es-ES', {
    year: 'numeric',
    month: 'long',
    day: 'numeric',
  });
};

/**
 * Formatea una fecha y hora a formato legible
 */
export const formatDateTime = (dateTime: string | Date): string => {
  if (!dateTime) return '';
  const d = typeof dateTime === 'string' ? new Date(dateTime) : dateTime;
  if (isNaN(d.getTime())) return '';
  
  return d.toLocaleString('es-ES', {
    year: 'numeric',
    month: 'long',
    day: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
  });
};

/**
 * Formatea una hora a formato legible
 */
export const formatTime = (time: string): string => {
  if (!time) return '';
  const [hours, minutes] = time.split(':');
  return `${hours}:${minutes}`;
};

/**
 * Formatea un número a formato de moneda
 */
export const formatCurrency = (amount: number, currency: string = 'EUR'): string => {
  return new Intl.NumberFormat('es-ES', {
    style: 'currency',
    currency,
  }).format(amount);
};

/**
 * Formatea un número con separadores de miles
 */
export const formatNumber = (number: number): string => {
  return new Intl.NumberFormat('es-ES').format(number);
};

/**
 * Capitaliza la primera letra de un string
 */
export const capitalize = (str: string): string => {
  if (!str) return '';
  return str.charAt(0).toUpperCase() + str.slice(1).toLowerCase();
};

/**
 * Capitaliza cada palabra de un string
 */
export const capitalizeWords = (str: string): string => {
  if (!str) return '';
  return str
    .split(' ')
    .map(word => capitalize(word))
    .join(' ');
};

/**
 * Trunca un string a una longitud máxima
 */
export const truncate = (str: string, maxLength: number): string => {
  if (!str || str.length <= maxLength) return str;
  return str.substring(0, maxLength) + '...';
};

/**
 * Enmascara un email
 */
export const maskEmail = (email: string): string => {
  if (!email || !email.includes('@')) return email;
  const [username, domain] = email.split('@');
  if (username.length <= 2) {
    return `${username[0]}***@${domain}`;
  }
  return `${username.substring(0, 2)}***@${domain}`;
};

/**
 * Formatea un nombre de usuario
 */
export const formatUserName = (name: string): string => {
  if (!name) return '';
  return capitalizeWords(name.trim());
};

/**
 * Convierte un string a slug
 */
export const toSlug = (str: string): string => {
  return str
    .toLowerCase()
    .normalize('NFD')
    .replace(/[\u0300-\u036f]/g, '')
    .replace(/[^a-z0-9]+/g, '-')
    .replace(/(^-|-$)/g, '');
};

/**
 * Formatea un tiempo relativo (hace X minutos, hace X horas, etc.)
 */
export const formatRelativeTime = (date: string | Date): string => {
  if (!date) return '';
  const d = typeof date === 'string' ? new Date(date) : date;
  const now = new Date();
  const diffInSeconds = Math.floor((now.getTime() - d.getTime()) / 1000);

  if (diffInSeconds < 60) return 'hace un momento';
  if (diffInSeconds < 3600) return `hace ${Math.floor(diffInSeconds / 60)} minutos`;
  if (diffInSeconds < 86400) return `hace ${Math.floor(diffInSeconds / 3600)} horas`;
  if (diffInSeconds < 2592000) return `hace ${Math.floor(diffInSeconds / 86400)} días`;
  
  return formatDate(d);
};

