package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HechoRepositorio extends JpaRepository<Hecho, Long> {
  @Query("select h from Hecho h where h.ubicacion.provincia is null")
  List<Hecho> hechosSinNormalizarProvinca();
}
