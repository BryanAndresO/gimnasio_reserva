import { Navigate, Outlet } from 'react-router-dom';
import { authService } from '../../services/core/authService';
import { ROUTES } from '../../utils/constants';

interface ProtectedRouteProps {
  requiredRole?: string;
}

export const ProtectedRoute: React.FC<ProtectedRouteProps> = ({ requiredRole }) => {
  const isAuthenticated = authService.isAuthenticated();
  const user = authService.getCurrentUser();

  // Si el usuario no está autenticado, redirigir al login
  if (!isAuthenticated) {
    return <Navigate to={ROUTES.LOGIN} replace />;
  }

  // Si se requiere un rol específico y el usuario no lo tiene, redirigir al dashboard
  if (requiredRole && user?.rol !== requiredRole) {
    return <Navigate to={ROUTES.DASHBOARD} replace />;
  }

  // Si todo está bien, renderizar el componente hijo
  return <Outlet />;
};

export default ProtectedRoute;
