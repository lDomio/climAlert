package ar.edu.utn.frba.dds.climAlert.client;

import ar.edu.utn.frba.dds.climAlert.dto.ClimaDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherApiRestClient implements WeatherApiClient {
    private final RestTemplate restTemplate;
    private final String city;
    private final String baseUrl;

    public WeatherApiRestClient(RestTemplate restTemplate, @Value("${weather.api.city}") String city, @Value("${weather.api.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.city = city;
    }

    @Override
    public ClimaDto fetchWeather() {
        return restTemplate.getForObject("{baseUrl}/current.json?q={city}", ClimaDto.class, baseUrl, city);
    }
}
