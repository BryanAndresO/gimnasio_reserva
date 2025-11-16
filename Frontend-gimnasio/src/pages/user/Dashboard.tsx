import React from 'react';
import { Card } from '../../components/common/Card';
import { Breadcrumb } from '../../components/layout/Breadcrumb';
import { useApi } from '../../hooks/useApi';
import { Loading } from '../../components/common/Loading';

export const Dashboard: React.FC = () => {
  const { data, loading } = useApi('/admin/reportes/general');

  if (loading) return <Loading fullScreen />;

  return (
    <div>
      <Breadcrumb items={[{ label: 'Dashboard' }]} />
      <h1 className="text-3xl font-bold mb-6">Dashboard</h1>
      
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-6">
        <Card>
          <div className="text-center">
            <div className="text-3xl font-bold text-blue-600">0</div>
            <div className="text-gray-600">Reservas Activas</div>
          </div>
        </Card>
        <Card>
          <div className="text-center">
            <div className="text-3xl font-bold text-green-600">0</div>
            <div className="text-gray-600">Clases Completadas</div>
          </div>
        </Card>
        <Card>
          <div className="text-center">
            <div className="text-3xl font-bold text-purple-600">0</div>
            <div className="text-gray-600">Próximas Clases</div>
          </div>
        </Card>
      </div>

      <Card title="Actividad Reciente">
        <p className="text-gray-600">No hay actividad reciente</p>
      </Card>
    </div>
  );
};

