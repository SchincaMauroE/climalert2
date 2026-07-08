package ar.edu.utn.frba.ddsi.alertas.models.entities.notificador;

import ar.edu.utn.frba.ddsi.alertas.models.entities.Alerta;
import ar.edu.utn.frba.ddsi.alertas.models.entities.notificador.interfaces.DespachadorNotificaciones;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Component
public class Cartero implements DespachadorNotificaciones {
  private final JavaMailSender mailSender;

  public Cartero(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void enviarAlerta(Alerta alerta) {
    String[] to = alerta.getDestinatarios().toArray(new String[0]);

    SimpleMailMessage mensaje = new SimpleMailMessage();
    mensaje.setTo(to);
    mensaje.setSubject("ALERTA METEOROLÓGICA CRÍTICA - Climalert");
    mensaje.setText(construirCorreo(alerta));

    try {
      mailSender.send(mensaje);
      alerta.setEnviado(true);
    } catch (Exception e) {
      System.err.println("Error al enviar el correo de alerta: " + e.getMessage());
      alerta.setEnviado(false);
    }
  }

  private String construirCorreo(Alerta alerta) {
    var clima = alerta.getClima();
    return """
               Se han detectado condiciones climáticas peligrosas automáticas.
               
               Detalle del Clima:
               --------------------------------------
               Ubicación: %s
               Fecha/Hora: %s
               Temperatura: %.1f°C (Umbral crítico: > 35°C)
               Humedad: %.1f%% (Umbral crítico: > 60%%)
               --------------------------------------
               Por favor, tome las medidas de prevención correspondientes.
               
               Sistema de Monitoreo Climalert.
               """.formatted(
        clima.getUbicacion(),
        alerta.getFechaCreacion(),
        clima.getTemperatura(),
        clima.getHumedad()
    );
  }
}
