package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHecho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudEliminacionRepositorio extends JpaRepository<SolicitudDeEliminacionDeHecho, Long> {
}
