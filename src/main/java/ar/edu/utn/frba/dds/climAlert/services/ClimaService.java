package ar.edu.utn.frba.dds.climAlert.services;

import ar.edu.utn.frba.dds.climAlert.domain.Clima;

import java.util.List;
import java.util.Optional;

public interface ClimaService {
    Clima procesarDatosClimaActual();
    Optional<Clima> obtenerClima(String id);
    List<Clima> obtenerTodos();
    Clima actualizarClima(Clima clima);
    void eliminarClima(String id);
}
