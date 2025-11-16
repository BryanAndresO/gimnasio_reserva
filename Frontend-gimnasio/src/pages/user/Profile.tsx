import React, { useState } from 'react';
import { Card } from '../../components/common/Card';
import { Breadcrumb } from '../../components/layout/Breadcrumb';
import { Button } from '../../components/common/Button';
import { Input } from '../../components/common/Input';

export const Profile: React.FC = () => {
  const [formData, setFormData] = useState({
    nombre: '',
    correo: '',
    telefono: '',
  });

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    console.log('Profile updated:', formData);
  };

  return (
    <div>
      <Breadcrumb items={[{ label: 'Dashboard', path: '/dashboard' }, { label: 'Perfil' }]} />
      <h1 className="text-3xl font-bold mb-6">Mi Perfil</h1>
      
      <Card title="Información Personal">
        <form onSubmit={handleSubmit} className="space-y-4">
          <Input
            label="Nombre"
            value={formData.nombre}
            onChange={(e) => setFormData({ ...formData, nombre: e.target.value })}
          />
          <Input
            label="Email"
            type="email"
            value={formData.correo}
            onChange={(e) => setFormData({ ...formData, correo: e.target.value })}
          />
          <Input
            label="Teléfono"
            type="tel"
            value={formData.telefono}
            onChange={(e) => setFormData({ ...formData, telefono: e.target.value })}
          />
          <Button type="submit" variant="primary">
            Guardar Cambios
          </Button>
        </form>
      </Card>
    </div>
  );
};

