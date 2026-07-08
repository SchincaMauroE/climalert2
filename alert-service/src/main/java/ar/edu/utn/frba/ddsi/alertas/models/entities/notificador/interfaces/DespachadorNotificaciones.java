package ar.edu.utn.frba.ddsi.alertas.models.entities.notificador.interfaces;

import ar.edu.utn.frba.ddsi.alertas.models.entities.Alerta;

public interface DespachadorNotificaciones {
  void enviarAlerta(Alerta alerta);
}
