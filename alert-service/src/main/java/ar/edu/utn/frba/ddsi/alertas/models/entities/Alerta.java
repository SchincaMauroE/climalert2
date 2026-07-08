package ar.edu.utn.frba.ddsi.alertas.models.entities;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alerta {
  private Long id;
  private LocalDateTime fechaCreacion;
  private DatosClima clima;
  private List<String> destinatarios;
  private boolean enviado;

  public Alerta(LocalDateTime fechaCreacion, DatosClima clima, List<String> destinatarios) {
    this.fechaCreacion = fechaCreacion;
    this.clima = clima;
    this.destinatarios = destinatarios;
    this.enviado = false; // Por defecto
  }
}
