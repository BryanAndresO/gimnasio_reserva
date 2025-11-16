import React from 'react';
import type { ReservaDTO } from '../../services/core/reservaService';
import { formatDateTime, formatRelativeTime } from '../../utils/formatters';
import { classNames } from '../../utils/helpers';

interface ReservaTimelineProps {
  reservas: ReservaDTO[];
}

export const ReservaTimeline: React.FC<ReservaTimelineProps> = ({ reservas }) => {
  if (reservas.length === 0) {
    return (
      <div className="text-center py-8 text-gray-500">
        No hay reservas en tu historial
      </div>
    );
  }

  const estadoColors = {
    CONFIRMADA: 'bg-blue-500',
    CANCELADA: 'bg-red-500',
    COMPLETADA: 'bg-green-500',
  };

  return (
    <div className="relative">
      {reservas.map((reserva, index) => (
        <div key={reserva.idReserva} className="relative pb-8">
          {index < reservas.length - 1 && (
            <div className="absolute left-5 top-10 bottom-0 w-0.5 bg-gray-200"></div>
          )}
          
          <div className="flex items-start">
            <div className={classNames(
              'flex-shrink-0 w-10 h-10 rounded-full flex items-center justify-center text-white text-sm font-semibold',
              estadoColors[reserva.estado as keyof typeof estadoColors] || 'bg-gray-500'
            )}>
              {index + 1}
            </div>
            
            <div className="ml-4 flex-1">
              <div className="flex items-center justify-between mb-1">
                <h4 className="font-semibold">{reserva.nombreClase || 'Clase'}</h4>
                <span className={classNames(
                  'px-2 py-1 rounded text-xs font-medium',
                  reserva.estado === 'CONFIRMADA' && 'bg-blue-100 text-blue-800',
                  reserva.estado === 'CANCELADA' && 'bg-red-100 text-red-800',
                  reserva.estado === 'COMPLETADA' && 'bg-green-100 text-green-800'
                )}>
                  {reserva.estado}
                </span>
              </div>
              
              <p className="text-sm text-gray-600 mb-1">
                {formatDateTime(reserva.horarioClase || reserva.fechaReserva)}
              </p>
              
              <p className="text-xs text-gray-500">
                Reservado {formatRelativeTime(reserva.fechaReserva)}
              </p>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
};

