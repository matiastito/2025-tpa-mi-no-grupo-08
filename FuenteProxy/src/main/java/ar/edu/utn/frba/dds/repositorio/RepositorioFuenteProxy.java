package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.fuente.FuenteProxy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioFuenteProxy extends JpaRepository<FuenteProxy, Long> {

}
