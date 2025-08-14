/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class ParticipanteUpdate extends ConsultaSQL{

    private static final String INSERT_PARTICIPANTE = "INSERT INTO participante "
            + "(nombre, tipo_de_participante, institucion_de_procedencia,"
            + "correo_electronico) "
            + "VALUES (?, ?, ?, ?)";
     
    public ParticipanteUpdate(Connection connection) {
        super(connection);
    }

    @Override
    public void realizarConsulta(String[] parametros) throws SQLException {
        String nombre = parametros[0];
        String tipoParticipante = parametros[1];
        String institucionProcedencia = parametros[2];
        String correo = parametros[3];
        
        PreparedStatement ps = connection.prepareStatement(INSERT_PARTICIPANTE);
        
        ps.setString(1, nombre);
        ps.setString(2, tipoParticipante);
        ps.setString(3, institucionProcedencia);
        ps.setString(4, correo);
        
        ps.executeUpdate();
        ps.close();
        
    }
    
}
