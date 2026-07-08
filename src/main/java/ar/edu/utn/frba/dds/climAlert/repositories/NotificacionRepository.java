package ar.edu.utn.frba.dds.climAlert.repositories;

import ar.edu.utn.frba.dds.climAlert.domain.Notificacion;

import java.util.List;
import java.util.Optional;

public interface NotificacionRepository {
    Notificacion save(Notificacion notificacion);
    Optional<Notificacion> findById(String id);
    List<Notificacion> findAll();
    List<Notificacion> findPendientes();
    void deleteById(String id);
}
