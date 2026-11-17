package ar.edu.utn.frba.dds.climAlert.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherApiResponse(
    @JsonProperty("current") CurrentWeather current
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record CurrentWeather(
        @JsonProperty("temp_c") double tempC,
        @JsonProperty("humidity") int humidity,
        @JsonProperty("condition") WeatherCondition condition,
        @JsonProperty("last_updated_epoch") long lastUpdatedEpoch
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record WeatherCondition(
        @JsonProperty("text") String text
    ) {}
}
