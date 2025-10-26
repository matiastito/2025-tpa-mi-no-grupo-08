package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.hecho.HechoModificacion;
import ar.edu.utn.frba.dds.modelo.hecho.HechoModificacionEstado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HechoModiciacionRepositorio extends JpaRepository<HechoModificacion, Long> {
  List<HechoModificacion> findByHechoModificacionEstado(HechoModificacionEstado hechoModificacionEstado);
}
