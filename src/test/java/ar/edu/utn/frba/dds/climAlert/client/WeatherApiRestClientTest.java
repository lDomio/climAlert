package ar.edu.utn.frba.dds.climAlert.client;

import ar.edu.utn.frba.dds.climAlert.dto.ClimaDto;
import ar.edu.utn.frba.dds.climAlert.dto.WeatherApiResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherApiRestClientTest {

    @Test
    void shouldReturnClimaDtoWhenFetchingWeather() {
        var restTemplate = mock(RestTemplate.class);

        var expectedResponse = new WeatherApiResponse(
            new WeatherApiResponse.CurrentWeather(25.5, 60, new WeatherApiResponse.WeatherCondition("clear sky"), 1700000000L)
        );

        when(restTemplate.getForObject(anyString(), eq(WeatherApiResponse.class), anyString(), anyString())).thenReturn(expectedResponse);

        var client = new WeatherApiRestClient(restTemplate, "Buenos Aires", "https://api.weatherapi.com/v1", "test-key");
        ClimaDto result = client.fetchWeather();

        assertNotNull(result);
        assertEquals(25.5, result.temperature());
        assertEquals(60, result.humidity());
        assertEquals("clear sky", result.description());
    }
}
