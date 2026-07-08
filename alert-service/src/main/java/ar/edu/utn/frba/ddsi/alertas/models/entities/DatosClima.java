package ar.edu.utn.frba.ddsi.alertas.models.entities;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatosClima {
  private Long id;
  private LocalDateTime timestamp;
  private double temperatura;
  private double humedad;
  private String ubicacion;
}
