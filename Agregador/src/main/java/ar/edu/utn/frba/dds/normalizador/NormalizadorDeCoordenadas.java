package ar.edu.utn.frba.dds.normalizador;

import static org.springframework.web.client.RestClient.create;
import ar.edu.utn.frba.dds.modelo.hecho.Ubicacion;
import org.springframework.stereotype.Component;

@Component
public class NormalizadorDeCoordenadas {
  public String provincia(String lat, String lon) {
    RespuestaGeoRef response =
        create("https://apis.datos.gob.ar/georef/api/ubicacion?lat=" + lat + "&lon=" + lon)
            .get()
            .retrieve()
            .body(RespuestaGeoRef.class);
    return response.getUbicacion().getProvincia().getNombre();
  }

  public String provincia(Ubicacion ubicacion) {
    return provincia(
        ubicacion.getLatitud().replace(',', '.'),
        ubicacion.getLongitud().replace(',', '.'));
  }
}
