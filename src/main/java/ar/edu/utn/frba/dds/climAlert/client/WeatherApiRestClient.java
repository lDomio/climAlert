package ar.edu.utn.frba.dds.climAlert.client;

import ar.edu.utn.frba.dds.climAlert.dto.ClimaDto;
import ar.edu.utn.frba.dds.climAlert.dto.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.time.Instant;

@Component
public class WeatherApiRestClient implements WeatherApiClient {
    private final RestTemplate restTemplate;
    private final String city;
    private final String baseUrl;
    private final String apiKey;

    public WeatherApiRestClient(RestTemplate restTemplate, @Value("${weather.api.city}") String city, @Value("${weather.api.base-url}") String baseUrl, @Value("${weather.api.api-key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.city = city;
    }

    @Override
    public ClimaDto fetchWeather() {
        var response = restTemplate.getForObject(
            baseUrl + "/current.json?key={key}&q={city}",
            WeatherApiResponse.class, apiKey, city
        );
        if (response == null || response.current() == null) {
            throw new RuntimeException("WeatherAPI response is empty");
        }
        var current = response.current();
        return new ClimaDto(
            current.tempC(),
            current.humidity(),
            current.condition().text(),
            Instant.ofEpochSecond(current.lastUpdatedEpoch())
        );
    }
}
