package ar.edu.utn.frba.dds.web.controlador;

import ar.edu.utn.frba.dds.modelo.fuente.FuenteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class HechosControlador {

  @Autowired
  private FuenteProxy fuenteProxy;

  @GetMapping("/hechos")
  public void hechos(String coleccionId) {
    fuenteProxy.hechos().collectList()
        .doOnNext(items -> System.out.println("Fetched " + items.size() + " items in total."))
        .flatMapMany(Flux::fromIterable) // If you want to process items individually
        .doOnNext(desastreDTO -> System.out.println("Processing item: " + desastreDTO.getCategoria()))
        .subscribe();
    ;
  }
}