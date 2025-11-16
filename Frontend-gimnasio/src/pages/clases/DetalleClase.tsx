import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import type { ClaseDTO } from '../../services/core/claseService';
import { claseService } from '../../services/core/claseService';
import { MainLayout } from '../../components/layout/MainLayout';
import { Breadcrumb } from '../../components/layout/Breadcrumb';
import { Card } from '../../components/common/Card';
import { Button } from '../../components/common/Button';
import { Loading } from '../../components/common/Loading';
import { EmptyState } from '../../components/common/EmptyState';
import { DisponibilidadBadge } from '../../components/clases/DisponibilidadBadge';
import { ReservaConfirmModal } from '../../components/reservas/ReservaConfirmModal';
import { reservaService } from '../../services/core/reservaService';
import { useLocalStorage } from '../../hooks/useLocalStorage';
import { STORAGE_KEYS } from '../../utils/constants';
import { formatDateTime } from '../../utils/formatters';
import { Toast } from '../../components/common/Toast';
import type { ToastType } from '../../components/common/Toast';

export const DetalleClase: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const [clase, setClase] = useState<ClaseDTO | null>(null);
  const [loading, setLoading] = useState(true);
  const [showConfirmModal, setShowConfirmModal] = useState(false);
  const [reservando, setReservando] = useState(false);
  const [user] = useLocalStorage<any>(STORAGE_KEYS.USER, null);
  const [toast, setToast] = useState<{ message: string; type: ToastType; visible: boolean }>({
    message: '',
    type: 'info',
    visible: false,
  });

  useEffect(() => {
    if (id) {
      cargarClase();
    }
  }, [id]);

  const cargarClase = async () => {
    try {
      setLoading(true);
      const data = await claseService.obtenerPorId(Number(id));
      setClase(data);
    } catch (error) {
      mostrarToast('Error al cargar la clase', 'error');
      navigate('/clases');
    } finally {
      setLoading(false);
    }
  };

  const handleReservar = () => {
    if (clase) {
      setShowConfirmModal(true);
    }
  };

  const confirmarReserva = async () => {
    if (!clase || !user) return;

    try {
      setReservando(true);
      await reservaService.crearReserva(user.idUsuario, clase.idClase);
      mostrarToast('Reserva confirmada exitosamente', 'success');
      setShowConfirmModal(false);
      setTimeout(() => navigate('/reservas'), 1500);
    } catch (error: any) {
      mostrarToast(error.response?.data?.message || 'Error al crear la reserva', 'error');
    } finally {
      setReservando(false);
    }
  };

  const mostrarToast = (message: string, type: ToastType) => {
    setToast({ message, type, visible: true });
    setTimeout(() => setToast({ ...toast, visible: false }), 3000);
  };

  if (loading) {
    return (
      <MainLayout>
        <Loading fullScreen />
      </MainLayout>
    );
  }

  if (!clase) {
    return (
      <MainLayout>
        <EmptyState
          title="Clase no encontrada"
          message="La clase que buscas no existe"
          actionLabel="Volver al catálogo"
          onAction={() => navigate('/clases')}
        />
      </MainLayout>
    );
  }

  return (
    <MainLayout>
      <Breadcrumb
        items={[
          { label: 'Dashboard', path: '/dashboard' },
          { label: 'Clases', path: '/clases' },
          { label: clase.nombre },
        ]}
      />

      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        <div className="lg:col-span-2">
          <Card title={clase.nombre}>
            {clase.descripcion && (
              <p className="text-gray-600 mb-4">{clase.descripcion}</p>
            )}

            <div className="space-y-3">
              <div className="flex items-center">
                <span className="font-medium w-32">Horario:</span>
                <span>{formatDateTime(clase.horario)}</span>
              </div>
              <div className="flex items-center">
                <span className="font-medium w-32">Duración:</span>
                <span>{clase.duracionMinutos} minutos</span>
              </div>
              <div className="flex items-center">
                <span className="font-medium w-32">Entrenador:</span>
                <span>{clase.nombreEntrenador}</span>
              </div>
              <div className="flex items-center">
                <span className="font-medium w-32">Especialidad:</span>
                <span>{clase.especialidadEntrenador}</span>
              </div>
            </div>
          </Card>
        </div>

        <div>
          <Card title="Disponibilidad">
            <div className="space-y-4">
              <DisponibilidadBadge
                cuposDisponibles={clase.cuposDisponibles}
                cupoTotal={clase.cupo}
                disponible={clase.cuposDisponibles > 0}
              />

              <div className="pt-4 border-t">
                <p className="text-sm text-gray-600 mb-4">
                  {clase.cuposDisponibles > 0
                    ? `Hay ${clase.cuposDisponibles} cupos disponibles. ¡Reserva tu lugar ahora!`
                    : 'Lo sentimos, no hay cupos disponibles para esta clase.'}
                </p>

                {clase.cuposDisponibles > 0 && (
                  <Button
                    variant="primary"
                    fullWidth
                    onClick={handleReservar}
                  >
                    Reservar Clase
                  </Button>
                )}
              </div>
            </div>
          </Card>
        </div>
      </div>

      <ReservaConfirmModal
        isOpen={showConfirmModal}
        clase={clase}
        onConfirm={confirmarReserva}
        onCancel={() => setShowConfirmModal(false)}
        isLoading={reservando}
      />

      <Toast
        message={toast.message}
        type={toast.type}
        isVisible={toast.visible}
        onClose={() => setToast({ ...toast, visible: false })}
      />
    </MainLayout>
  );
};

