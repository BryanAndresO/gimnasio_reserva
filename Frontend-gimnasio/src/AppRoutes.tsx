import React from 'react';
import { Routes, Route } from 'react-router-dom';
import { ROUTES } from './utils/constants';
import { MainLayout } from './components/layout/MainLayout';
import { Home } from './pages/public/Home';
import { About } from './pages/public/About';
import { Contact } from './pages/public/Contact';
import { Pricing } from './pages/public/Pricing';
import { NotFound } from './pages/public/NotFound';
import { Dashboard } from './pages/user/Dashboard';
import { Profile } from './pages/user/Profile';
import { Settings } from './pages/user/Settings';
import { Notifications } from './pages/user/Notifications';

export const AppRoutes: React.FC = () => {
  return (
    <Routes>
      {/* Páginas públicas */}
      <Route path={ROUTES.HOME} element={<Home />} />
      <Route path={ROUTES.ABOUT} element={<About />} />
      <Route path={ROUTES.CONTACT} element={<Contact />} />
      <Route path={ROUTES.PRICING} element={<Pricing />} />

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

      {/* 404 */}
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
};

