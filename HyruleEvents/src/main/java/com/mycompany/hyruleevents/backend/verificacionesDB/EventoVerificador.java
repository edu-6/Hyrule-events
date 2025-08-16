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
public class EventoVerificador extends VerificadorEnDB {

    private static final String SELECT_EVENTO = "SELECT * FROM evento WHERE codigo = ? ";
    public EventoVerificador(Connection connection) {
        super(connection);
    }

    @Override
    public void verificarValidezConsulta(String[] parametros) throws ExceptionEnDB {
        String codigo = parametros[0];
        
        try (PreparedStatement ps = connection.prepareStatement(SELECT_EVENTO);) {
            ps.setString(1, codigo);

            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){ // si encuentra el codigo
                throw new ExceptionEnDB("El evento con codigo "+codigo+" ya existe!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ExceptionEnDB(" Hubo un error al realizar la consulta!");
        }
    }

}
