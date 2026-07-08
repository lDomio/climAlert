package ar.edu.utn.frba.dds.climAlert.services;

import ar.edu.utn.frba.dds.climAlert.domain.Entidad;

import java.util.List;
import java.util.Optional;

public interface EntidadService {
    Entidad crearEntidad(String email);
    Optional<Entidad> obtenerEntidad(String id);
    List<Entidad> obtenerTodos();
    Entidad actualizarEntidad(Entidad entidad);
    void eliminarEntidad(String id);
}
