package ar.edu.utn.frba.dds.climAlert.dto;

import java.time.Instant;

public record ClimaDto(
    double temperature,
    int humidity,
    String description,
    Instant timestamp
) {}
