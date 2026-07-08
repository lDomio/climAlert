package ar.edu.utn.frba.dds.climAlert.schedulers;

import ar.edu.utn.frba.dds.climAlert.services.NotificacionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificacionScheduledTask {
    private final NotificacionService notificacionService;

    public NotificacionScheduledTask(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @Scheduled(fixedRate = 60000)
    public void enviarNotificacionesPendientes() {
        notificacionService.enviarNotificacion();
    }
}
