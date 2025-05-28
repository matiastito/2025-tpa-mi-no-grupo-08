package ar.edu.utn.frba.dds;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Aplicacion {
  public static void main(String[] args) {
    run(Aplicacion.class, args);
  }
}