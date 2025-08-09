/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class DBConnection {

    private static final String IP = "localhost";
    private static final int PUERTO = 3306;
    private static final String SCHEMA = "hr_db";
    private String usuario;
    private String contraseña;

    private static final String URL = "jdbc:mysql://"
            + IP + ":" + PUERTO + "/" + SCHEMA;

    private Connection connection;

    public DBConnection(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public void connect() throws SQLException {
        System.out.println("URL de conexion: " + URL);
        connection = DriverManager.getConnection(URL, usuario, contraseña);
        System.out.println("Esquema: " + connection.getSchema());
        System.out.println("Catalogo: " + connection.getCatalog());
    }

    public Connection getConnection() {
        return connection;
    }
    
    
}
