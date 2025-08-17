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
    
    public ParticipanteVerificador(Connection connection) {
        super(connection);
    }

    @Override
    public void verificarValidezConsulta(String[] parametros) throws ExceptionEnDB {
        String correo = parametros[3];
        
        try  {
            ResultSet resultSet = this.buscarParticipante(correo);
            if(resultSet.next()){ // si encuentra el participante
                throw new ExceptionEnDB("El participante con correo "+correo+" ya existe!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ExceptionEnDB(" Hubo un error al realizar la consulta!");
        }
    }

}
