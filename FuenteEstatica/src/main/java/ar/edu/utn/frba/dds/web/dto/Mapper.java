package ar.edu.utn.frba.dds.web.dto;

import static ar.edu.utn.frba.dds.web.dto.HechoDTO.from;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;

public class Mapper {
  public static HechoDTO toDto(Hecho hecho) {
    return from(hecho);
  }
}