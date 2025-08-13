/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.consultas;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public abstract class ConsultaSQL{
    protected Connection connection;

    public ConsultaSQL(Connection connection) {
        this.connection = connection;
    }
    
    public abstract void realizarConsulta(String [] parametros) throws SQLException;
}
