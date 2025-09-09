package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ElasticsearchHechoRepositorio extends ElasticsearchRepository<Hecho, Long> {
  List<Hecho> findByDescripcion(String descripcion);

}
