package ar.edu.utn.frba.dds.climAlert.services;

import ar.edu.utn.frba.dds.climAlert.domain.Alerta;

import java.util.List;
import java.util.Optional;

public interface AlertaService {
    Alerta crearAlerta(Alerta alerta);
    Optional<Alerta> obtenerAlerta(String id);
    List<Alerta> obtenerTodos();
    Alerta actualizarAlerta(Alerta alerta);
    void eliminarAlerta(String id);
}
