import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import { ROUTES } from '../../utils/constants';
import { classNames } from '../../utils/helpers';

export const Sidebar: React.FC = () => {
  const location = useLocation();

  const menuItems = [
    { path: ROUTES.DASHBOARD, label: 'Dashboard', icon: '📊' },
    { path: ROUTES.PROFILE, label: 'Perfil', icon: '👤' },
    { path: ROUTES.SETTINGS, label: 'Configuración', icon: '⚙️' },
    { path: ROUTES.NOTIFICATIONS, label: 'Notificaciones', icon: '🔔' },
  ];

  return (
    <aside className="w-64 bg-white shadow-md p-4">
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
    </aside>
  );
};

