import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import { ThemeProvider } from './ThemeProvider';
import { ErrorBoundary } from './components/common/ErrorBoundary';
import { AppRoutes } from './AppRoutes';
import './App.css';

function App() {
  return (
    <ErrorBoundary>
      <ThemeProvider>
        <BrowserRouter>
          <AppRoutes />
        </BrowserRouter>
      </ThemeProvider>
    </ErrorBoundary>
  );
}

export default App;
