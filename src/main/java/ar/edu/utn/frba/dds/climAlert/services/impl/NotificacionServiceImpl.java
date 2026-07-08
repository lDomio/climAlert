package ar.edu.utn.frba.dds.climAlert.services.impl;

import ar.edu.utn.frba.dds.climAlert.domain.Notificacion;
import ar.edu.utn.frba.dds.climAlert.repositories.NotificacionRepository;
import ar.edu.utn.frba.dds.climAlert.services.EmailServiceAdapter;
import ar.edu.utn.frba.dds.climAlert.services.NotificacionService;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

@Service
public class NotificacionServiceImpl implements NotificacionService {
    private static final Logger log = LoggerFactory.getLogger(NotificacionServiceImpl.class);

    private final NotificacionRepository notificacionRepository;
    private final EmailServiceAdapter emailServiceAdapter;

    public NotificacionServiceImpl(NotificacionRepository notificacionRepository, EmailServiceAdapter emailServiceAdapter) {
        this.notificacionRepository = notificacionRepository;
        this.emailServiceAdapter = emailServiceAdapter;
    }

    @Override
    public Notificacion guardarNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    @Override
    public Optional<Notificacion> obtenerNotificacion(String id) {
        return notificacionRepository.findById(id);
    }

    @Override
    public List<Notificacion> obtenerTodos() {
        return notificacionRepository.findAll();
    }

    @Override
    public Notificacion actualizarNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    @Override
    public void eliminarNotificacion(String id) {
        notificacionRepository.deleteById(id);
    }

    @Override
    public List<Notificacion> obtenerNotificacionesAEnviar() {
        List<Notificacion> pendientes = notificacionRepository.findPendientes();
        log.info("Notificaciones pendientes por enviar: {}", pendientes.size());
        return pendientes;
    }

    @Override
    public void enviarNotificacion() {
        List<Notificacion> notificaciones = obtenerNotificacionesAEnviar();
        notificaciones.forEach(n -> {
            emailServiceAdapter.enviarMail(n.getEmailDestinatario(), "Alerta climatológica", n.getMensaje());
            n.setEnviada(true);
            log.info("Alerta enviada a {}: \"{}\"", n.getEmailDestinatario(), n.getMensaje());
            notificacionRepository.save(n);
        });
    }
}
