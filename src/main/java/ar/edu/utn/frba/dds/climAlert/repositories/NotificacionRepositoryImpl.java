package ar.edu.utn.frba.dds.climAlert.repositories;

import ar.edu.utn.frba.dds.climAlert.domain.Notificacion;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class NotificacionRepositoryImpl implements NotificacionRepository {
    private final Map<String, Notificacion> storage = new ConcurrentHashMap<>();

    @Override
    public Notificacion save(Notificacion notificacion) {
        storage.put(notificacion.getId(), notificacion);
        return notificacion;
    }

    @Override
    public Optional<Notificacion> findById(String id) {
        return Optional.ofNullable(storage.get(id))
                .filter(n -> !n.isDeleted());
    }

    @Override
    public List<Notificacion> findAll() {
        return storage.values().stream()
                .filter(n -> !n.isDeleted())
                .toList();
    }

    @Override
    public List<Notificacion> findPendientes() {
        return storage.values().stream()
                .filter(n -> !n.isDeleted() && !n.isEnviada())
                .toList();
    }

    @Override
    public void deleteById(String id) {
        Optional.ofNullable(storage.get(id))
                .ifPresent(n -> {
                    n.setDeleted(true);
                    storage.put(id, n);
                });
    }
}
