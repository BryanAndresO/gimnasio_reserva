import React from 'react';
import { Link } from 'react-router-dom';
import { ROUTES } from '../../utils/constants';
import { Button } from '../../components/common/Button';
import { Header } from '../../components/layout/Header';
import { Footer } from '../../components/layout/Footer';

export const Home: React.FC = () => {
  return (
    <div className="min-h-screen flex flex-col">
      <Header />
      <main className="flex-1">
        <section className="bg-gradient-to-r from-blue-600 to-blue-800 text-white py-20">
          <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
            <h1 className="text-4xl md:text-6xl font-bold mb-4">
              Bienvenido a CODEFIT
            </h1>
            <p className="text-xl mb-8">
              Reserva tus clases de forma fácil y rápida
            </p>
            <div className="flex justify-center space-x-4">
              <Link to={ROUTES.REGISTER}>
                <Button variant="success" size="lg">
                  Comenzar Ahora
                </Button>
              </Link>
              <Link to={ROUTES.ABOUT}>
                <Button variant="secondary" size="lg">
                  Saber Más
                </Button>
              </Link>
            </div>
          </div>
        </section>

        <section className="py-16 bg-white">
          <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            <h2 className="text-3xl font-bold text-center mb-12">Características</h2>
            <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
              <div className="text-center">
                <div className="text-4xl mb-4">📅</div>
                <h3 className="text-xl font-semibold mb-2">Reservas Fáciles</h3>
                <p className="text-gray-600">Reserva tus clases en solo unos clics</p>
              </div>
              <div className="text-center">
                <div className="text-4xl mb-4">👥</div>
                <h3 className="text-xl font-semibold mb-2">Entrenadores Expertos</h3>
                <p className="text-gray-600">Profesionales certificados para tu entrenamiento</p>
              </div>
              <div className="text-center">
                <div className="text-4xl mb-4">📊</div>
                <h3 className="text-xl font-semibold mb-2">Seguimiento</h3>
                <p className="text-gray-600">Monitorea tu progreso y asistencia</p>
              </div>
            </div>
          </div>
        </section>
      </main>
      <Footer />
    </div>
  );
};

