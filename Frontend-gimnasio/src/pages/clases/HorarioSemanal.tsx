import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { MainLayout } from '../../components/layout/MainLayout';
import { Breadcrumb } from '../../components/layout/Breadcrumb';
import { ClaseCalendario } from '../../components/clases/ClaseCalendario';
import { Loading } from '../../components/common/Loading';
import { EmptyState } from '../../components/common/EmptyState';
import type { ClaseDTO } from '../../services/core/claseService';
import { claseService } from '../../services/core/claseService';

export const HorarioSemanal: React.FC = () => {
  const navigate = useNavigate();
  const [clases, setClases] = useState<ClaseDTO[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    cargarClases();
  }, []);

  const cargarClases = async () => {
    try {
      setLoading(true);
      const data = await claseService.obtenerClasesActivas();
      setClases(data);
    } catch (error) {
      console.error('Error al cargar las clases:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleSeleccionarClase = (clase: ClaseDTO) => {
    navigate(`/clases/${clase.idClase}`);
  };

  return (
    <MainLayout>
      <Breadcrumb items={[{ label: 'Dashboard', path: '/dashboard' }, { label: 'Horario Semanal' }]} />
      <h1 className="text-3xl font-bold mb-6">Horario Semanal</h1>

      {loading ? (
        <Loading fullScreen />
      ) : clases.length === 0 ? (
        <EmptyState
          title="No hay clases programadas"
          message="No se encontraron clases en el horario semanal"
        />
      ) : (
        <ClaseCalendario
          clases={clases}
          onSeleccionarClase={handleSeleccionarClase}
        />
      )}
    </MainLayout>
  );
};

