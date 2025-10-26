package ar.edu.utn.frba.dds.servicio;

import ar.edu.utn.frba.dds.modelo.colaborador.Contribuyente;
import ar.edu.utn.frba.dds.repositorio.ContribuyenteRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContribuyenteServicio {

  @Autowired
  private ContribuyenteRepositorio contribuyenteRepositorio;

  public Optional<Contribuyente> contribuyente(String nombre, String apellido) {
    return contribuyenteRepositorio.findByNombreAndApellido(nombre, apellido);
  }

  public Contribuyente guardar(String nombre, String apellido) {
    if (contribuyente(nombre, apellido).isEmpty()) {
      contribuyenteRepositorio.save(new Contribuyente(nombre, apellido));
    }
    return contribuyenteRepositorio.findByNombreAndApellido(nombre, apellido).get();
  }
}
