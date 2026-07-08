package ar.edu.utn.frba.dds.climAlert.services.impl;

import ar.edu.utn.frba.dds.climAlert.client.WeatherApiClient;
import ar.edu.utn.frba.dds.climAlert.domain.Alerta;
import ar.edu.utn.frba.dds.climAlert.domain.Clima;
import ar.edu.utn.frba.dds.climAlert.domain.Notificacion;
import ar.edu.utn.frba.dds.climAlert.dto.ClimaDto;
import ar.edu.utn.frba.dds.climAlert.repositories.ClimaRepository;
import ar.edu.utn.frba.dds.climAlert.services.ClimaService;
import ar.edu.utn.frba.dds.climAlert.services.NotificacionService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClimaServiceImpl implements ClimaService {
    private final ClimaRepository climaRepository;
    private final AlertaServiceImpl alertaService;
    private final WeatherApiClient weatherApiClient;
    private final NotificacionService notificacionService;

    private static final List<String> DESTINATARIOS_FIJOS = List.of(
            "admin@clima.com",
            "emergencias@clima.com",
            "meteorologia@clima.com"
    );

    public ClimaServiceImpl(ClimaRepository climaRepository, AlertaServiceImpl alertaService, WeatherApiClient weatherApiClient, NotificacionService notificacionService) {
        this.climaRepository = climaRepository;
        this.alertaService = alertaService;
        this.weatherApiClient = weatherApiClient;
        this.notificacionService = notificacionService;
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

        List<Alerta> alertas = alertaService.obtenerTodos();
        if (!alertas.isEmpty() && alertas.getFirst().isAlertClimate(clima)) {
            String cuerpo = """
                    Alerta climatológica
                    Temperatura: %s°C
                    Humedad: %s%
                    Fecha: %s
                    """.formatted(clima.getTemperature(), clima.getHumidity(), clima.getDate());

            for (String destinatario : DESTINATARIOS_FIJOS) {
                Notificacion notificacion = new Notificacion(
                        destinatario,
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
