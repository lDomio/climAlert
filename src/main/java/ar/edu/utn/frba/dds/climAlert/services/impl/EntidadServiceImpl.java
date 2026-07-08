package ar.edu.utn.frba.dds.climAlert.services.impl;

import ar.edu.utn.frba.dds.climAlert.domain.Entidad;
import ar.edu.utn.frba.dds.climAlert.repositories.EntidadRepository;
import ar.edu.utn.frba.dds.climAlert.services.EntidadService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntidadServiceImpl implements EntidadService {
    private final EntidadRepository entidadRepository;

    public EntidadServiceImpl(EntidadRepository entidadRepository) {
        this.entidadRepository = entidadRepository;
    }

    @Override
    public Entidad crearEntidad(String email) {
        Entidad nuevaEntidad = new Entidad(email);
        nuevaEntidad.setEstaHabilitado(true);
        return entidadRepository.save(nuevaEntidad);
    }

    @Override
    public Optional<Entidad> obtenerEntidad(String id) {
        return entidadRepository.findById(id);
    }

    @Override
    public List<Entidad> obtenerTodos() {
        return entidadRepository.findAll();
    }

    @Override
    public Entidad actualizarEntidad(Entidad entidad) {
        return entidadRepository.save(entidad);
    }

    @Override
    public void eliminarEntidad(String id) {
        entidadRepository.deleteById(id);
    }
}
