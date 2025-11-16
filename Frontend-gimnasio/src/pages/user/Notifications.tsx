import React from 'react';
import { Card } from '../../components/common/Card';
import { Breadcrumb } from '../../components/layout/Breadcrumb';
import { EmptyState } from '../../components/common/EmptyState';

export const Notifications: React.FC = () => {
  return (
    <div>
      <Breadcrumb items={[{ label: 'Dashboard', path: '/dashboard' }, { label: 'Notificaciones' }]} />
      <h1 className="text-3xl font-bold mb-6">Notificaciones</h1>
      
      <Card>
        <EmptyState
          title="No hay notificaciones"
          message="No tienes notificaciones nuevas"
        />
      </Card>
    </div>
  );
};

