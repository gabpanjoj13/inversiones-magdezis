/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.inversiones.magdezi.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.java.inversiones.magdezi.config.ConexionDB;
import main.java.inversiones.magdezi.model.Rol;
/**
 *
 * @author informatica
 */

public class RolRepository {

    public Rol buscarPorNombre(String nombre) {

        String sql = "SELECT id, nombre FROM roles WHERE nombre = ?";

        try (
            Connection connection = ConexionDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, nombre);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return new Rol(
                            resultSet.getInt("id"),
                            resultSet.getString("nombre")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error al buscar rol: " + e.getMessage()
            );
        }

        return null;
    }
}

