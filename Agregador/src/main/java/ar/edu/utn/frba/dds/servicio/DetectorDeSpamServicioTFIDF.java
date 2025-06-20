package ar.edu.utn.frba.dds.servicio;

import ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHecho;
import ar.edu.utn.frba.dds.repositorio.SolicitudEliminacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DetectorDeSpamServicioTFIDF implements DetectorDeSpamServicio {

    @Autowired
    private SolicitudEliminacionRepositorio solicitudEliminacionRepositorio;

    @Override
    public void rechazaSpam(SolicitudDeEliminacionDeHecho solicitud) {
        String texto = solicitud.getMotivo();
        if (texto == null || texto.isBlank()) {
            solicitud.rechazar();
            return;
        }

        List<String> corpus = solicitudEliminacionRepositorio.solicitudesDeEliminacionDeHecho().stream()
                .filter(s -> s != solicitud && s.getMotivo() != null)
                .map(SolicitudDeEliminacionDeHecho::getMotivo)
                .toList();

        List<String> palabras = tokenize(texto);
        Map<String, Integer> frecuencia = contarFrecuencias(palabras);
        int totalPalabras = palabras.size();

        double sumaTFIDF = 0.0;
        for (String termino : frecuencia.keySet()) {
            double tf = frecuencia.get(termino) / (double) totalPalabras;
            double idf = calcularIDF(termino, corpus);
            sumaTFIDF += tf * idf;
        }

        double promedio = sumaTFIDF / frecuencia.size();
        double umbral = 0.07;
        if (promedio < umbral) {
            solicitud.rechazar();
        }
    }

    private List<String> tokenize(String texto) {
        return Arrays.stream(texto.toLowerCase().split("\\W+"))
                .filter(t -> !t.isBlank())
                .toList();
    }

    private Map<String, Integer> contarFrecuencias(List<String> tokens) {
        Map<String, Integer> frecuencias = new HashMap<>();
        for (String token : tokens) {
            frecuencias.put(token, frecuencias.getOrDefault(token, 0) + 1);
        }
        return frecuencias;
    }

    private double calcularIDF(String termino, List<String> corpus) {
        int docsConTermino = 1; // smoothing
        for (String doc : corpus) {
            if (doc.toLowerCase().contains(termino)) {
                docsConTermino++;
            }
        }
        return Math.log((1 + corpus.size()) / (double) docsConTermino);
    }
}