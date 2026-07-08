package ar.edu.utn.frba.dds.climAlert.repositories;

import ar.edu.utn.frba.dds.climAlert.domain.Entidad;

import java.util.List;
import java.util.Optional;

public interface EntidadRepository {
    Entidad save(Entidad entidad);
    Optional<Entidad> findById(String id);
    List<Entidad> findAll();
    void deleteById(String id);
}
