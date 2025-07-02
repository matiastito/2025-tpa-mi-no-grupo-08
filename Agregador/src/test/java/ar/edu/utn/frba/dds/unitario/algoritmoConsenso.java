package ar.edu.utn.frba.dds.unitario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ar.edu.utn.frba.dds.modelo.coleccion.Coleccion;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.navegacion.TipoConsenso;

/*Test unitario que verifica que la colección permita asignar correctamente un tipo de consenso.
Se crea una colección y se le asigna el enum TipoConsenso.MAYORIA_SIMPLE.
Luego se verifica que el tipo de consenso guardado sea efectivamente el asignado.*/

public class algoritmoConsenso {

    private Hecho hecho;
    private Coleccion coleccion
    ;
    @Test
    void testAsignarTipoConsenso() {
        coleccion = new Coleccion("Prueba", "Coleccion de prueba");
        coleccion.setTipoConsenso(TipoConsenso.MAYORIA_SIMPLE);
        assertEquals(TipoConsenso.MAYORIA_SIMPLE, coleccion.getTipoConsenso());
    }
}