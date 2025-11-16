import React from 'react';
import { Link } from 'react-router-dom';
import { ROUTES } from '../../utils/constants';
import { Button } from '../../components/common/Button';
import { Header } from '../../components/layout/Header';
import { Footer } from '../../components/layout/Footer';

export const NotFound: React.FC = () => {
  return (
    <div className="min-h-screen flex flex-col">
      <Header />
      <main className="flex-1 flex items-center justify-center">
        <div className="text-center">
          <h1 className="text-6xl font-bold text-gray-900 mb-4">404</h1>
          <h2 className="text-2xl font-semibold text-gray-700 mb-4">
            Página no encontrada
          </h2>
          <p className="text-gray-500 mb-8">
            La página que estás buscando no existe.
          </p>
          <Link to={ROUTES.HOME}>
            <Button variant="primary">Volver al Inicio</Button>
          </Link>
        </div>
      </main>
      <Footer />
    </div>
  );
};

