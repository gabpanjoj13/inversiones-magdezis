/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.inversiones.magdezi.repository;

import main.java.inversiones.magdezi.model.Rol;
import main.java.inversiones.magdezi.model.Usuario;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.java.inversiones.magdezi.config.ConexionDB;



public class UsuarioRepository {

    public boolean existePorCorreo(String correo) {

        String sql =
                "SELECT id FROM usuarios WHERE correo = ?";

        try (
            Connection connection = ConexionDB.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setString(1, correo);

            try (ResultSet resultSet = statement.executeQuery()) {

                return resultSet.next();
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error verificando correo: "
                    + e.getMessage()
            );

            return false;
        }
    }

    public boolean guardar(Usuario usuario) {

        String sql =
                "INSERT INTO usuarios "
                + "(nombre, apellido, correo, password, rol_id) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (
            Connection connection = ConexionDB.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getCorreo());
            statement.setString(4, usuario.getPassword());
            statement.setInt(5, usuario.getRol().getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error guardando usuario: "
                    + e.getMessage()
            );

            return false;
        }
    }

    public Usuario buscarPorCorreo(String correo) {

        String sql =
                "SELECT u.id, u.nombre, u.apellido, "
                + "u.correo, u.password, "
                + "r.id AS rol_id, r.nombre AS rol_nombre "
                + "FROM usuarios u "
                + "INNER JOIN roles r ON u.rol_id = r.id "
                + "WHERE u.correo = ?";

        try (
            Connection connection = ConexionDB.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setString(1, correo);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Rol rol = new Rol(
                            resultSet.getInt("rol_id"),
                            resultSet.getString("rol_nombre")
                    );

                    return new Usuario(
                            resultSet.getInt("id"),
                            resultSet.getString("nombre"),
                            resultSet.getString("apellido"),
                            resultSet.getString("correo"),
                            resultSet.getString("password"),
                            rol
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error buscando usuario: "
                    + e.getMessage()
            );
        }

        return null;
    }
}

