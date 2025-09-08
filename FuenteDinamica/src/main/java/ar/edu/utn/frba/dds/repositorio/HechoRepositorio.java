package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HechoRepositorio extends JpaRepository<Hecho, Long> {

}
