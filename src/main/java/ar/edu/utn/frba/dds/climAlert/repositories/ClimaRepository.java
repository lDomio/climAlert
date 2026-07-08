package ar.edu.utn.frba.dds.climAlert.repositories;

import ar.edu.utn.frba.dds.climAlert.domain.Clima;

import java.util.List;
import java.util.Optional;

public interface ClimaRepository {
    Clima save(Clima clima);
    Optional<Clima> findById(String id);
    List<Clima> findAll();
    void deleteById(String id);
}
