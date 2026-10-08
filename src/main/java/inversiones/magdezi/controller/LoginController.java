/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main.java.inversiones.magdezi.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

import main.java.inversiones.magdezi.dto.LoginDTO;
import main.java.inversiones.magdezi.model.Usuario;
import main.java.inversiones.magdezi.service.UsuarioService;
 


public class LoginController {

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink linkRegistro;

    private final UsuarioService usuarioService =
            new UsuarioService();

    @FXML
    private void iniciarSesion() {

        String correo = txtCorreo.getText();
        String password = txtPassword.getText();

        if (correo.isEmpty() || password.isEmpty()) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campos obligatorios",
                    "Debe ingresar correo y contraseña."
            );

            return;
        }

        LoginDTO loginDTO =
                new LoginDTO(correo, password);

        Usuario usuario =
                usuarioService.iniciarSesion(loginDTO);

        if (usuario == null) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error",
                    "Correo o contraseña incorrectos."
            );

            return;
        }

        String rol =
                usuario.getRol().getNombre();

        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Inicio de sesión exitoso",
                "Bienvenido "
                        + usuario.getNombre()
                        + ".\nRol: "
                        + rol
        );
    }

    @FXML
    private void abrirRegistro() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/view/registro.fxml"
                            )
                    );

            Parent root = loader.load();

            Stage stage =
                    (Stage) linkRegistro
                            .getScene()
                            .getWindow();

            stage.setScene(
                    new Scene(root)
            );

            stage.setTitle(
                    "Registro - Inversiones Magdezis"
            );

            stage.show();

        } catch (IOException e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error",
                    "No se pudo abrir el registro."
            );
        }
    }

    private void mostrarAlerta(
            Alert.AlertType tipo,
            String titulo,
            String mensaje) {

        Alert alert = new Alert(tipo);

        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        alert.showAndWait();
    }
}

