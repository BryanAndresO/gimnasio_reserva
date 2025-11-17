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
        {/* Sección de bienvenida con imagen de fondo */}
        <section
  className="relative text-white h-[450px] flex items-center bg-cover bg-center"
  style={{ backgroundImage: "url('/codefit.jpg')" }}
>
  {/* Overlay */}
  <div className="absolute top-0 left-0 w-full h-full bg-black/40"></div>

  {/* Contenido */}
  <div className="relative z-10 max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
    <h1 className="text-4xl md:text-6xl font-bold mb-4 drop-shadow-lg">
      Entrena sin límites
    </h1>
    <p className="text-xl mb-8 drop-shadow-md">
      Reserva tus clases de manera rápida y sencilla
    </p>
    <div className="flex justify-center space-x-4">
      <Link to={ROUTES.REGISTER}>
        <Button variant="primary" size="lg">
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


        {/* Sección de características */}
        <section className="py-16 bg-white">
          <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            <h2 className="text-3xl font-bold text-center mb-12">Características</h2>
            <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
              <div className="text-center">
                <div className="text-4xl mb-4">📅</div>
                <h3 className="text-xl font-semibold mb-2">Reservas Fáciles</h3>
                <p className="text-gray-600">
                  Reserva tus clases en solo unos clics
                </p>
              </div>
              <div className="text-center">
                <div className="text-4xl mb-4">👥</div>
                <h3 className="text-xl font-semibold mb-2">Entrenadores Expertos</h3>
                <p className="text-gray-600">
                  Profesionales certificados para tu entrenamiento
                </p>
              </div>
              <div className="text-center">
                <div className="text-4xl mb-4">📊</div>
                <h3 className="text-xl font-semibold mb-2">Seguimiento</h3>
                <p className="text-gray-600">
                  Monitorea tu progreso y asistencia
                </p>
              </div>
            </div>
          </div>
        </section>

       {/* Sección de llamada a la acción */}
<section
  className="relative text-white py-16 flex items-center bg-cover bg-center"
  style={{ backgroundImage: "url('/codefit2.jpg')" }}
>
  {/* Overlay */}
  <div className="absolute top-0 left-0 w-full h-full bg-black/40"></div>

  {/* Contenido */}
  <div className="relative z-10 max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
    <h2 className="text-3xl md:text-4xl font-bold mb-4 drop-shadow-lg">
      ¿Listo para empezar?
    </h2>
    <p className="text-lg mb-8 drop-shadow-md">
      Únete hoy y lleva tu entrenamiento al siguiente nivel
    </p>
    <Link to={ROUTES.REGISTER}>
      <Button variant="primary" size="lg">
        Regístrate Ahora
      </Button>
    </Link>
  </div>
</section>

      </main>
      <Footer />
    </div>
  );
};
