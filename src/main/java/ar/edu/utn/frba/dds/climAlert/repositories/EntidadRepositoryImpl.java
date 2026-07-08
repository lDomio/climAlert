package ar.edu.utn.frba.dds.climAlert.repositories;

import ar.edu.utn.frba.dds.climAlert.domain.Entidad;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class EntidadRepositoryImpl implements EntidadRepository {
    private final Map<String, Entidad> storage = new ConcurrentHashMap<>();

    @Override
    public Entidad save(Entidad entidad) {
        storage.put(entidad.getId(), entidad);
        return entidad;
    }

    @Override
    public Optional<Entidad> findById(String id) {
        return Optional.ofNullable(storage.get(id))
                .filter(Entidad::isEstaHabilitado);
    }

    @Override
    public List<Entidad> findAll() {
        return storage.values().stream()
                .filter(Entidad::isEstaHabilitado)
                .toList();
    }

    @Override
    public void deleteById(String id) {
        Optional.ofNullable(storage.get(id))
                .ifPresent(e -> {
                    e.setEstaHabilitado(false);
                    storage.put(id, e);
                });
    }
}
