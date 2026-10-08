/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.inversiones.magdezi.dto;

/**
 *
 * @author informatica
 */
public class RegistroDTO {

    private String nombre;
    private String apellido;
    private String correo;
    private String password;

    public RegistroDTO(String nombre, String apellido,
                       String correo, String password) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPassword() {
        return password;
    }
}