package ar.edu.utn.frba.dds.servicio;

import ar.edu.utn.frba.dds.modelo.colaborador.Contribuyente;
import ar.edu.utn.frba.dds.repositorio.ContribuyenteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContribuyenteServicio {

  @Autowired
  private ContribuyenteRepositorio contribuyenteRepositorio;

  public Contribuyente contribuyente(String nombre, String apellido) {
    return contribuyenteRepositorio.findByNombreAndApellido(nombre, apellido).get();
  }

  public Contribuyente guardar(String nombre, String apellido) {
    if (contribuyente(nombre, apellido) == null) {
      contribuyenteRepositorio.save(new Contribuyente(nombre, apellido));
    }
    return contribuyenteRepositorio.findByNombreAndApellido(nombre, apellido).get();
  }
}
