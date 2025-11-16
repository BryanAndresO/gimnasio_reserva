import React from 'react';
import { Card } from '../../components/common/Card';
import { Breadcrumb } from '../../components/layout/Breadcrumb';
import { Button } from '../../components/common/Button';

export const Settings: React.FC = () => {
  return (
    <div>
      <Breadcrumb items={[{ label: 'Dashboard', path: '/dashboard' }, { label: 'Configuración' }]} />
      <h1 className="text-3xl font-bold mb-6">Configuración</h1>
      
      <Card title="Preferencias">
        <div className="space-y-4">
          <div>
            <label className="flex items-center">
              <input type="checkbox" className="mr-2" />
              Notificaciones por email
            </label>
          </div>
          <div>
            <label className="flex items-center">
              <input type="checkbox" className="mr-2" />
              Recordatorios de clases
            </label>
          </div>
          <Button variant="primary">Guardar Preferencias</Button>
        </div>
      </Card>
    </div>
  );
};

