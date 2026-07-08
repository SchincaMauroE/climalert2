package ar.edu.utn.frba.ddsi.alertas.models.entities.proveedor.interfaces;

import ar.edu.utn.frba.ddsi.alertas.models.entities.DatosClima;

public interface ProveedorClima {
  DatosClima traerClimaActual(String ubicacion);
}
