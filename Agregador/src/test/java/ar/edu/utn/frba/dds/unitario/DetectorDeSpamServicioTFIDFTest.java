package ar.edu.utn.frba.dds.unitario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.edu.utn.frba.dds.modelo.colaborador.Contribuyente;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHecho;
import ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHechoEstado;
import ar.edu.utn.frba.dds.repositorio.SolicitudEliminacionRepositorio;
import ar.edu.utn.frba.dds.servicio.DetectorDeSpamServicioTFIDF;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DetectorDeSpamServicioTFIDFTest {
  @Test
  public void testRechazaSolicitud() throws Exception {
    // 1. Preparar el mock del repositorio
    SolicitudEliminacionRepositorio mockRepo = mock(SolicitudEliminacionRepositorio.class);

    // 2. Crear el detector y asignarle el mock
    DetectorDeSpamServicioTFIDF detector = new DetectorDeSpamServicioTFIDF();
    var field = DetectorDeSpamServicioTFIDF.class.getDeclaredField("solicitudEliminacionRepositorio");
    field.setAccessible(true);
    field.set(detector, mockRepo);

    // 3. Simular solicitudes previas
    Hecho hecho = mock(Hecho.class);
    Contribuyente contribuyente = mock(Contribuyente.class);

    List<SolicitudDeEliminacionDeHecho> solicitudesPrevias = List.of(
        new SolicitudDeEliminacionDeHecho(contribuyente, hecho, "borrar borrar"),
        new SolicitudDeEliminacionDeHecho(contribuyente, hecho, "borrar eliminar")
    );

    when(mockRepo.findAll()).thenReturn(solicitudesPrevias);

    // 4. Solicitud nueva
    SolicitudDeEliminacionDeHecho nuevaSolicitud = new SolicitudDeEliminacionDeHecho(contribuyente, hecho, "borrar");

    // 5. Ejecutar detector
    detector.rechazaSpam(nuevaSolicitud);

    // 6. Verificar
    assertEquals(SolicitudDeEliminacionDeHechoEstado.RECHAZADA, nuevaSolicitud.getEstado());
  }
}
