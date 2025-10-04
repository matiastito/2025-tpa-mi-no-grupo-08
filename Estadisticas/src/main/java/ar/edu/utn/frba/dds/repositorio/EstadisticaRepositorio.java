package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.model.estadistica.Estadistica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadisticaRepositorio extends JpaRepository<Estadistica, Long> {

}
