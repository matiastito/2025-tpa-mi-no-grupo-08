package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HechoRepositorio extends JpaRepository<Hecho, Long> {
  @Query("select h from Hecho h where h.ubicacion.provincia is null")
  List<Hecho> hechosSinNormalizarProvinca();
}
