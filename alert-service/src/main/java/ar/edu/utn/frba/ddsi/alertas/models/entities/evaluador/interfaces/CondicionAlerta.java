package ar.edu.utn.frba.ddsi.alertas.models.entities.evaluador.interfaces;

import ar.edu.utn.frba.ddsi.alertas.models.entities.DatosClima;

public interface CondicionAlerta {
  boolean cumple(DatosClima clima);
}
