import React from 'react';
import { Header } from '../../components/layout/Header';
import { Footer } from '../../components/layout/Footer';

export const About: React.FC = () => {
  return (
    <div className="min-h-screen flex flex-col">
      <Header />
      <main className="flex-1 max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
        <h1 className="text-4xl font-bold mb-6">Acerca de Nosotros</h1>
        <div className="prose prose-lg">
          <p className="text-gray-600 mb-4">
            CODEFIT es un sistema moderno de gestión de reservas diseñado para facilitar
            la organización de clases y entrenamientos en gimnasios.
          </p>
          <p className="text-gray-600 mb-4">
            Nuestro objetivo es proporcionar una experiencia fluida tanto para los usuarios como
            para los administradores del gimnasio.
          </p>
          <h2 className="text-2xl font-semibold mt-8 mb-4">Nuestra Misión</h2>
          <p className="text-gray-600">
            Facilitar el acceso a clases de fitness y entrenamientos, mejorando la experiencia
            de nuestros usuarios mediante tecnología innovadora.
          </p>
        </div>
      </main>
      <Footer />
    </div>
  );
};

