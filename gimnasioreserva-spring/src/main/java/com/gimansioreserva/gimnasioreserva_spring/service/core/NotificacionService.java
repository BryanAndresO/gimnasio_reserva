package com.gimansioreserva.gimnasioreserva_spring.service.core;

import com.gimansioreserva.gimnasioreserva_spring.domain.Reserva;
import com.gimansioreserva.gimnasioreserva_spring.repository.ReservaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacionService {

    private final ReservaRepository reservaRepository;

    public NotificacionService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Async
    public void enviarRecordatorioReserva(Reserva reserva) {
        // Implementación de envío de recordatorio
        // Por ejemplo: email, SMS, push notification
        LocalDateTime horarioClase = reserva.getClase().getHorario();
        LocalDateTime recordatorio = horarioClase.minusHours(24);
        
        if (LocalDateTime.now().isAfter(recordatorio) && 
            LocalDateTime.now().isBefore(horarioClase) &&
            reserva.getEstado().equals("CONFIRMADA")) {
            // Lógica de notificación
            System.out.println("Recordatorio: Tienes una clase " + reserva.getClase().getNombre() + 
                             " el " + horarioClase);
        }
    }

    @Async
    public void enviarConfirmacionReserva(Reserva reserva) {
        // Implementación de confirmación de reserva
        System.out.println("Confirmación: Tu reserva para " + reserva.getClase().getNombre() + 
                         " ha sido confirmada");
    }

    @Async
    public void enviarCancelacionReserva(Reserva reserva) {
        // Implementación de notificación de cancelación
        System.out.println("Cancelación: Tu reserva para " + reserva.getClase().getNombre() + 
                         " ha sido cancelada");
    }

    public void enviarRecordatoriosProximos() {
        List<Reserva> reservasProximas = reservaRepository.findByEstado("CONFIRMADA").stream()
                .filter(r -> {
                    LocalDateTime horario = r.getClase().getHorario();
                    LocalDateTime ahora = LocalDateTime.now();
                    LocalDateTime en24Horas = ahora.plusHours(24);
                    return horario.isAfter(ahora) && horario.isBefore(en24Horas);
                })
                .toList();
        
        reservasProximas.forEach(this::enviarRecordatorioReserva);
    }
}

