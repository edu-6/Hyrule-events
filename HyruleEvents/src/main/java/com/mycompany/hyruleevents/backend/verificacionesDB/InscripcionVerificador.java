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
public class InscripcionVerificador extends VerificadorEnDB {

    private static final String SELECT_PARTICIPANTE = "SELECT * FROM inscripcion"
            + " WHERE correo_participante = ? AND codigo_evento = ?";
    
    public InscripcionVerificador(Connection connection) {
        super(connection);
    }

    @Override
    public void verificarValidezConsulta(String[] parametros) throws ExceptionEnDB {
        
         String correo = parametros[0];
         String codigo_evento = parametros[1];
        
        try (PreparedStatement ps = connection.prepareStatement(SELECT_PARTICIPANTE);) {
            ps.setString(1, correo);
            ps.setString(2, codigo_evento);

            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){ // si encuentra la una inscripción con correo y codigo iguales
                throw new ExceptionEnDB("Ya existe la inscripcion!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ExceptionEnDB(" Hubo un error al realizar la consulta!");
        }
    }

}
