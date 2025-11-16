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
    <Card>
      <div className="flex justify-between items-start mb-4">
        <div>
          <h3 className="text-lg font-semibold">{reserva.nombreClase || 'Clase'}</h3>
          <p className="text-sm text-gray-600 mt-1">
            {formatDateTime(reserva.horarioClase || reserva.fechaReserva)}
          </p>
        </div>
        <span className={`px-3 py-1 rounded-full text-xs font-medium ${
          estadoColors[reserva.estado as keyof typeof estadoColors] || 'bg-gray-100 text-gray-800'
        }`}>
          {reserva.estado}
        </span>
      </div>

      {reserva.duracionMinutos && (
        <p className="text-sm text-gray-600 mb-4">
          Duración: {reserva.duracionMinutos} minutos
        </p>
      )}

      <div className="flex gap-2">
        {onVerDetalle && (
          <Button
            variant="outline"
            size="sm"
            onClick={() => onVerDetalle(reserva.idReserva)}
          >
            Ver Detalle
          </Button>
        )}
        {puedeCancelar && onCancelar && (
          <Button
            variant="danger"
            size="sm"
            onClick={() => onCancelar(reserva.idReserva)}
          >
            Cancelar
          </Button>
        )}
      </div>
    </Card>
  );
};

