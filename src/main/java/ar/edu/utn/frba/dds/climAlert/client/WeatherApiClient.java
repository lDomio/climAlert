package ar.edu.utn.frba.dds.climAlert.client;

import ar.edu.utn.frba.dds.climAlert.dto.ClimaDto;

public interface WeatherApiClient {
    ClimaDto fetchWeather();
}
