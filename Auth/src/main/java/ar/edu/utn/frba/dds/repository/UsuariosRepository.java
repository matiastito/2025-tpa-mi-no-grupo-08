package ar.edu.utn.frba.dds.repository;

import static ar.edu.utn.frba.dds.model.Rol.ADMIN;
import static ar.edu.utn.frba.dds.model.Rol.CONTRIBUYENTE;

import ar.edu.utn.frba.dds.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UsuariosRepository {
  private List<Usuario> usuarios;

  public UsuariosRepository() {
    this.usuarios = new ArrayList<>();
    this.cargarUsuarios();
  }

  private void cargarUsuarios() {
    var encoder = new BCryptPasswordEncoder();
    var usuario1 = new Usuario();
    usuario1.setNombre("Jose");
    usuario1.setNombreDeUsuario("admin");
    usuario1.setContrasenia(encoder.encode("1234"));
    usuario1.setRol(ADMIN);


    var usuario2 = new Usuario();
    usuario2.setNombre("Pedro");
    usuario2.setNombreDeUsuario("pedro");
    usuario2.setContrasenia(encoder.encode("1234"));
    usuario2.setRol(CONTRIBUYENTE);

    this.usuarios.add(usuario1);
    this.usuarios.add(usuario2);
  }

  public void save(Usuario usuario) {
    this.usuarios.add(usuario);
  }

  public List<Usuario> getUsuarios() {
    return this.usuarios;
  }

  public Optional<Usuario> findByNombreDeUsuario(String nombreDeUsuario) {
    return this.usuarios.stream().filter(usuario ->
        usuario.getNombreDeUsuario().equals(nombreDeUsuario)).findFirst();
  }
}
