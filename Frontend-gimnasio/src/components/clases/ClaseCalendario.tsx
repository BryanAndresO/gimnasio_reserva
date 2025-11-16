import React from 'react';
import type { ClaseDTO } from '../../services/core/claseService';
import { formatDate, formatDateTime } from '../../utils/formatters';
import { DisponibilidadBadge } from './DisponibilidadBadge';

interface ClaseCalendarioProps {
  clases: ClaseDTO[];
  onSeleccionarClase?: (clase: ClaseDTO) => void;
}

export const ClaseCalendario: React.FC<ClaseCalendarioProps> = ({
  clases,
  onSeleccionarClase,
}) => {
  // Agrupar clases por fecha
  const clasesPorFecha = clases.reduce((acc, clase) => {
    const fecha = formatDate(clase.horario);
    if (!acc[fecha]) {
      acc[fecha] = [];
    }
    acc[fecha].push(clase);
    return acc;
  }, {} as Record<string, ClaseDTO[]>);

  const fechas = Object.keys(clasesPorFecha).sort();

  if (fechas.length === 0) {
    return (
      <div className="text-center py-8 text-gray-500">
        No hay clases programadas
      </div>
    );
  }

  return (
    <div className="space-y-6">
      {fechas.map((fecha) => (
        <div key={fecha} className="border-b pb-6 last:border-b-0">
          <h3 className="text-lg font-semibold mb-4">{fecha}</h3>
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
            {clasesPorFecha[fecha].map((clase) => (
              <div
                key={clase.idClase}
                className="border rounded-lg p-4 hover:shadow-md transition-shadow cursor-pointer"
                onClick={() => onSeleccionarClase?.(clase)}
              >
                <div className="flex justify-between items-start mb-2">
                  <h4 className="font-semibold">{clase.nombre}</h4>
                  <span className="text-sm text-gray-500">{formatDateTime(clase.horario)}</span>
                </div>
                <p className="text-sm text-gray-600 mb-3">{clase.nombreEntrenador}</p>
                <DisponibilidadBadge
                  cuposDisponibles={clase.cuposDisponibles}
                  cupoTotal={clase.cupo}
                  disponible={clase.cuposDisponibles > 0}
                />
              </div>
            ))}
          </div>
        </div>
      ))}
    </div>
  );
};

