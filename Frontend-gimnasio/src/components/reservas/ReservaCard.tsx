import React from 'react';
import type { ReservaDTO } from '../../services/core/reservaService';
import { formatDateTime } from '../../utils/formatters';
import { Card } from '../common/Card';
import { Button } from '../common/Button';

interface ReservaCardProps {
  reserva: ReservaDTO;
  onCancelar?: (id: number) => void;
  onVerDetalle?: (id: number) => void;
}

export const ReservaCard: React.FC<ReservaCardProps> = ({
  reserva,
  onCancelar,
  onVerDetalle,
}) => {
  const estadoColors = {
    CONFIRMADA: 'bg-blue-100 text-blue-800',
    CANCELADA: 'bg-red-100 text-red-800',
    COMPLETADA: 'bg-green-100 text-green-800',
  };

  const puedeCancelar = reserva.estado === 'CONFIRMADA';

  return (
    <Card className="hover:shadow-lg transition-shadow">
      <div className="flex justify-between items-start mb-4">
        <div className="flex-1">
          <h3 className="text-lg font-semibold text-gray-900">{reserva.nombreClase || 'Clase'}</h3>
          <div className="mt-2 space-y-1">
            <p className="text-sm text-gray-600">
              <span className="font-medium">📅 Horario:</span>{' '}
              {formatDateTime(reserva.horarioClase || reserva.fechaReserva)}
            </p>
            {reserva.duracionMinutos && (
              <p className="text-sm text-gray-600">
                <span className="font-medium">⏱️ Duración:</span> {reserva.duracionMinutos} minutos
              </p>
            )}
            {/* Mostrar información del entrenador si está disponible */}
            {reserva.nombreEntrenador && (
              <p className="text-sm text-gray-600">
                <span className="font-medium">👤 Entrenador:</span> {reserva.nombreEntrenador}
                {reserva.especialidadEntrenador && (
                  <span className="text-gray-500 ml-2">({reserva.especialidadEntrenador})</span>
                )}
              </p>
            )}
          </div>
        </div>
        <span className={`px-3 py-1 rounded-full text-xs font-medium whitespace-nowrap ml-4 ${
          estadoColors[reserva.estado as keyof typeof estadoColors] || 'bg-gray-100 text-gray-800'
        }`}>
          {reserva.estado}
        </span>
      </div>

      <div className="flex gap-2 mt-4">
        {onVerDetalle && (
          <Button
            variant="outline"
            size="sm"
            onClick={() => onVerDetalle(reserva.idReserva)}
            className="flex-1"
          >
            Ver Detalle
          </Button>
        )}
        {puedeCancelar && onCancelar && (
          <Button
            variant="danger"
            size="sm"
            onClick={() => onCancelar(reserva.idReserva)}
            className="flex-1"
          >
            Cancelar
          </Button>
        )}
      </div>
    </Card>
  );
};

