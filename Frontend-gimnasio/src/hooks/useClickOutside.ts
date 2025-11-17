import { useEffect, useRef } from 'react';
import type { RefObject } from 'react';

/**
 * Hook para detectar clicks fuera de un elemento
 */
export const useClickOutside = <T extends HTMLElement = HTMLElement>(
  handler: () => void
): RefObject<T | null> => {
  const ref = useRef<T | null>(null);

  useEffect(() => {
    const handleClickOutside = (event: MouseEvent | TouchEvent) => {
      if (ref.current && !ref.current.contains(event.target as Node)) {
        handler();
      }
    };

    document.addEventListener('mousedown', handleClickOutside);
    document.addEventListener('touchstart', handleClickOutside);

    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
      document.removeEventListener('touchstart', handleClickOutside);
    };
  }, [handler]);

  return ref;
};

