package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.administrador.Administrador;

public interface AdministradorRepositorio {
  Administrador administrador(String nombre);
}
