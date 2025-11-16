import React from 'react';
import { Link } from 'react-router-dom';
import { ROUTES } from '../../utils/constants';
import { useLocalStorage } from '../../hooks/useLocalStorage';
import { STORAGE_KEYS } from '../../utils/constants';

export const Header: React.FC = () => {
  const [user] = useLocalStorage<any>(STORAGE_KEYS.USER, null);

  return (
    <header className="bg-white shadow-md">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center h-16">
          <Link to={ROUTES.HOME} className="text-2xl font-bold text-blue-600">
            CODEFIT
          </Link>
          <nav className="flex space-x-4">
            {user ? (
              <>
                <Link to={ROUTES.DASHBOARD} className="text-gray-700 hover:text-blue-600">
                  Dashboard
                </Link>
                <Link to={ROUTES.PROFILE} className="text-gray-700 hover:text-blue-600">
                  Perfil
                </Link>
              </>
            ) : (
              <>
                <Link to={ROUTES.LOGIN} className="text-gray-700 hover:text-blue-600">
                  Iniciar Sesión
                </Link>
                <Link to={ROUTES.REGISTER} className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700">
                  Registrarse
                </Link>
              </>
            )}
          </nav>
        </div>
      </div>
    </header>
  );
};

