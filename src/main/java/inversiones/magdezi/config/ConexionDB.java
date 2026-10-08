/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.inversiones.magdezi.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author informatica
 */
public class ConexionDB {
    
    
    private ConexionDB() {
    }
    
    public static Connection getConnection() throws SQLException {
       return DriverManager.getConnection(
            CredentialsDb.URL,
            CredentialsDb.USER,
            CredentialsDb.PASSWORD
       );
    }
    
}
