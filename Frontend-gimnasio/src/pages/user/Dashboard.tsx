import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Card } from '../../components/common/Card';
import { Breadcrumb } from '../../components/layout/Breadcrumb';
import { Button } from '../../components/common/Button';
import { STORAGE_KEYS, ROUTES } from '../../utils/constants';

interface UserData {
  correo: string;
  nombre: string;
  rol: string;
}

export const Dashboard: React.FC = () => {
  const [userData, setUserData] = useState<UserData | null>(null);
  const navigate = useNavigate();

  useEffect(() => {
    const userDataStr = localStorage.getItem(STORAGE_KEYS.USER);
    if (userDataStr) {
      try {
        setUserData(JSON.parse(userDataStr));
      } catch (error) {
        console.error('Error parsing user data:', error);
      }
    }
  }, []);

  const isAdmin = userData?.rol === 'ADMIN';

  return (
    <div>
      <Breadcrumb items={[{ label: 'Dashboard' }]} />

      {userData && (
        <div className="mb-6">
          <h1 className="text-3xl font-bold">Bienvenido, {userData.nombre}</h1>
          <p className="text-gray-600 mt-2">
            <span className={`px-2 py-1 rounded text-xs font-semibold ${
              isAdmin ? 'bg-purple-100 text-purple-800' : 'bg-blue-100 text-blue-800'
            }`}>
              {userData.rol}
            </span>
            <span className="ml-2">{userData.correo}</span>
          </p>
        </div>
      )}

      {isAdmin && (
        <div className="mb-6 p-4 bg-blue-50 border-l-4 border-blue-600 rounded">
          <div className="flex items-center justify-between">
            <div>
              <h3 className="font-semibold text-blue-900">Panel de Administración Disponible</h3>
              <p className="text-sm text-blue-700 mt-1">Accede al panel de administración para gestionar el sistema</p>
            </div>
            <Button onClick={() => navigate(ROUTES.ADMIN_DASHBOARD)}>
              Ir al Panel Admin
            </Button>
          </div>
        </div>
      )}

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

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <Card title="Acciones Rápidas">
          <div className="space-y-3">
            <button
              onClick={() => navigate(ROUTES.NUEVA_RESERVA)}
              className="w-full text-left p-4 border border-gray-200 rounded-lg hover:bg-gray-50 transition-colors"
            >
              <h3 className="font-semibold text-gray-900">Nueva Reserva</h3>
              <p className="text-sm text-gray-600 mt-1">Reserva tu próxima clase</p>
            </button>
            <button
              onClick={() => navigate(ROUTES.CLASES)}
              className="w-full text-left p-4 border border-gray-200 rounded-lg hover:bg-gray-50 transition-colors"
            >
              <h3 className="font-semibold text-gray-900">Ver Clases</h3>
              <p className="text-sm text-gray-600 mt-1">Explora nuestro catálogo de clases</p>
            </button>
            <button
              onClick={() => navigate(ROUTES.HORARIO_SEMANAL)}
              className="w-full text-left p-4 border border-gray-200 rounded-lg hover:bg-gray-50 transition-colors"
            >
              <h3 className="font-semibold text-gray-900">Horario Semanal</h3>
              <p className="text-sm text-gray-600 mt-1">Consulta los horarios disponibles</p>
            </button>
          </div>
        </Card>

        <Card title="Actividad Reciente">
          <p className="text-gray-600">No hay actividad reciente</p>
        </Card>
      </div>
    </div>
  );
};

