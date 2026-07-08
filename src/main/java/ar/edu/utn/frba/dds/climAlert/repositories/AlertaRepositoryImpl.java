package ar.edu.utn.frba.dds.climAlert.repositories;

import ar.edu.utn.frba.dds.climAlert.domain.Alerta;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AlertaRepositoryImpl implements AlertaRepository {
    private final Map<String, Alerta> storage = new ConcurrentHashMap<>();

    @Override
    public Alerta save(Alerta alerta) {
        storage.put(alerta.getId(), alerta);
        return alerta;
    }

    @Override
    public Optional<Alerta> findById(String id) {
        return Optional.ofNullable(storage.get(id))
                .filter(a -> !a.isDeleted());
    }

    @Override
    public List<Alerta> findAll() {
        return storage.values().stream()
                .filter(a -> !a.isDeleted())
                .toList();
    }

    @Override
    public void deleteById(String id) {
        Optional.ofNullable(storage.get(id))
                .ifPresent(a -> {
                    a.setDeleted(true);
                    storage.put(id, a);
                });
    }
}
