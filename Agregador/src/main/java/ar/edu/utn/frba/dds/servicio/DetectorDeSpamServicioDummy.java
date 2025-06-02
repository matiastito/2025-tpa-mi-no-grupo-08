package ar.edu.utn.frba.dds.servicio;

import static java.lang.Math.random;

import org.springframework.stereotype.Component;

//FIXME agregar el algortirmo TF y hacerlo async
@Component
public class DetectorDeSpamServicioDummy implements DetectorDeSpamServicio {
  @Override
  public boolean esSpam(String texto) {
    return ((int) (random() * 10) + 1) < 8;
  }
}
