import React from 'react';
import { Link } from 'react-router-dom';
import { ROUTES } from '../../utils/constants';

export const Footer: React.FC = () => {
  return (
    <footer className="bg-gray-800 text-white py-8">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
          <div>
            <h3 className="text-lg font-semibold mb-4">CODEFIT</h3>
            <p className="text-gray-400">Sistema de gestión de reservas para gimnasio</p>
          </div>
          <div>
            <h3 className="text-lg font-semibold mb-4">Enlaces</h3>
            <ul className="space-y-2 text-gray-400">
              <li><Link to={ROUTES.HOME} className="hover:text-white">Inicio</Link></li>
              <li><Link to={ROUTES.ABOUT} className="hover:text-white">Acerca de</Link></li>
              <li><Link to={ROUTES.CONTACT} className="hover:text-white">Contacto</Link></li>
              <li><Link to={ROUTES.PRICING} className="hover:text-white">Precios</Link></li>
            </ul>
          </div>
          <div>
            <h3 className="text-lg font-semibold mb-4">Contacto</h3>
            <p className="text-gray-400">Email: info@codefit.com</p>
            <p className="text-gray-400">Teléfono: +34 123 456 789</p>
          </div>
        </div>
        <div className="mt-8 pt-8 border-t border-gray-700 text-center text-gray-400">
          <p>&copy; 2024 CODEFIT. Todos los derechos reservados.</p>
        </div>
      </div>
    </footer>
  );
};

