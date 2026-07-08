package ar.edu.utn.frba.dds.climAlert.services.impl;

import ar.edu.utn.frba.dds.climAlert.client.WeatherApiClient;
import ar.edu.utn.frba.dds.climAlert.domain.Alerta;
import ar.edu.utn.frba.dds.climAlert.domain.Clima;
import ar.edu.utn.frba.dds.climAlert.domain.Notificacion;
import ar.edu.utn.frba.dds.climAlert.dto.ClimaDto;
import ar.edu.utn.frba.dds.climAlert.repositories.ClimaRepository;
import ar.edu.utn.frba.dds.climAlert.services.EntidadService;
import ar.edu.utn.frba.dds.climAlert.services.NotificacionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClimaServiceImplTest {

    @Mock ClimaRepository climaRepository;
    @Mock AlertaServiceImpl alertaService;
    @Mock WeatherApiClient weatherApiClient;
    @Mock NotificacionService notificacionService;
    @Mock EntidadService entidadService;

    private ClimaServiceImpl sut;

    private final Alerta alertaConfig = new Alerta(35, 60, new Date());

    @BeforeEach
    void setUp() {
        sut = new ClimaServiceImpl(climaRepository, alertaService, weatherApiClient, notificacionService, entidadService);
    }

    @Test
    void procesarDatosClimaActual_debeGuardarClimaYCrearNotificacionesCuandoHayAlerta() {
        ClimaDto dto = new ClimaDto(40, 80, "tormenta", Instant.now());
        when(weatherApiClient.fetchWeather()).thenReturn(dto);
        when(climaRepository.save(any())).thenAnswer(i -> i.getArgument(0));
        when(alertaService.obtenerTodos()).thenReturn(List.of(alertaConfig));

        Clima resultado = sut.procesarDatosClimaActual();

        assertNotNull(resultado);
        assertEquals(40, resultado.getTemperature());
        assertEquals(80, resultado.getHumidity());
        verify(notificacionService, times(3)).guardarNotificacion(any());
    }

    @Test
    void procesarDatosClimaActual_debeGuardarConLosDestinatariosCorrectos() {
        ClimaDto dto = new ClimaDto(40, 80, "tormenta", Instant.now());
        when(weatherApiClient.fetchWeather()).thenReturn(dto);
        when(climaRepository.save(any())).thenAnswer(i -> i.getArgument(0));
        when(alertaService.obtenerTodos()).thenReturn(List.of(alertaConfig));

        sut.procesarDatosClimaActual();

        ArgumentCaptor<Notificacion> captor = ArgumentCaptor.forClass(Notificacion.class);
        verify(notificacionService, times(3)).guardarNotificacion(captor.capture());

        List<String> emails = captor.getAllValues().stream()
                .map(Notificacion::getEmailDestinatario)
                .toList();
        assertAll(
                () -> assertTrue(emails.contains("admin@clima.com")),
                () -> assertTrue(emails.contains("emergencias@clima.com")),
                () -> assertTrue(emails.contains("meteorologia@clima.com"))
        );
    }

    @Test
    void procesarDatosClimaActual_debeIncluirDetalleClimaEnElCuerpo() {
        ClimaDto dto = new ClimaDto(40, 80, "tormenta", Instant.now());
        when(weatherApiClient.fetchWeather()).thenReturn(dto);
        when(climaRepository.save(any())).thenAnswer(i -> i.getArgument(0));
        when(alertaService.obtenerTodos()).thenReturn(List.of(alertaConfig));

        sut.procesarDatosClimaActual();

        ArgumentCaptor<Notificacion> captor = ArgumentCaptor.forClass(Notificacion.class);
        verify(notificacionService, times(3)).guardarNotificacion(captor.capture());

        String cuerpo = captor.getAllValues().getFirst().getMensaje();
        assertAll(
                () -> assertTrue(cuerpo.contains("40")),
                () -> assertTrue(cuerpo.contains("80")),
                () -> assertTrue(cuerpo.contains("Alerta climatológica"))
        );
    }

    @Test
    void procesarDatosClimaActual_noDebeCrearNotificacionesCuandoNoSuperaUmbrales() {
        ClimaDto dto = new ClimaDto(25, 50, "soleado", Instant.now());
        when(weatherApiClient.fetchWeather()).thenReturn(dto);
        when(climaRepository.save(any())).thenAnswer(i -> i.getArgument(0));
        when(alertaService.obtenerTodos()).thenReturn(List.of(alertaConfig));

        sut.procesarDatosClimaActual();

        verify(notificacionService, never()).guardarNotificacion(any());
    }

    @Test
    void procesarDatosClimaActual_noDebeCrearNotificacionesCuandoNoHayAlertaConfig() {
        ClimaDto dto = new ClimaDto(40, 80, "tormenta", Instant.now());
        when(weatherApiClient.fetchWeather()).thenReturn(dto);
        when(climaRepository.save(any())).thenAnswer(i -> i.getArgument(0));
        when(alertaService.obtenerTodos()).thenReturn(List.of());

        sut.procesarDatosClimaActual();

        verify(notificacionService, never()).guardarNotificacion(any());
    }
}
