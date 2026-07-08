package ar.edu.utn.frba.dds.climAlert.services.impl;

import ar.edu.utn.frba.dds.climAlert.domain.Alerta;
import ar.edu.utn.frba.dds.climAlert.repositories.AlertaRepository;
import ar.edu.utn.frba.dds.climAlert.services.AlertaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertaServiceImpl implements AlertaService {
    private final AlertaRepository alertaRepository;

    public AlertaServiceImpl(AlertaRepository alertaRepository) {
        this.alertaRepository = alertaRepository;
    }

    @Override
    public Alerta crearAlerta(Alerta alerta) {
        return alertaRepository.save(alerta);
    }

    @Override
    public Optional<Alerta> obtenerAlerta(String id) {
        return alertaRepository.findById(id);
    }

    @Override
    public List<Alerta> obtenerTodos() {
        return alertaRepository.findAll();
    }

    @Override
    public Alerta actualizarAlerta(Alerta alerta) {
        return alertaRepository.save(alerta);
    }

    @Override
    public void eliminarAlerta(String id) {
        alertaRepository.deleteById(id);
    }
}
