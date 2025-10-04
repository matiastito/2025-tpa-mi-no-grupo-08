package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuenteRepositorio extends JpaRepository<Fuente, Long> {

}
