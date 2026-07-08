package ar.edu.utn.frba.ddsi.alertas.models.entities.evaluador;

import ar.edu.utn.frba.ddsi.alertas.models.entities.DatosClima;
import ar.edu.utn.frba.ddsi.alertas.models.entities.evaluador.interfaces.CondicionAlerta;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class EvaluadorAlerta {
  private final List<CondicionAlerta> condiciones;
  private DatosClima ultimoClimaDisparador = null;

  public EvaluadorAlerta(List<CondicionAlerta> condiciones) {
    this.condiciones = condiciones;
  }

  public boolean disparaAlerta(DatosClima clima) {
    boolean esCritico = condiciones.stream().anyMatch(condicion -> condicion.cumple(clima));

    if (!esCritico) {
      ultimoClimaDisparador = null;
      return false;
    }

    if (yaAlerto(clima)) {
      System.out.println("[Evaluador] Clima crítico detectado, pero ya se notificó previamente. Omitiendo duplicado.");
      return false;
    }

    return true;
  }

  public void marcarComoAlertado(DatosClima clima) {
    this.ultimoClimaDisparador = clima;
  }

  private boolean yaAlerto(DatosClima clima) {
    if (ultimoClimaDisparador == null) {
      return false;
    }

    return doubleEquals(ultimoClimaDisparador.getTemperatura(), clima.getTemperatura())
        && doubleEquals(ultimoClimaDisparador.getHumedad(), clima.getHumedad());
  }

  // Metodo Auxiliar
  private boolean doubleEquals(double d1, double d2) {
    return Math.abs(d1 - d2) < 0.001;
  }
}
