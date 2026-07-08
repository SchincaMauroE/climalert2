package ar.edu.utn.frba.ddsi.alertas.models.entities.evaluador;

import ar.edu.utn.frba.ddsi.alertas.models.entities.DatosClima;
import ar.edu.utn.frba.ddsi.alertas.models.entities.evaluador.interfaces.CondicionAlerta;

public class CondicionTemperaturaYHumedad implements CondicionAlerta {
  private static final double marcaTemperatura = 35.0;
  private static final double marcaHumedad = 60.0;

  @Override
  public boolean cumple(DatosClima clima) {
    return clima.getTemperatura() > marcaTemperatura
        && clima.getHumedad() > marcaHumedad;
  }
}
