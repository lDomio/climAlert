package ar.edu.utn.frba.dds.climAlert.services.impl;

import ar.edu.utn.frba.dds.climAlert.client.WeatherApiClient;
import ar.edu.utn.frba.dds.climAlert.domain.Alerta;
import ar.edu.utn.frba.dds.climAlert.domain.Clima;
import ar.edu.utn.frba.dds.climAlert.domain.Entidad;
import ar.edu.utn.frba.dds.climAlert.domain.Notificacion;
import ar.edu.utn.frba.dds.climAlert.dto.ClimaDto;
import ar.edu.utn.frba.dds.climAlert.repositories.ClimaRepository;
import ar.edu.utn.frba.dds.climAlert.services.ClimaService;
import ar.edu.utn.frba.dds.climAlert.services.EntidadService;
import ar.edu.utn.frba.dds.climAlert.services.NotificacionService;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClimaServiceImpl implements ClimaService {
    private final ClimaRepository climaRepository;
    private final AlertaServiceImpl alertaService;
    private final WeatherApiClient weatherApiClient;
    private final NotificacionService notificacionService;
    private final EntidadService entidadService;

    private static final Logger log = LoggerFactory.getLogger(ClimaServiceImpl.class);

    public ClimaServiceImpl(ClimaRepository climaRepository, AlertaServiceImpl alertaService, WeatherApiClient weatherApiClient, NotificacionService notificacionService, EntidadService entidadService) {
        this.climaRepository = climaRepository;
        this.alertaService = alertaService;
        this.weatherApiClient = weatherApiClient;
        this.notificacionService = notificacionService;
        this.entidadService = entidadService;
    }

    @Override
    public Clima procesarDatosClimaActual() {
        ClimaDto dto = weatherApiClient.fetchWeather();
        Clima clima = new Clima(
                Date.from(dto.timestamp()),
                (int) dto.temperature(),
                dto.humidity(),
                true
        );
        climaRepository.save(clima);
        log.info("Clima actual guardado: {}°C, {}% humedad, {}", dto.temperature(), dto.humidity(), dto.timestamp());

        List<Alerta> alertas = alertaService.obtenerTodos();
        if (!alertas.isEmpty() && alertas.getFirst().isAlertClimate(clima)) {
            String cuerpo = """
                    Alerta climatológica
                    Temperatura: %s°C
                    Humedad: %s%%
                    Fecha: %s
                    """.formatted(clima.getTemperature(), clima.getHumidity(), clima.getDate());

            List<Entidad> destinatarios = this.entidadService.obtenerTodos();
            for (Entidad e : destinatarios) {
                Notificacion notificacion = new Notificacion(
                        e.getEmail(),
                        cuerpo,
                        new Date()
                );
                notificacionService.guardarNotificacion(notificacion);
            }
        }

        return clima;
    }

    @Override
    public Optional<Clima> obtenerClima(String id) {
        return climaRepository.findById(id);
    }

    @Override
    public List<Clima> obtenerTodos() {
        return climaRepository.findAll();
    }

    @Override
    public Clima actualizarClima(Clima clima) {
        return climaRepository.save(clima);
    }

    @Override
    public void eliminarClima(String id) {
        climaRepository.deleteById(id);
    }
}
