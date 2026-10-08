/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main.java.inversiones.magdezi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author informatica
 */

    public class Main extends Application {

        @Override
        public void start(Stage stage) throws Exception {

            Parent root =
                    FXMLLoader.load(
                            getClass().getResource(
                                    "/view/login.fxml"
                            )
                    );

            Scene scene =
                    new Scene(root);

            stage.setTitle(
                    "Inversiones Magdezis"
            );

            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }



 