package ar.edu.utn.frba.ddsi.alertas.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherApiResponse(
  UbicacionDto ubicacion,
  ClimaActualDto climaActual
) {}

