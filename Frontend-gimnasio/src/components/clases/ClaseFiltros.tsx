import React, { useState } from 'react';
import { Input } from '../common/Input';
import { Button } from '../common/Button';

interface ClaseFiltrosProps {
  onFiltrar: (filtros: FiltrosClase) => void;
}

export interface FiltrosClase {
  nombre?: string;
  fechaInicio?: string;
  fechaFin?: string;
  idEntrenador?: number;
}

export const ClaseFiltros: React.FC<ClaseFiltrosProps> = ({ onFiltrar }) => {
  const [filtros, setFiltros] = useState<FiltrosClase>({});

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onFiltrar(filtros);
  };

  const handleReset = () => {
    setFiltros({});
    onFiltrar({});
  };

  return (
    <div className="bg-white p-4 rounded-lg shadow-md mb-6">
      <form onSubmit={handleSubmit} className="grid grid-cols-1 md:grid-cols-4 gap-4">
        <Input
          label="Buscar por nombre"
          placeholder="Nombre de la clase..."
          value={filtros.nombre || ''}
          onChange={(e) => setFiltros({ ...filtros, nombre: e.target.value })}
        />
        <Input
          label="Fecha inicio"
          type="datetime-local"
          value={filtros.fechaInicio || ''}
          onChange={(e) => setFiltros({ ...filtros, fechaInicio: e.target.value })}
        />
        <Input
          label="Fecha fin"
          type="datetime-local"
          value={filtros.fechaFin || ''}
          onChange={(e) => setFiltros({ ...filtros, fechaFin: e.target.value })}
        />
        <div className="flex items-end gap-2">
          <Button type="submit" variant="primary" fullWidth>
            Filtrar
          </Button>
          <Button type="button" variant="outline" onClick={handleReset}>
            Limpiar
          </Button>
        </div>
      </form>
    </div>
  );
};

