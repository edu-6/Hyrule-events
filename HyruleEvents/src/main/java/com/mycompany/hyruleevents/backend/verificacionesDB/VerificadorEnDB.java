/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.verificacionesDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public abstract class VerificadorEnDB {

    protected Connection connection;
    private static final String SELECT_EVENTO = "SELECT * FROM evento WHERE codigo = ? ";
    private static final String SELECT_PARTICIPANTE = "SELECT * FROM participante WHERE correo_electronico = ? ";

    public VerificadorEnDB(Connection connection) {
        this.connection = connection;
    }

    public abstract void verificarValidezConsulta(String[] parametros) throws ExceptionEnDB;

    public ResultSet buscarEvento(String codigo) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SELECT_EVENTO);
        ps.setString(1, codigo);
        ResultSet result = ps.executeQuery();
        //ps.close();
        return result;

    }
    
    
    public ResultSet buscarParticipante(String correo) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SELECT_PARTICIPANTE);
        ps.setString(1, correo);
        ResultSet result = ps.executeQuery();
        //ps.close();
        return result;
    }

    
}
