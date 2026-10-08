/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.inversiones.magdezi.service;

import main.java.inversiones.magdezi.dto.LoginDTO;
import main.java.inversiones.magdezi.dto.RegistroDTO;
import main.java.inversiones.magdezi.model.Rol;
import main.java.inversiones.magdezi.model.Usuario;
import main.java.inversiones.magdezi.repository.RolRepository;
import main.java.inversiones.magdezi.repository.UsuarioRepository;
/**
 *
 * @author informatica
 */
public class UsuarioService {

  

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public UsuarioService() {
        this.usuarioRepository = new UsuarioRepository();
        this.rolRepository = new RolRepository();
    }

    public String registrar(RegistroDTO datos) {

        if (datos == null) {
            return "Los datos son obligatorios.";
        }

        if (datos.getNombre() == null
                || datos.getNombre().trim().isEmpty()) {

            return "El nombre es obligatorio.";
        }

        if (datos.getApellido() == null
                || datos.getApellido().trim().isEmpty()) {

            return "El apellido es obligatorio.";
        }

        if (datos.getCorreo() == null
                || datos.getCorreo().trim().isEmpty()) {

            return "El correo es obligatorio.";
        }

        if (!correoValido(datos.getCorreo())) {
            return "El correo no tiene un formato válido.";
        }

        if (datos.getPassword() == null
                || datos.getPassword().length() < 6) {

            return "La contraseña debe tener al menos 6 caracteres.";
        }

        if (usuarioRepository.existePorCorreo(datos.getCorreo())) {
            return "Ya existe un usuario con ese correo.";
        }

        Rol rolUser = rolRepository.buscarPorNombre("USER");

        if (rolUser == null) {
            return "No se encontró el rol USER.";
        }

        String passwordEncriptada =
                BCrypt.hashpw(
                        datos.getPassword(),
                        BCrypt.gensalt()
                );

        Usuario usuario = new Usuario(
                0,
                datos.getNombre().trim(),
                datos.getApellido().trim(),
                datos.getCorreo().trim(),
                passwordEncriptada,
                rolUser
        );

        boolean guardado =
                usuarioRepository.guardar(usuario);

        if (guardado) {
            return "Registro exitoso.";
        }

        return "No fue posible registrar el usuario.";
    }

    public Usuario iniciarSesion(LoginDTO datos) {

        if (datos == null) {
            return null;
        }

        if (datos.getCorreo() == null
                || datos.getPassword() == null) {

            return null;
        }

        Usuario usuario =
                usuarioRepository.buscarPorCorreo(
                        datos.getCorreo().trim()
                );

        if (usuario == null) {
            return null;
        }

        boolean passwordCorrecta =
                BCrypt.checkpw(
                        datos.getPassword(),
                        usuario.getPassword()
                );

        if (!passwordCorrecta) {
            return null;
        }

        return usuario;
    }

    private boolean correoValido(String correo) {

        return correo.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        );
    }
}

