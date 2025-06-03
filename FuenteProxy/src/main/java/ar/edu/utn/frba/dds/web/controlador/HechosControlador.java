package ar.edu.utn.frba.dds.web.controlador;

import ar.edu.utn.frba.dds.modelo.fuente.FuenteProxy;
import ar.edu.utn.frba.dds.web.dto.HechoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class HechosControlador {

  @Autowired
  private FuenteProxy fuenteProxy;

  @GetMapping("/hechos")
  public Collection<HechoDTO> hechos(String coleccionId) {
    return fuenteProxy.hechos();
        /*
        .collectList()
        .doOnNext(items -> {
          System.out.println("Fetched " + items.size() + " items in total.");

        })
        .flatMapMany(Flux::fromIterable) // If you want to process items individually
        .doOnNext(hechoDTO -> System.out.println("Processing item: " + hechoDTO.getCategoria()))
         */
    //.subscribe();
  }
}