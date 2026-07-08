package ar.edu.utn.frba.ddsi.alertas.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record  ClimaActualDto(
    @JsonProperty("temp_c") double tempC,
    double humidity
) {}
