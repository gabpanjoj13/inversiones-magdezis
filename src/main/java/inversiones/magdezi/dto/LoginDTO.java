/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.inversiones.magdezi.dto;

/**
 *
 * @author informatica
 */
public class LoginDTO {

    private String correo;
    private String password;

    public LoginDTO(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPassword() {
        return password;
    }
}


