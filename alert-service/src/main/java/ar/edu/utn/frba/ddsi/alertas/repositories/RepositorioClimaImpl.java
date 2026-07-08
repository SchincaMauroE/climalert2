package ar.edu.utn.frba.ddsi.alertas.repositories;

import ar.edu.utn.frba.ddsi.alertas.models.entities.DatosClima;
import java.util.Optional;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class RepositorioClimaImpl implements RepositorioClima{

  private final List<DatosClima> memoryStorage = new ArrayList<>();
  private long idSequence = 1L;

  @Override
  public void guardar(DatosClima clima) {
    if (clima.getId() == null) {
      clima.setId(idSequence++);
    }

    memoryStorage.add(clima);

    System.out.println("[Memory DB] Registrado historial climático. Total registros: " + memoryStorage.size());
  }

  @Override
  public Optional<DatosClima> buscarUltimo() {
    if (memoryStorage.isEmpty()) {
      return Optional.empty();
    }

    DatosClima latest = memoryStorage.getLast();
    return Optional.of(latest);
  }
}
