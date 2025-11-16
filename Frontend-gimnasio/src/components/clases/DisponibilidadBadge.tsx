import React from 'react';
import { classNames } from '../../utils/helpers';

interface DisponibilidadBadgeProps {
  cuposDisponibles: number;
  cupoTotal: number;
  disponible: boolean;
}

export const DisponibilidadBadge: React.FC<DisponibilidadBadgeProps> = ({
  cuposDisponibles,
  cupoTotal,
  disponible,
}) => {
  const porcentaje = (cuposDisponibles / cupoTotal) * 100;
  
  let colorClass = 'bg-gray-100 text-gray-800';
  if (!disponible || cuposDisponibles === 0) {
    colorClass = 'bg-red-100 text-red-800';
  } else if (porcentaje <= 25) {
    colorClass = 'bg-yellow-100 text-yellow-800';
  } else {
    colorClass = 'bg-green-100 text-green-800';
  }

  return (
    <span className={classNames('px-3 py-1 rounded-full text-sm font-medium', colorClass)}>
      {cuposDisponibles === 0 ? 'Agotado' : `${cuposDisponibles}/${cupoTotal} disponibles`}
    </span>
  );
};

