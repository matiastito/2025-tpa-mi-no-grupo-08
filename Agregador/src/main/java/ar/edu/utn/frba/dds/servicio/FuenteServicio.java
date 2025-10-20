package ar.edu.utn.frba.dds.servicio;

import static ar.edu.utn.frba.dds.modelo.fuente.TipoFuente.DINAMICA;

import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.repositorio.FuenteRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuenteServicio {
  @Autowired
  private FuenteRepositorio fuenteRepositorio;

  public List<Fuente> fuentes() {
    return fuenteRepositorio.findAll();
  }

  public void crearHecho(Hecho hecho) {
    Optional<Fuente> fuenteDinamica = fuenteRepositorio.findByTipoFuente(DINAMICA);
    fuenteDinamica.ifPresent(fuente -> fuente.crear(hecho));
  }

  public void editarFuente(Fuente fuente) {
    Optional<Fuente> fuenteAEditar = fuenteRepositorio.findById(fuente.getId());
    if (fuenteAEditar.isPresent()) {
      fuenteAEditar.get().setBaseUrl(fuente.getBaseUrl());
      fuenteAEditar.get().setTipoFuente(fuente.getTipoFuente());
      fuenteRepositorio.save(fuenteAEditar.get());
    }
  }
}
