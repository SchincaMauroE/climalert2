package ar.edu.utn.frba.ddsi.alertas.repositories;

import ar.edu.utn.frba.ddsi.alertas.models.entities.DatosClima;
import java.util.Optional;

public interface RepositorioClima {
  void guardar(DatosClima clima);
  Optional<DatosClima> buscarUltimo();
}
