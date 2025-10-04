package ar.edu.utn.frba.dds.util.archivo.lector;

import java.net.URI;
import java.util.List;

public interface LectorDeArchivo {
  List<List<String>> getRegistros(URI uri);
}
