package ar.edu.utn.frba.ddsi.alertas.services;

import ar.edu.utn.frba.ddsi.alertas.models.entities.Alerta;
import ar.edu.utn.frba.ddsi.alertas.models.entities.DatosClima;
import ar.edu.utn.frba.ddsi.alertas.models.entities.evaluador.EvaluadorAlerta;
import ar.edu.utn.frba.ddsi.alertas.models.entities.notificador.interfaces.DespachadorNotificaciones;
import ar.edu.utn.frba.ddsi.alertas.models.entities.proveedor.interfaces.ProveedorClima;
import ar.edu.utn.frba.ddsi.alertas.repositories.RepositorioClima;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AlertadorClimaService {
  private final ProveedorClima proveedorClima;
  private final EvaluadorAlerta evaluadorAlerta;
  private final DespachadorNotificaciones despachadorNotificaciones;
  private final RepositorioClima repositorioClima;
  private final String ubicacionDeseada;
  private final List<String> destinatarios;

  public AlertadorClimaService(
      ProveedorClima proveedorClima,
      EvaluadorAlerta evaluadorAlerta,
      DespachadorNotificaciones despachadorNotificaciones,
      RepositorioClima repositorioClima,
      @Value("${climalert.target-location}") String ubicacionDeseada,
      @Value("${climalert.recipients}") List<String> destinatarios
  ) {
    this.proveedorClima = proveedorClima;
    this.evaluadorAlerta = evaluadorAlerta;
    this.despachadorNotificaciones = despachadorNotificaciones;
    this.repositorioClima = repositorioClima;
    this.ubicacionDeseada = ubicacionDeseada;
    this.destinatarios = destinatarios;
  }

  @Scheduled(fixedRate = 300000)
  public void consultarYGuardarClima() {
    DatosClima clima = proveedorClima.traerClimaActual(ubicacionDeseada);
    // GUARDAR EN REPO
    repositorioClima.guardar(clima);
  }

  @Scheduled(fixedRate = 60000)
  public void analizarClimaActual() {
    repositorioClima.buscarUltimo().ifPresent(ultimoClima -> {
      if (evaluadorAlerta.disparaAlerta(ultimoClima)) {
        Alerta alerta = new Alerta(LocalDateTime.now(), ultimoClima, destinatarios);
        despachadorNotificaciones.enviarAlerta(alerta);
        evaluadorAlerta.marcarComoAlertado(ultimoClima);
      }
    });
  }
}
