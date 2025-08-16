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
public class ParticipanteVerificador extends VerificadorEnDB {

    private static final String SELECT_PARTICIPANTE = "SELECT * FROM participante WHERE correo_electronico = ? ";
    
    public ParticipanteVerificador(Connection connection) {
        super(connection);
    }

    @Override
    public void verificarValidezConsulta(String[] parametros) throws ExceptionEnDB {
        String correo = parametros[3];
        
        try (PreparedStatement ps = connection.prepareStatement(SELECT_PARTICIPANTE);) {
            ps.setString(1, correo);

            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){ // si encuentra el codigo
                throw new ExceptionEnDB("El participante con correo "+correo+" ya existe!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ExceptionEnDB(" Hubo un error al realizar la consulta!");
        }
    }

}
