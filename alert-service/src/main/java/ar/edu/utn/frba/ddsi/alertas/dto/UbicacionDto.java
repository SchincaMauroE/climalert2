package ar.edu.utn.frba.ddsi.alertas.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UbicacionDto(
    String name,
    String country
) {}
