import React from 'react';
import { Modal } from '../common/Modal';
import { Button } from '../common/Button';
import type { ReservaDTO } from '../../services/core/reservaService';
import { formatDateTime } from '../../utils/formatters';

interface ReservaCancelModalProps {
  isOpen: boolean;
  reserva: ReservaDTO | null;
  onConfirm: () => void;
  onCancel: () => void;
  isLoading?: boolean;
}

export const ReservaCancelModal: React.FC<ReservaCancelModalProps> = ({
  isOpen,
  reserva,
  onConfirm,
  onCancel,
  isLoading = false,
}) => {
  if (!reserva) return null;

  return (
    <Modal
      isOpen={isOpen}
      onClose={onCancel}
      title="Cancelar Reserva"
      size="md"
    >
      <div className="space-y-4">
        <div>
          <h3 className="font-semibold text-lg mb-2">{reserva.nombreClase || 'Clase'}</h3>
          <div className="space-y-2 text-gray-600">
            <p><span className="font-medium">Horario:</span> {formatDateTime(reserva.horarioClase || reserva.fechaReserva)}</p>
            <p><span className="font-medium">Fecha de reserva:</span> {formatDateTime(reserva.fechaReserva)}</p>
          </div>
        </div>

        <div className="bg-red-50 p-3 rounded-lg">
          <p className="text-sm text-red-800">
            ¿Estás seguro de que deseas cancelar esta reserva? Esta acción no se puede deshacer.
          </p>
        </div>

        <div className="flex gap-3 justify-end">
          <Button variant="outline" onClick={onCancel} disabled={isLoading}>
            No, mantener
          </Button>
          <Button variant="danger" onClick={onConfirm} isLoading={isLoading}>
            Sí, cancelar
          </Button>
        </div>
      </div>
    </Modal>
  );
};

