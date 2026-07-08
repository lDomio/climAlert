package ar.edu.utn.frba.dds.climAlert.repositories;

import ar.edu.utn.frba.dds.climAlert.domain.Clima;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ClimaRepositoryImpl implements ClimaRepository {
    private final Map<String, Clima> storage = new ConcurrentHashMap<>();

    @Override
    public Clima save(Clima clima) {
        storage.put(clima.getId(), clima);
        return clima;
    }

    @Override
    public Optional<Clima> findById(String id) {
        return Optional.ofNullable(storage.get(id))
                .filter(c -> !c.isDeleted());
    }

    @Override
    public List<Clima> findAll() {
        return storage.values().stream()
                .filter(c -> !c.isDeleted())
                .toList();
    }

    @Override
    public void deleteById(String id) {
        Optional.ofNullable(storage.get(id))
                .ifPresent(c -> {
                    c.setDeleted(true);
                    storage.put(id, c);
                });
    }
}
