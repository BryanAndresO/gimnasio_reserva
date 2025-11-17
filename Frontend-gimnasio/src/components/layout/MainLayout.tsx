import React from 'react';
import { Header } from './Header';
import { Sidebar } from './Sidebar';
import { Footer } from './Footer';

interface MainLayoutProps {
  children: React.ReactNode;
  showSidebar?: boolean; // <-- Nueva prop opcional
}

export const MainLayout: React.FC<MainLayoutProps> = ({ children, showSidebar = true }) => {
  return (
    <div className="min-h-screen flex flex-col">
      <Header />
      <div className="flex flex-1">
        {showSidebar && <Sidebar />} {/* <-- Renderiza solo si showSidebar es true */}
        <main className={`flex-1 p-6 bg-gray-50 ${showSidebar ? '' : 'ml-0'}`}>
          {children}
        </main>
      </div>
      <Footer />
    </div>
  );
};
