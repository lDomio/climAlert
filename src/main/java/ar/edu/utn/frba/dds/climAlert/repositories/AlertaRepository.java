package ar.edu.utn.frba.dds.climAlert.repositories;

import ar.edu.utn.frba.dds.climAlert.domain.Alerta;

import java.util.List;
import java.util.Optional;

public interface AlertaRepository {
    Alerta save(Alerta alerta);
    Optional<Alerta> findById(String id);
    List<Alerta> findAll();
    void deleteById(String id);
}
