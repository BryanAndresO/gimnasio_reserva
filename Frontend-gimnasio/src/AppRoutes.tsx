import React from 'react';
import { Routes, Route } from 'react-router-dom';
import { ROUTES } from './utils/constants';
import { MainLayout } from './components/layout/MainLayout';
import { Home } from './pages/public/Home';
import { About } from './pages/public/About';
import { Contact } from './pages/public/Contact';
import { Pricing } from './pages/public/Pricing';
import { NotFound } from './pages/public/NotFound';
import { Register } from './pages/auth/Register';
import { Login } from './pages/auth/Login';
import { Dashboard } from './pages/user/Dashboard';
import { Profile } from './pages/user/Profile';
import { Settings } from './pages/user/Settings';
import { Notifications } from './pages/user/Notifications';
import { CatalogoClases } from './pages/clases/CatalogoClases';
import { DetalleClase } from './pages/clases/DetalleClase';
import { HorarioSemanal } from './pages/clases/HorarioSemanal';
import { MisReservas } from './pages/reservas/MisReservas';
import { NuevaReserva } from './pages/reservas/NuevaReserva';
import { HistorialReservas } from './pages/reservas/HistorialReservas';

export const AppRoutes: React.FC = () => {
  return (
    <Routes>
      {/* Páginas públicas */}
      <Route path={ROUTES.HOME} element={<Home />} />
      <Route path={ROUTES.ABOUT} element={<About />} />
      <Route path={ROUTES.CONTACT} element={<Contact />} />
      <Route path={ROUTES.PRICING} element={<Pricing />} />
      <Route path={ROUTES.REGISTER} element={<Register />} />
      <Route path={ROUTES.LOGIN} element={<Login />} />

      {/* Páginas de usuario */}
      <Route
        path={ROUTES.DASHBOARD}
        element={
          <MainLayout>
            <Dashboard />
          </MainLayout>
        }
      />
      <Route
        path={ROUTES.PROFILE}
        element={
          <MainLayout>
            <Profile />
          </MainLayout>
        }
      />
      <Route
        path={ROUTES.SETTINGS}
        element={
          <MainLayout>
            <Settings />
          </MainLayout>
        }
      />
      <Route
        path={ROUTES.NOTIFICATIONS}
        element={
          <MainLayout>
            <Notifications />
          </MainLayout>
        }
      />

      {/* Páginas de Clases */}
      <Route
        path={ROUTES.CLASES}
        element={
          <MainLayout>
            <CatalogoClases />
          </MainLayout>
        }
      />
      <Route
        path="/clases/:id"
        element={
          <MainLayout>
            <DetalleClase />
          </MainLayout>
        }
      />
      <Route
        path={ROUTES.HORARIO_SEMANAL}
        element={
          <MainLayout>
            <HorarioSemanal />
          </MainLayout>
        }
      />

      {/* Páginas de Reservas */}
      <Route
        path={ROUTES.RESERVAS}
        element={
          <MainLayout>
            <MisReservas />
          </MainLayout>
        }
      />
      <Route
        path={ROUTES.NUEVA_RESERVA}
        element={
          <MainLayout>
            <NuevaReserva />
          </MainLayout>
        }
      />
      <Route
        path={ROUTES.HISTORIAL_RESERVAS}
        element={
          <MainLayout>
            <HistorialReservas />
          </MainLayout>
        }
      />

      {/* 404 */}
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
};

