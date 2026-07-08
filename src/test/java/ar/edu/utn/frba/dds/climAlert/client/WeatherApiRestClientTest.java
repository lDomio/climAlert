package ar.edu.utn.frba.dds.climAlert.client;

import ar.edu.utn.frba.dds.climAlert.dto.ClimaDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherApiRestClientTest {

    @Test
    void shouldReturnClimaDtoWhenFetchingWeather() {
        var restTemplate = mock(RestTemplate.class);

        var expectedDto = new ClimaDto(25.5, 60, "clear sky", Instant.now());

        when(restTemplate.getForObject(anyString(), eq(ClimaDto.class), anyString(), anyString())).thenReturn(expectedDto);

        var client = new WeatherApiRestClient(restTemplate, "Buenos Aires", "https://api.weatherapi.com/v1", "test-key");
        ClimaDto result = client.fetchWeather();

        assertNotNull(result);
        assertEquals(25.5, result.temperature());
        assertEquals(60, result.humidity());
        assertEquals("clear sky", result.description());
    }
}
