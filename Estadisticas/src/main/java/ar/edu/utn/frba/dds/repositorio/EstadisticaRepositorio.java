package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.model.estadistica.Estadistica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadisticaRepositorio extends JpaRepository<Estadistica, Long> {

}
