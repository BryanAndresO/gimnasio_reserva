import React from 'react';
import { Modal } from '../common/Modal';
import { Button } from '../common/Button';
import type { ClaseDTO } from '../../services/core/claseService';
import { formatDateTime } from '../../utils/formatters';

interface ReservaConfirmModalProps {
  isOpen: boolean;
  clase: ClaseDTO | null;
  onConfirm: () => void;
  onCancel: () => void;
  isLoading?: boolean;
}

export const ReservaConfirmModal: React.FC<ReservaConfirmModalProps> = ({
  isOpen,
  clase,
  onConfirm,
  onCancel,
  isLoading = false,
}) => {
  if (!clase) return null;

  return (
    <Modal
      isOpen={isOpen}
      onClose={onCancel}
      title="Confirmar Reserva"
      size="md"
    >
      <div className="space-y-4">
        <div>
          <h3 className="font-semibold text-lg mb-2">{clase.nombre}</h3>
          <div className="space-y-2 text-gray-600">
            <p><span className="font-medium">Horario:</span> {formatDateTime(clase.horario)}</p>
            <p><span className="font-medium">Entrenador:</span> {clase.nombreEntrenador}</p>
            <p><span className="font-medium">Duración:</span> {clase.duracionMinutos} minutos</p>
            <p><span className="font-medium">Cupos disponibles:</span> {clase.cuposDisponibles}</p>
          </div>
        </div>

        <div className="bg-blue-50 p-3 rounded-lg">
          <p className="text-sm text-blue-800">
            ¿Estás seguro de que deseas reservar esta clase?
          </p>
        </div>

        <div className="flex gap-3 justify-end">
          <Button variant="outline" onClick={onCancel} disabled={isLoading}>
            Cancelar
          </Button>
          <Button variant="primary" onClick={onConfirm} isLoading={isLoading}>
            Confirmar Reserva
          </Button>
        </div>
      </div>
    </Modal>
  );
};

