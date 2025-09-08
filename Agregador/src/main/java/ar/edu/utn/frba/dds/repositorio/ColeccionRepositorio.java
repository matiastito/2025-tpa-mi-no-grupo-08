package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.coleccion.Coleccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColeccionRepositorio extends JpaRepository<Coleccion, Long> {

}
