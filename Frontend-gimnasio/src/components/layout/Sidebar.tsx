import React, { useEffect, useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { ROUTES, STORAGE_KEYS } from '../../utils/constants';
import { classNames } from '../../utils/helpers';

interface UserData {
  rol: string;
}

export const Sidebar: React.FC = () => {
  const location = useLocation();
  const [userData, setUserData] = useState<UserData | null>(null);

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

  const userMenuItems = [
    { path: ROUTES.DASHBOARD, label: 'Dashboard', icon: '📊' },
    { path: ROUTES.CLASES, label: 'Clases', icon: '🏋️' },
    { path: ROUTES.RESERVAS, label: 'Mis Reservas', icon: '📅' },
    { path: ROUTES.PROFILE, label: 'Perfil', icon: '👤' },
    { path: ROUTES.SETTINGS, label: 'Configuración', icon: '⚙️' },
  ];

  const adminMenuItems = [
    { path: ROUTES.ADMIN_DASHBOARD, label: 'Panel Admin', icon: '🎯' },
    { path: ROUTES.ADMIN_USUARIOS, label: 'Usuarios', icon: '👥' },
    { path: ROUTES.ADMIN_CLASES, label: 'Clases', icon: '🏋️' },
    { path: ROUTES.ADMIN_ENTRENADORES, label: 'Entrenadores', icon: '💪' },
    { path: ROUTES.ADMIN_RESERVAS, label: 'Reservas', icon: '📅' },
    { path: ROUTES.ADMIN_REPORTES, label: 'Reportes', icon: '📊' },
  ];

  const menuItems = isAdmin ? adminMenuItems : userMenuItems;

  return (
    <aside className="w-64 bg-white shadow-md p-4">
      <div className="mb-4 p-3 bg-gray-100 rounded-lg">
        <p className="text-xs text-gray-600 uppercase font-semibold">
          {isAdmin ? 'Administrador' : 'Usuario'}
        </p>
      </div>

      <nav>
        <ul className="space-y-2">
          {menuItems.map((item) => (
            <li key={item.path}>
              <Link
                to={item.path}
                className={classNames(
                  'flex items-center px-4 py-2 rounded-lg transition-colors',
                  location.pathname === item.path
                    ? 'bg-blue-600 text-white'
                    : 'text-gray-700 hover:bg-gray-100'
                )}
              >
                <span className="mr-2">{item.icon}</span>
                {item.label}
              </Link>
            </li>
          ))}
        </ul>
      </nav>

      {isAdmin && (
        <div className="mt-6 pt-6 border-t border-gray-200">
          <Link
            to={ROUTES.DASHBOARD}
            className="flex items-center px-4 py-2 rounded-lg text-gray-700 hover:bg-gray-100 transition-colors"
          >
            <span className="mr-2">👤</span>
            Vista de Usuario
          </Link>
        </div>
      )}
    </aside>
  );
};

