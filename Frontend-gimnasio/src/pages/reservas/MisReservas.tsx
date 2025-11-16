import React, { useState, useEffect } from 'react';
import { Breadcrumb } from '../../components/layout/Breadcrumb';
import { ReservaCard } from '../../components/reservas/ReservaCard';
import { ReservaCancelModal } from '../../components/reservas/ReservaCancelModal';
import { Loading } from '../../components/common/Loading';
import { EmptyState } from '../../components/common/EmptyState';
import type { ReservaDTO } from '../../services/core/reservaService';
import { reservaService } from '../../services/core/reservaService';
import { useLocalStorage } from '../../hooks/useLocalStorage';
import { STORAGE_KEYS } from '../../utils/constants';
import { Toast } from '../../components/common/Toast';
import type { ToastType } from '../../components/common/Toast';
import { Button } from '../../components/common/Button';

export const MisReservas: React.FC = () => {
  const [reservas, setReservas] = useState<ReservaDTO[]>([]);
  const [loading, setLoading] = useState(true);
  const [reservaACancelar, setReservaACancelar] = useState<ReservaDTO | null>(null);
  const [showCancelModal, setShowCancelModal] = useState(false);
  const [cancelando, setCancelando] = useState(false);
  const [user] = useLocalStorage<any>(STORAGE_KEYS.USER, null);
  const [toast, setToast] = useState<{ message: string; type: ToastType; visible: boolean }>({
    message: '',
    type: 'info',
    visible: false,
  });

  useEffect(() => {
    if (user?.idUsuario) {
      cargarReservas();
    }
  }, [user]);

  const cargarReservas = async () => {
    try {
      setLoading(true);
      const data = await reservaService.obtenerReservasConfirmadas(Number(user?.idUsuario));
      setReservas(Array.isArray(data) ? data : []);
    } catch (error) {
      mostrarToast('Error al cargar las reservas', 'error');
      setReservas([]);
    } finally {
      setLoading(false);
    }
  };

  const handleCancelar = (idReserva: number) => {
    const reserva = reservas.find(r => r.idReserva === idReserva);
    if (reserva) {
      setReservaACancelar(reserva);
      setShowCancelModal(true);
    }
  };

  const confirmarCancelacion = async () => {
    if (!reservaACancelar || !user) return;

    try {
      setCancelando(true);
      await reservaService.cancelarReserva(reservaACancelar.idReserva, Number(user.idUsuario));
      mostrarToast('Reserva cancelada exitosamente', 'success');
      setShowCancelModal(false);
      setReservaACancelar(null);
      cargarReservas();
    } catch (error: any) {
      mostrarToast(error.response?.data?.message || 'Error al cancelar la reserva', 'error');
    } finally {
      setCancelando(false);
    }
  };

  const mostrarToast = (message: string, type: ToastType) => {
    setToast({ message, type, visible: true });
    setTimeout(() => setToast({ ...toast, visible: false }), 3000);
  };

  return (
    <>
      <Breadcrumb items={[{ label: 'Dashboard', path: '/dashboard' }, { label: 'Mis Reservas' }]} />
      
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-3xl font-bold">Mis Reservas</h1>
        <Button variant="primary" onClick={() => window.location.href = '/clases'}>
          Nueva Reserva
        </Button>
      </div>

      {loading ? (
        <Loading fullScreen />
      ) : reservas.length === 0 ? (
        <EmptyState
          title="No tienes reservas"
          message="No tienes reservas confirmadas en este momento"
          actionLabel="Ver Clases Disponibles"
          onAction={() => window.location.href = '/clases'}
        />
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {reservas.map((reserva) => (
            <ReservaCard
              key={reserva.idReserva}
              reserva={reserva}
              onCancelar={handleCancelar}
            />
          ))}
        </div>
      )}

      <ReservaCancelModal
        isOpen={showCancelModal}
        reserva={reservaACancelar}
        onConfirm={confirmarCancelacion}
        onCancel={() => {
          setShowCancelModal(false);
          setReservaACancelar(null);
        }}
        isLoading={cancelando}
      />

      <Toast
        message={toast.message}
        type={toast.type}
        isVisible={toast.visible}
        onClose={() => setToast({ ...toast, visible: false })}
      />
    </>
  );
};

