package ar.edu.utn.frba.ddsi.alertas.models.entities.proveedor;

import ar.edu.utn.frba.ddsi.alertas.dto.WeatherApiResponse;
import ar.edu.utn.frba.ddsi.alertas.models.entities.DatosClima;
import ar.edu.utn.frba.ddsi.alertas.models.entities.proveedor.interfaces.ProveedorClima;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class clienteWeatherApi implements ProveedorClima {
  private final RestClient cliente;
  private final String llaveApi;
  private final String urlBase = "http://api.weatherapi.com/v1/current.json";

  public clienteWeatherApi(@Value("${weatherapi.key}") String apiKey) {
    this.cliente = RestClient.create();
    this.llaveApi = apiKey;
  }

  @Override
  public DatosClima traerClimaActual(String ubicacion) {
    // Consumir la API Externa mapeando al DTO
    WeatherApiResponse response = cliente.get()
        .uri(urlBase + "?key={key}&q={q}", llaveApi, ubicacion)
        .retrieve()
        .body(WeatherApiResponse.class);

    if (response == null || response.climaActual() == null) {
      throw new RuntimeException("No se pudieron obtener datos válidos de WeatherAPI");
    }

    // Convertir el DTO a la Entidad de Dominio
    DatosClima datosClima = new DatosClima();
    datosClima.setUbicacion(response.ubicacion().name());
    datosClima.setTemperatura(response.climaActual().tempC());
    datosClima.setHumedad(response.climaActual().humidity());
    datosClima.setTimestamp(java.time.LocalDateTime.now());

    return datosClima;
  }
}
