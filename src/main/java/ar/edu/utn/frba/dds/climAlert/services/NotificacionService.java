package ar.edu.utn.frba.dds.climAlert.services;

import ar.edu.utn.frba.dds.climAlert.domain.Notificacion;

import java.util.List;
import java.util.Optional;

public interface NotificacionService {
    Notificacion guardarNotificacion(Notificacion notificacion);
    Optional<Notificacion> obtenerNotificacion(String id);
    List<Notificacion> obtenerTodos();
    Notificacion actualizarNotificacion(Notificacion notificacion);
    void eliminarNotificacion(String id);
    List<Notificacion> obtenerNotificacionesAEnviar();
    void enviarNotificacion();
}
