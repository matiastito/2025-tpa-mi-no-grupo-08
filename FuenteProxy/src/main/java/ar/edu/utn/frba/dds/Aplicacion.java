package ar.edu.utn.frba.dds;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;

//FIXME modelar el móodulo con MULTIPLES fuentes
@SpringBootApplication
public class Aplicacion {
  public static void main(String[] args) {
    run(Aplicacion.class, args);
  }
}